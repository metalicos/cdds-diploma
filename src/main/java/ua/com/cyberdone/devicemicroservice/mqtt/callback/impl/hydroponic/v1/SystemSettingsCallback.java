package ua.com.cyberdone.devicemicroservice.mqtt.callback.impl.hydroponic.v1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.stereotype.Service;
import ua.com.cyberdone.devicemicroservice.device.util.GlobalConstants;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.entity.HydroponicSettingDetails;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.entity.HydroponicSystemSettings;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.model.SystemSettings;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.service.HydroponicSettingDetailsService;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.service.HydroponicSystemSettingsService;
import ua.com.cyberdone.devicemicroservice.mqtt.callback.Callback;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
@Service("hydroponic/v1/system-settings")
public class SystemSettingsCallback implements Callback {
    private final ObjectMapper objectMapper;
    private final HydroponicSystemSettingsService hydroponicSystemSettingsService;
    private final HydroponicSettingDetailsService hydroponicSettingDetailsService;

    @Override
    public void execute(MqttClient client, MqttMessage message) {
        long start = System.currentTimeMillis();
        log.info("[START] {} [execute]", this.getClass().getCanonicalName());
        var jsonMsg = new String(message.getPayload(), StandardCharsets.UTF_8);
        try {
            var phSensorSettings = objectMapper.readValue(jsonMsg, SystemSettings.class);
            log.info("[EcSensorSettings]:{}", phSensorSettings);
            hydroponicSystemSettingsService.findActive(phSensorSettings.getDeviceUuid())
                    .ifPresentOrElse(oldSettings -> updateSettingsInDb(oldSettings, phSensorSettings),
                            () -> saveSettingsInDb(phSensorSettings));
        } catch (JsonProcessingException ex) {
            log.error("Error to precess EcSensorSettings.", ex);
        }
        log.info("[END] [TAKEN:{}ms] {} [execute] ", System.currentTimeMillis() - start, this.getClass().getCanonicalName());
    }


    private void saveSettingsInDb(SystemSettings systemSettings) {
        if (systemSettings == null) {
            log.error("SystemSettings settings are null");
            return;
        }
        long start = System.currentTimeMillis();
        log.info("[START] {} [saveSettingsInDb]", this.getClass().getCanonicalName());

        //save details
        var hydroponicSettingDetails = HydroponicSettingDetails.builder()
                .settingType(GlobalConstants.HydroponicSettV1Constants.ACTIVE_SETTINGS_TYPE)
                .createdTimestamp(LocalDateTime.now())
                .build();

        var savedHydroponicSettingDetails = hydroponicSettingDetailsService.save(hydroponicSettingDetails);

        //save systemSettings
        var hydroponicEcSensorSettings = HydroponicSystemSettings.builder()
                .settingDetailId(savedHydroponicSettingDetails.getId())
                .deviceUuid(systemSettings.getDeviceUuid())
                .time(systemSettings.getTime())
                .timeZone(systemSettings.getTimeZone())
                .ntpServer(systemSettings.getNtpServer())
                .wifiSSID(systemSettings.getWifiSSID())
                .wifiPASS(systemSettings.getWifiPASS())
                .restarts(systemSettings.getRestarts())
                .growingDay(systemSettings.getGrowingDay())
                .isGrowing(systemSettings.isGrowing())
                .growStartDate(systemSettings.getGrowStartDate())
                .dispensersEnable(systemSettings.isDispensersEnable())
                .sensorsEnable(systemSettings.isSensorsEnable())
                .createdTimestamp(LocalDateTime.now())
                .build();

        hydroponicSystemSettingsService.save(hydroponicEcSensorSettings);

        log.info("[END] [TAKEN:{}ms] {} [saveSettingsInDb]", System.currentTimeMillis() - start, this.getClass().getCanonicalName());
    }

    private void updateSettingsInDb(HydroponicSystemSettings fromDb, SystemSettings settings) {
        if (settings == null) {
            log.error("SystemSettings are null");
            return;
        }
        long start = System.currentTimeMillis();
        log.info("[START] {} [updateSettingsInDb]", this.getClass().getCanonicalName());

        //update
        fromDb.setDeviceUuid(settings.getDeviceUuid());
        fromDb.setTime(settings.getTime());
        fromDb.setTimeZone(settings.getTimeZone());
        fromDb.setNtpServer(settings.getNtpServer());
        fromDb.setWifiSSID(settings.getWifiSSID());
        fromDb.setWifiPASS(settings.getWifiPASS());
        fromDb.setRestarts(settings.getRestarts());
        fromDb.setGrowingDay(settings.getGrowingDay());
        fromDb.setGrowing(settings.isGrowing());
        fromDb.setGrowStartDate(settings.getGrowStartDate());
        fromDb.setDispensersEnable(settings.isDispensersEnable());
        fromDb.setSensorsEnable(settings.isSensorsEnable());
        fromDb.setUpdatedTimestamp(LocalDateTime.now());

        hydroponicSystemSettingsService.update(fromDb);

        log.info("[END] [TAKEN:{}ms] {} [updateSettingsInDb] ", System.currentTimeMillis() - start, this.getClass().getCanonicalName());
    }
}

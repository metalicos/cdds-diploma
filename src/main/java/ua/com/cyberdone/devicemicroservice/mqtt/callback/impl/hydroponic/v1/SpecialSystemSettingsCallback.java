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
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.entity.HydroponicSpecialSystemSettings;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.model.SpecialSystemSettings;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.service.HydroponicSettingDetailsService;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.service.HydroponicSpecialSystemSettingsService;
import ua.com.cyberdone.devicemicroservice.mqtt.callback.Callback;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
@Service("hydroponic/v1/special-system-settings")
public class SpecialSystemSettingsCallback implements Callback {
    private final ObjectMapper objectMapper;
    private final HydroponicSpecialSystemSettingsService hydroponicSpecialSystemSettingsService;
    private final HydroponicSettingDetailsService hydroponicSettingDetailsService;

    @Override
    public void execute(MqttClient client, MqttMessage message) {
        long start = System.currentTimeMillis();
        log.info("[START] {} [execute]", this.getClass().getCanonicalName());
        var jsonMsg = new String(message.getPayload(), StandardCharsets.UTF_8);
        try {
            var phSensorSettings = objectMapper.readValue(jsonMsg, SpecialSystemSettings.class);
            log.info("[EcSensorSettings]:{}", phSensorSettings);
            hydroponicSpecialSystemSettingsService.findActive(phSensorSettings.getDeviceUuid())
                    .ifPresentOrElse(oldSettings -> updateSettingsInDb(oldSettings, phSensorSettings),
                            () -> saveSettingsInDb(phSensorSettings));
        } catch (JsonProcessingException ex) {
            log.error("Error to precess EcSensorSettings.", ex);
        }
        log.info("[END] [TAKEN:{}ms] {} [execute] ", System.currentTimeMillis() - start, this.getClass().getCanonicalName());
    }


    private void saveSettingsInDb(SpecialSystemSettings specialSystemSettings) {
        if (specialSystemSettings == null) {
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

        //save specialSystemSettings
        var hydroponicEcSensorSettings = HydroponicSpecialSystemSettings.builder()
                .settingDetailId(savedHydroponicSettingDetails.getId())
                .deviceUuid(specialSystemSettings.getDeviceUuid())
                .time(specialSystemSettings.getTime())
                .softwareVersion(specialSystemSettings.getSoftwareVersion())
                .scheduleAmount(specialSystemSettings.getScheduleAmount())
                .dispensersAmount(specialSystemSettings.getDispensersAmount())
                .wifiRssi(specialSystemSettings.getWifiRssi())
                .wifiBsid(specialSystemSettings.getWifiBsid())
                .localIp(specialSystemSettings.getLocalIp())
                .subnetMask(specialSystemSettings.getSubnetMask())
                .gatewayIp(specialSystemSettings.getGatewayIp())
                .macAddr(specialSystemSettings.getMacAddr())
                .createdTimestamp(LocalDateTime.now())
                .build();

        hydroponicSpecialSystemSettingsService.save(hydroponicEcSensorSettings);

        log.info("[END] [TAKEN:{}ms] {} [saveSettingsInDb]", System.currentTimeMillis() - start, this.getClass().getCanonicalName());
    }

    private void updateSettingsInDb(HydroponicSpecialSystemSettings fromDb, SpecialSystemSettings settings) {
        if (settings == null) {
            log.error("SystemSettings are null");
            return;
        }
        long start = System.currentTimeMillis();
        log.info("[START] {} [updateSettingsInDb]", this.getClass().getCanonicalName());

        //update
        fromDb.setDeviceUuid(settings.getDeviceUuid());
        fromDb.setTime(settings.getTime());
        fromDb.setSoftwareVersion(settings.getSoftwareVersion());
        fromDb.setScheduleAmount(settings.getScheduleAmount());
        fromDb.setDispensersAmount(settings.getDispensersAmount());
        fromDb.setWifiRssi(settings.getWifiRssi());
        fromDb.setWifiBsid(settings.getWifiBsid());
        fromDb.setLocalIp(settings.getLocalIp());
        fromDb.setSubnetMask(settings.getSubnetMask());
        fromDb.setGatewayIp(settings.getGatewayIp());
        fromDb.setMacAddr(settings.getMacAddr());
        fromDb.setUpdatedTimestamp(LocalDateTime.now());

        hydroponicSpecialSystemSettingsService.update(fromDb);

        log.info("[END] [TAKEN:{}ms] {} [updateSettingsInDb] ", System.currentTimeMillis() - start, this.getClass().getCanonicalName());
    }
}

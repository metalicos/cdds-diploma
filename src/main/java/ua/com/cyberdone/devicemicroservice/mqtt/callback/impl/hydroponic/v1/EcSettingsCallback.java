package ua.com.cyberdone.devicemicroservice.mqtt.callback.impl.hydroponic.v1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.stereotype.Service;
import ua.com.cyberdone.devicemicroservice.device.util.GlobalConstants;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.entity.HydroponicEcSensorSettings;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.entity.HydroponicSettingDetails;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.model.EcSensorSettings;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.service.HydroponicEcSensorSettingsService;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.service.HydroponicSettingDetailsService;
import ua.com.cyberdone.devicemicroservice.mqtt.callback.Callback;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
@Service("hydroponic/v1/ec-settings")
public class EcSettingsCallback implements Callback {
    private final ObjectMapper objectMapper;
    private final HydroponicEcSensorSettingsService hydroponicEcSensorSettingsService;
    private final HydroponicSettingDetailsService hydroponicSettingDetailsService;

    @Override
    public void execute(MqttClient client, MqttMessage message) {
        long start = System.currentTimeMillis();
        log.info("[START] {} [execute]", this.getClass().getCanonicalName());
        var jsonMsg = new String(message.getPayload(), StandardCharsets.UTF_8);
        try {
            var ecSensorSettings = objectMapper.readValue(jsonMsg, EcSensorSettings.class);
            log.info("[EcSensorSettings]:{}", ecSensorSettings);
            hydroponicEcSensorSettingsService.findActive(ecSensorSettings.getDeviceUuid())
                    .ifPresentOrElse(oldSettings -> updateSettingsInDb(oldSettings, ecSensorSettings),
                            () -> saveSettingsInDb(ecSensorSettings));
        } catch (JsonProcessingException ex) {
            log.error("Error to precess EcSensorSettings.", ex);
        }
        log.info("[END] [TAKEN:{}ms] {} [execute] ", System.currentTimeMillis() - start, this.getClass().getCanonicalName());
    }


    private void saveSettingsInDb(EcSensorSettings ecSensorSettings) {
        if (ecSensorSettings == null) {
            log.error("EcSensorSettings settings are null");
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

        //save ecSensorSettings
        var hydroponicEcSensorSettings = HydroponicEcSensorSettings.builder()
                .settingDetailId(savedHydroponicSettingDetails.getId())
                .deviceUuid(ecSensorSettings.getDeviceUuid())
                .time(ecSensorSettings.getTime())
                .kLowPoint(ecSensorSettings.getKLowPoint())
                .kHighPoint(ecSensorSettings.getKHighPoint())
                .rawEc(ecSensorSettings.getRawEc())
                .createdTimestamp(LocalDateTime.now())
                .build();


        hydroponicEcSensorSettingsService.save(hydroponicEcSensorSettings);

        log.info("[END] [TAKEN:{}ms] {} [saveSettingsInDb]", System.currentTimeMillis() - start, this.getClass().getCanonicalName());
    }

    private void updateSettingsInDb(HydroponicEcSensorSettings fromDb, EcSensorSettings settings) {
        if (settings == null) {
            log.error("Dispenser settings are null");
            return;
        }
        long start = System.currentTimeMillis();
        log.info("[START] {} [updateSettingsInDb]", this.getClass().getCanonicalName());

        //update
        fromDb.setDeviceUuid(settings.getDeviceUuid());
        fromDb.setTime(settings.getTime());
        fromDb.setKLowPoint(settings.getKLowPoint());
        fromDb.setKHighPoint(settings.getKHighPoint());
        fromDb.setRawEc(settings.getRawEc());
        fromDb.setUpdatedTimestamp(LocalDateTime.now());

        hydroponicEcSensorSettingsService.update(fromDb);

        log.info("[END] [TAKEN:{}ms] {} [updateSettingsInDb] ", System.currentTimeMillis() - start, this.getClass().getCanonicalName());
    }
}

package ua.com.cyberdone.devicemicroservice.mqtt.callback.impl.hydroponic.v1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.stereotype.Service;
import ua.com.cyberdone.devicemicroservice.device.common.util.GlobalConstants;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.entity.HydroponicPhSensorSettings;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.entity.HydroponicSettingDetails;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.model.PhSensorSettings;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.service.HydroponicPhSensorSettingsService;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.service.HydroponicSettingDetailsService;
import ua.com.cyberdone.devicemicroservice.mqtt.callback.Callback;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
@Service("hydroponic/v1/ph-settings")
public class PhSettingsCallback implements Callback {
    private final ObjectMapper objectMapper;
    private final HydroponicPhSensorSettingsService hydroponicPhSensorSettingsService;
    private final HydroponicSettingDetailsService hydroponicSettingDetailsService;

    @Override
    public void execute(MqttClient client, MqttMessage message) {
        long start = System.currentTimeMillis();
        log.info("[START] {} [execute]", this.getClass().getCanonicalName());
        var jsonMsg = new String(message.getPayload(), StandardCharsets.UTF_8);
        try {
            var phSensorSettings = objectMapper.readValue(jsonMsg, PhSensorSettings.class);
            log.info("[EcSensorSettings]:{}", phSensorSettings);
            hydroponicPhSensorSettingsService.findActive(phSensorSettings.getDeviceUuid())
                    .ifPresentOrElse(oldSettings -> updateSettingsInDb(oldSettings, phSensorSettings),
                            () -> saveSettingsInDb(phSensorSettings));
        } catch (JsonProcessingException ex) {
            log.error("Error to precess EcSensorSettings.", ex);
        }
        log.info("[END] [TAKEN:{}ms] {} [execute] ", System.currentTimeMillis() - start, this.getClass().getCanonicalName());
    }


    private void saveSettingsInDb(PhSensorSettings phSensorSettings) {
        if (phSensorSettings == null) {
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

        //save phSensorSettings
        var hydroponicEcSensorSettings = HydroponicPhSensorSettings.builder()
                .settingDetailId(savedHydroponicSettingDetails.getId())
                .deviceUuid(phSensorSettings.getDeviceUuid())
                .time(phSensorSettings.getTime())
                .point(phSensorSettings.getPoint())
                .value(phSensorSettings.getValue())
                .adc(phSensorSettings.getAdc())
                .slope(phSensorSettings.getSlope())
                .adcOffset(phSensorSettings.getAdcOffset())
                .oversampling(phSensorSettings.getOversampling())
                .createdTimestamp(LocalDateTime.now())
                .build();

        hydroponicPhSensorSettingsService.save(hydroponicEcSensorSettings);

        log.info("[END] [TAKEN:{}ms] {} [saveSettingsInDb]", System.currentTimeMillis() - start, this.getClass().getCanonicalName());
    }

    private void updateSettingsInDb(HydroponicPhSensorSettings fromDb, PhSensorSettings settings) {
        if (settings == null) {
            log.error("Dispenser settings are null");
            return;
        }
        long start = System.currentTimeMillis();
        log.info("[START] {} [updateSettingsInDb]", this.getClass().getCanonicalName());

        //update
        fromDb.setDeviceUuid(settings.getDeviceUuid());
        fromDb.setTime(settings.getTime());
        fromDb.setPoint(settings.getPoint());
        fromDb.setValue(settings.getValue());
        fromDb.setAdc(settings.getAdc());
        fromDb.setSlope(settings.getSlope());
        fromDb.setAdcOffset(settings.getAdcOffset());
        fromDb.setOversampling(settings.getOversampling());
        fromDb.setUpdatedTimestamp(LocalDateTime.now());

        hydroponicPhSensorSettingsService.update(fromDb);

        log.info("[END] [TAKEN:{}ms] {} [updateSettingsInDb] ", System.currentTimeMillis() - start, this.getClass().getCanonicalName());
    }
}

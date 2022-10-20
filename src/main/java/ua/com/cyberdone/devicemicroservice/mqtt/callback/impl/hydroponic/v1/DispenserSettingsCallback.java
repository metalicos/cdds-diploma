package ua.com.cyberdone.devicemicroservice.mqtt.callback.impl.hydroponic.v1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.cyberdone.devicemicroservice.device.common.util.GlobalConstants;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.entity.HydroponicDispenserSettings;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.entity.HydroponicSettingDetails;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.model.DispenserSettings;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.service.HydroponicDispenserSettingsService;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.service.HydroponicSettingDetailsService;
import ua.com.cyberdone.devicemicroservice.mqtt.callback.Callback;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
@Service("hydroponic/v1/dispenser-settings")
public class DispenserSettingsCallback implements Callback {
    private final ObjectMapper objectMapper;
    private final MqttClient client;
    private final HydroponicDispenserSettingsService hydroponicDispenserSettingsService;
    private final HydroponicSettingDetailsService hydroponicSettingDetailsService;


    @Async
    @Override
    @Transactional
    public void execute(MqttClient client, MqttMessage message) {
        long start = System.currentTimeMillis();
        log.info("[START] {} [execute]", this.getClass().getCanonicalName());
        var jsonMsg = new String(message.getPayload(), StandardCharsets.UTF_8);
        try {
            var dispenserSettings = objectMapper.readValue(jsonMsg, DispenserSettings.class);
            log.info("[Dispenser settings]:{}", dispenserSettings);
            hydroponicDispenserSettingsService.findActive(dispenserSettings.getDeviceUuid(), dispenserSettings.getIndex())
                    .ifPresentOrElse(oldSettings -> updateSettingsInDb(oldSettings, dispenserSettings),
                            () -> saveSettingsInDb(dispenserSettings));
        } catch (JsonProcessingException ex) {
            log.error("Error to precess DispenserSettings.", ex);
        }
        log.info("[END] [TAKEN:{}ms] {} [execute] ", System.currentTimeMillis() - start, this.getClass().getCanonicalName());
    }

    private void saveSettingsInDb(DispenserSettings dispenserSettings) {
        if (dispenserSettings == null) {
            log.error("Dispenser settings are null");
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

        //save dispenserSettings
        var hydroponicDispenserSettings = HydroponicDispenserSettings.builder()
                .settingDetailId(savedHydroponicSettingDetails.getId())
                .deviceUuid(dispenserSettings.getDeviceUuid())
                .index(dispenserSettings.getIndex())
                .time(dispenserSettings.getTime())
                .dispenserName(dispenserSettings.getDispenserName())
                .pinA(dispenserSettings.getPinA())
                .pinB(dispenserSettings.getPinB())
                .regulationDirection(dispenserSettings.isRegulationDirection())
                .enable(dispenserSettings.isEnable())
                .polarity(dispenserSettings.isPolarity())
                .smartDose(dispenserSettings.isSmartDose())
                .totalAddedMl(dispenserSettings.getTotalAddedMl())
                .mlPerMs(dispenserSettings.getMlPerMs())
                .targetValue(dispenserSettings.getTargetValue())
                .error(dispenserSettings.getError())
                .recheckDispenserAfterSeconds(dispenserSettings.getRecheckDispenserAfterSeconds())
                .lastDispenserRecheckTime(dispenserSettings.getLastDispenserRecheckTime())
                .mixingVolumeMl(dispenserSettings.getMixingVolumeMl())
                .createdTimestamp(LocalDateTime.now())
                .build();

        hydroponicDispenserSettingsService.save(hydroponicDispenserSettings);

        log.info("[END] [TAKEN:{}ms] {} [saveSettingsInDb]", System.currentTimeMillis() - start, this.getClass().getCanonicalName());
    }

    private void updateSettingsInDb(HydroponicDispenserSettings fromDb, DispenserSettings settings) {
        if (settings == null) {
            log.error("Dispenser settings are null");
            return;
        }
        long start = System.currentTimeMillis();
        log.info("[START] {} [updateSettingsInDb]", this.getClass().getCanonicalName());

        //update
        fromDb.setDeviceUuid(settings.getDeviceUuid());
        fromDb.setIndex(settings.getIndex());
        fromDb.setTime(settings.getTime());
        fromDb.setDispenserName(settings.getDispenserName());
        fromDb.setPinA(settings.getPinA());
        fromDb.setPinB(settings.getPinB());
        fromDb.setRegulationDirection(settings.isRegulationDirection());
        fromDb.setEnable(settings.isEnable());
        fromDb.setPolarity(settings.isPolarity());
        fromDb.setSmartDose(settings.isSmartDose());
        fromDb.setTotalAddedMl(settings.getTotalAddedMl());
        fromDb.setMlPerMs(settings.getMlPerMs());
        fromDb.setTargetValue(settings.getTargetValue());
        fromDb.setError(settings.getError());
        fromDb.setRecheckDispenserAfterSeconds(settings.getRecheckDispenserAfterSeconds());
        fromDb.setLastDispenserRecheckTime(settings.getLastDispenserRecheckTime());
        fromDb.setMixingVolumeMl(settings.getMixingVolumeMl());
        fromDb.setUpdatedTimestamp(LocalDateTime.now());

        hydroponicDispenserSettingsService.update(fromDb);

        log.info("[END] [TAKEN:{}ms] {} [updateSettingsInDb] ", System.currentTimeMillis() - start, this.getClass().getCanonicalName());
    }
}

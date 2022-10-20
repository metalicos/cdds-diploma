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
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.entity.HydroponicDispenseScheduleSettings;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.entity.HydroponicSettingDetails;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.model.DispenseScheduleSettings;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.service.HydroponicDispenseScheduleSettingsService;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.service.HydroponicSettingDetailsService;
import ua.com.cyberdone.devicemicroservice.mqtt.callback.Callback;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
@Service("hydroponic/v1/dispense-schedule-settings")
public class DispenseScheduleSettingsCallback implements Callback {
    private final ObjectMapper objectMapper;
    private final HydroponicDispenseScheduleSettingsService hydroponicDispenseScheduleSettingsService;
    private final HydroponicSettingDetailsService hydroponicSettingDetailsService;

    @Async
    @Override
    @Transactional
    public void execute(MqttClient client, MqttMessage message) {
        long start = System.currentTimeMillis();
        log.info("[START] {} [execute]", this.getClass().getCanonicalName());
        var jsonMsg = new String(message.getPayload(), StandardCharsets.UTF_8);
        try {
            var dispenseScheduleSettings = objectMapper.readValue(jsonMsg, DispenseScheduleSettings.class);
            log.info("[DispenseScheduleSettings]:{}", dispenseScheduleSettings);
            hydroponicDispenseScheduleSettingsService.findActive(dispenseScheduleSettings.getDeviceUuid(), dispenseScheduleSettings.getIndex())
                    .ifPresentOrElse(oldSettings -> updateSettingsInDb(oldSettings, dispenseScheduleSettings),
                            () -> saveSettingsInDb(dispenseScheduleSettings));
        } catch (JsonProcessingException ex) {
            log.error("Error to precess DispenserSettings.", ex);
        }
        log.info("[END] [TAKEN:{}ms] {} [execute] ", System.currentTimeMillis() - start, this.getClass().getCanonicalName());
    }

    private void saveSettingsInDb(DispenseScheduleSettings dispenserSettings) {
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
        var hydroponicDispenseScheduleSettings = HydroponicDispenseScheduleSettings.builder()
                .settingDetailId(savedHydroponicSettingDetails.getId())
                .deviceUuid(dispenserSettings.getDeviceUuid())
                .index(dispenserSettings.getIndex())
                .time(dispenserSettings.getTime())
                .day(dispenserSettings.getDay())
                .targetEc(dispenserSettings.getTargetEc())
                .targetPh(dispenserSettings.getTargetPh())
                .ecError(dispenserSettings.getEcError())
                .phError(dispenserSettings.getPhError())
                .recheckAfterSec(dispenserSettings.getRecheckAfterSec())
                .isActive(dispenserSettings.getIsActive())
                .doseMl(dispenserSettings.getDoseMl())
                .createdTimestamp(LocalDateTime.now())
                .build();

        hydroponicDispenseScheduleSettingsService.save(hydroponicDispenseScheduleSettings);

        log.info("[END] [TAKEN:{}ms] {} [saveSettingsInDb]", System.currentTimeMillis() - start, this.getClass().getCanonicalName());
    }

    private void updateSettingsInDb(HydroponicDispenseScheduleSettings fromDb, DispenseScheduleSettings settings) {
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
        fromDb.setDay(settings.getDay());
        fromDb.setTargetEc(settings.getTargetEc());
        fromDb.setTargetPh(settings.getTargetPh());
        fromDb.setEcError(settings.getEcError());
        fromDb.setPhError(settings.getPhError());
        fromDb.setRecheckAfterSec(settings.getRecheckAfterSec());
        fromDb.setIsActive(settings.getIsActive());
        fromDb.setDoseMl(settings.getDoseMl());
        fromDb.setUpdatedTimestamp(LocalDateTime.now());

        hydroponicDispenseScheduleSettingsService.update(fromDb);

        log.info("[END] [TAKEN:{}ms] {} [updateSettingsInDb] ", System.currentTimeMillis() - start, this.getClass().getCanonicalName());
    }
}

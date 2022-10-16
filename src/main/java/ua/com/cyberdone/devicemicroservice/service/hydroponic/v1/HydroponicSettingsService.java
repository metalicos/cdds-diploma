package ua.com.cyberdone.devicemicroservice.service.hydroponic.v1;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import ua.com.cyberdone.devicemicroservice.data.entity.hydroponic.v1.HydroponicSettings;
import ua.com.cyberdone.devicemicroservice.data.repository.hydroponic.v1.HydroponicSettingsRepository;
import ua.com.cyberdone.devicemicroservice.model.hydroponic.v1.*;
import ua.com.cyberdone.devicemicroservice.util.GlobalConstants.HydroponicSettV1Constants;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public record HydroponicSettingsService(HydroponicSettingsRepository hydroponicSettingsRepository) {

    public List<HydroponicSettings> findAll(String uuid, Long page, Long limit, String type) {
        long start = System.currentTimeMillis();
        log.info("Start HydroponicSettingsService.findAll. {}", uuid);

        List<HydroponicSettings> hydroponicSettings;

        if (HydroponicSettV1Constants.ACTIVE_SETTINGS_TYPE.equals(type)) {
            hydroponicSettings = hydroponicSettingsRepository.findAll(uuid, 0L, 1L, HydroponicSettV1Constants.ACTIVE_SETTINGS_TYPE);
        } else {
            var from = page * limit;
            hydroponicSettings = hydroponicSettingsRepository.findAll(uuid, from, limit, type);
        }

        log.info("End HydroponicSettingsService.findAll. {}", hydroponicSettings);
        log.info("Taken: {} ms. {}", System.currentTimeMillis() - start, hydroponicSettings);
        return hydroponicSettings;
    }

    public void update(String uuid, String type, Object settings, String name, String description) {
        long start = System.currentTimeMillis();
        log.info("Start HydroponicSettingsService.update. {}", uuid);
        var time = LocalDateTime.now();

        var sett = Optional.ofNullable(settings).orElseThrow(() -> new IllegalArgumentException("Settings obj is NULL"));
        switch (type) {
            case HydroponicSettV1Constants.EC_SENSOR_SETTINGS_TYPE -> {
                hydroponicSettingsRepository.update(uuid, (EcSensorSettings) sett, time);
            }
            case HydroponicSettV1Constants.PH_SENSOR_SETTINGS_TYPE -> {
                hydroponicSettingsRepository.update(uuid, (PhSensorSettings) sett, time);
            }
            case HydroponicSettV1Constants.SYSTEM_SETTINGS_TYPE -> {
                hydroponicSettingsRepository.update(uuid, (SystemSettings) sett, time);
            }
            case HydroponicSettV1Constants.SPECIAL_SYSTEM_SETTINGS_TYPE -> {
                hydroponicSettingsRepository.update(uuid, (SpecialSystemSettings) sett, time);
            }
            case HydroponicSettV1Constants.DISPENSERS_SETTINGS_TYPE -> {
                hydroponicSettingsRepository.update(uuid, (DispensersSettings) sett, time);
            }
            case HydroponicSettV1Constants.DISPENSE_SCHEDULES_SETTINGS_TYPE -> {
                hydroponicSettingsRepository.update(uuid, (DispenseSchedulesSettings) sett, time);
            }
        }
        if (StringUtils.isNoneBlank(name, description)) {
            hydroponicSettingsRepository.update(uuid, name, description, type, time);
        }
        log.info("End HydroponicSettingsService.update. {}", uuid);
        log.info("Taken: {} ms. {}", System.currentTimeMillis() - start, uuid);
    }

    public void save(String uuid, String type, Object settings, String name, String description) {
        long start = System.currentTimeMillis();
        log.info("Start HydroponicSettingsService.update. {}", uuid);
        var time = LocalDateTime.now();

        var sett = Optional.ofNullable(settings).orElseThrow(() -> new IllegalArgumentException("Settings obj is NULL"));
        switch (type) {
            case HydroponicSettV1Constants.EC_SENSOR_SETTINGS_TYPE -> {
                hydroponicSettingsRepository.save(uuid, name, description, null, null, null, null, null, (EcSensorSettings) sett, type, time);
            }
            case HydroponicSettV1Constants.PH_SENSOR_SETTINGS_TYPE -> {
                hydroponicSettingsRepository.save(uuid, name, description, null, null, null, null, (PhSensorSettings) sett, null, type, time);
            }
            case HydroponicSettV1Constants.SYSTEM_SETTINGS_TYPE -> {
                hydroponicSettingsRepository.save(uuid, name, description, null, null, (SystemSettings) sett, null, null, null, type, time);
            }
            case HydroponicSettV1Constants.SPECIAL_SYSTEM_SETTINGS_TYPE -> {
                hydroponicSettingsRepository.save(uuid, name, description, null, null, null, (SpecialSystemSettings) sett, null, null, type, time);
            }
            case HydroponicSettV1Constants.DISPENSERS_SETTINGS_TYPE -> {
                hydroponicSettingsRepository.save(uuid, name, description, (DispensersSettings) sett, null, null, null, null, null, type, time);
            }
            case HydroponicSettV1Constants.DISPENSE_SCHEDULES_SETTINGS_TYPE -> {
                hydroponicSettingsRepository.save(uuid, name, description, null, (DispenseSchedulesSettings) sett, null, null, null, null, type, time);
            }
            case HydroponicSettV1Constants.ALL_SETTINGS_TYPE -> {
                var all = (HydroponicAllSettings) sett;
                hydroponicSettingsRepository.save(uuid, name, description, all.getDispensersSettings(), all.getDispenseSchedulesSettings(),
                        all.getSystemSettings(), all.getSpecialSystemSettings(), all.getPhSensorSettings(), all.getEcSensorSettings(), type, time);
            }
        }
        log.info("End HydroponicSettingsService.update. {}", uuid);
        log.info("Taken: {} ms. {}", System.currentTimeMillis() - start, uuid);
    }
}

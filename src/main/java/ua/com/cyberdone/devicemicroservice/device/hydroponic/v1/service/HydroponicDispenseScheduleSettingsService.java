package ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.entity.HydroponicDispenseScheduleSettings;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.repository.HydroponicDispenseScheduleSettingsRepository;

import java.util.Optional;

@Slf4j
@Service
public record HydroponicDispenseScheduleSettingsService(
        HydroponicDispenseScheduleSettingsRepository hydroponicDispenseScheduleSettingsRepository) {

    public Optional<HydroponicDispenseScheduleSettings> findActive(String deviceUuid, Integer index) {
        long start = System.currentTimeMillis();
        log.info("[START] {} [findActive] [deviceUuid={}]", this.getClass().getCanonicalName(), deviceUuid);

        var hydroponicDispenserSettings = hydroponicDispenseScheduleSettingsRepository.findActive(deviceUuid, index);

        log.info("[END] [TAKEN:{}ms] {} [findActive] [deviceUuid={}]", System.currentTimeMillis() - start, this.getClass().getCanonicalName(), deviceUuid);
        return hydroponicDispenserSettings;
    }

    public HydroponicDispenseScheduleSettings save(HydroponicDispenseScheduleSettings data) {
        long start = System.currentTimeMillis();
        log.info("[START] {} [save] [{}]", this.getClass().getCanonicalName(), data);
        var saved = hydroponicDispenseScheduleSettingsRepository.save(data);
        log.info("[END] [TAKEN:{}ms] {} [save] [saved={}]", System.currentTimeMillis() - start, this.getClass().getCanonicalName(), saved);
        return saved;
    }

    public void update(HydroponicDispenseScheduleSettings data) {
        long start = System.currentTimeMillis();
        log.info("[START] {} [update] [{}]", this.getClass().getCanonicalName(), data);
        hydroponicDispenseScheduleSettingsRepository.save(data);
        log.info("[END] [TAKEN:{}ms] {} [update]", System.currentTimeMillis() - start, this.getClass().getCanonicalName());
    }
}

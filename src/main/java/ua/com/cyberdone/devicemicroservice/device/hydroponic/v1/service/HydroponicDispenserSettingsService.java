package ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.entity.HydroponicDispenserSettings;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.repository.HydroponicDispenserSettingsRepository;

import java.util.Optional;

@Slf4j
@Service
public record HydroponicDispenserSettingsService(
        HydroponicDispenserSettingsRepository hydroponicDispenserSettingsRepository) {

    public Optional<HydroponicDispenserSettings> findActive(String deviceUuid, Integer index) {
        long start = System.currentTimeMillis();
        log.info("[START] {} [findActive] [deviceUuid={}]", this.getClass().getCanonicalName(), deviceUuid);
        var hydroponicDispenserSettings = hydroponicDispenserSettingsRepository.findActive(deviceUuid, index);
        log.info("[END] [TAKEN:{}ms] {} [findActive] [deviceUuid={}]", System.currentTimeMillis() - start, this.getClass().getCanonicalName(), deviceUuid);
        return hydroponicDispenserSettings;
    }

    public HydroponicDispenserSettings save(HydroponicDispenserSettings data) {
        long start = System.currentTimeMillis();
        log.info("[START] {} [save] [{}]", this.getClass().getCanonicalName(), data);
        var saved = hydroponicDispenserSettingsRepository.save(data);
        log.info("[END] [TAKEN:{}ms] {} [save] [saved={}]", System.currentTimeMillis() - start, this.getClass().getCanonicalName(), saved);
        return saved;
    }

    public void update(HydroponicDispenserSettings data) {
        long start = System.currentTimeMillis();
        log.info("[START] {} [update] [{}]", this.getClass().getCanonicalName(), data);
        hydroponicDispenserSettingsRepository.save(data);
        log.info("[END] [TAKEN:{}ms] {} [update]", System.currentTimeMillis() - start, this.getClass().getCanonicalName());
    }
}

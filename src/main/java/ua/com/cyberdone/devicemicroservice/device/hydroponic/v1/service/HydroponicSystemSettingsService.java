package ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.entity.HydroponicSystemSettings;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.repository.HydroponicSystemSettingsRepository;

import java.util.Optional;

@Slf4j
@Service
public record HydroponicSystemSettingsService(
        HydroponicSystemSettingsRepository hydroponicSystemSettingsRepository) {

    public Optional<HydroponicSystemSettings> findActive(String deviceUuid) {
        long start = System.currentTimeMillis();
        log.info("[START] {} [findActive] [deviceUuid={}]", this.getClass().getCanonicalName(), deviceUuid);

        var hydroponicSystemSettings = hydroponicSystemSettingsRepository.findActive(deviceUuid);

        log.info("[END] [TAKEN:{}ms] {} [findActive] [deviceUuid={}]", System.currentTimeMillis() - start, this.getClass().getCanonicalName(), deviceUuid);
        return hydroponicSystemSettings;
    }

    public HydroponicSystemSettings save(HydroponicSystemSettings data) {
        long start = System.currentTimeMillis();
        log.info("[START] {} [save] [{}]", this.getClass().getCanonicalName(), data);
        var saved = hydroponicSystemSettingsRepository.save(data);
        log.info("[END] [TAKEN:{}ms] {} [save] [saved={}]", System.currentTimeMillis() - start, this.getClass().getCanonicalName(), saved);
        return saved;
    }

    public void update(HydroponicSystemSettings data) {
        long start = System.currentTimeMillis();
        log.info("[START] {} [update] [{}]", this.getClass().getCanonicalName(), data);
        hydroponicSystemSettingsRepository.save(data);
        log.info("[END] [TAKEN:{}ms] {} [update]", System.currentTimeMillis() - start, this.getClass().getCanonicalName());
    }
}
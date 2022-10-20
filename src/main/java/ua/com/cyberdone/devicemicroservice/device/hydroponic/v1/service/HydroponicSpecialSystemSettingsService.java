package ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.entity.HydroponicSpecialSystemSettings;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.entity.HydroponicSystemSettings;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.repository.HydroponicSpecialSystemSettingsRepository;

import java.util.Optional;

@Slf4j
@Service
public record HydroponicSpecialSystemSettingsService(
        HydroponicSpecialSystemSettingsRepository hydroponicSpecialSystemSettingsRepository) {

    public Optional<HydroponicSpecialSystemSettings> findActive(String deviceUuid) {
        long start = System.currentTimeMillis();
        log.info("[START] {} [findActive] [deviceUuid={}]", this.getClass().getCanonicalName(), deviceUuid);

        var hydroponicSpecialSystemSettings = hydroponicSpecialSystemSettingsRepository.findActive(deviceUuid);

        log.info("[END] [TAKEN:{}ms] {} [findActive] [deviceUuid={}]", System.currentTimeMillis() - start, this.getClass().getCanonicalName(), deviceUuid);
        return hydroponicSpecialSystemSettings;
    }

    public HydroponicSpecialSystemSettings save(HydroponicSpecialSystemSettings data) {
        long start = System.currentTimeMillis();
        log.info("[START] {} [save] [{}]", this.getClass().getCanonicalName(), data);
        var saved = hydroponicSpecialSystemSettingsRepository.save(data);
        log.info("[END] [TAKEN:{}ms] {} [save] [saved={}]", System.currentTimeMillis() - start, this.getClass().getCanonicalName(), saved);
        return saved;
    }

    public void update(HydroponicSpecialSystemSettings data) {
        long start = System.currentTimeMillis();
        log.info("[START] {} [update] [{}]", this.getClass().getCanonicalName(), data);
        hydroponicSpecialSystemSettingsRepository.save(data);
        log.info("[END] [TAKEN:{}ms] {} [update]", System.currentTimeMillis() - start, this.getClass().getCanonicalName());
    }
}

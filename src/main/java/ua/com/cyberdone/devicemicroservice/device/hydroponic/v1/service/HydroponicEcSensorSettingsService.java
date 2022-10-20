package ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.entity.HydroponicEcSensorSettings;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.repository.HydroponicEcSensorSettingsRepository;

import java.util.Optional;

@Slf4j
@Service
public record HydroponicEcSensorSettingsService(
        HydroponicEcSensorSettingsRepository hydroponicEcSensorSettingsRepository) {

    public Optional<HydroponicEcSensorSettings> findActive(String deviceUuid) {
        long start = System.currentTimeMillis();
        log.info("[START] {} [findActive] [deviceUuid={}]", this.getClass().getCanonicalName(), deviceUuid);

        var hydroponicEcSensorSettings = hydroponicEcSensorSettingsRepository.findActive(deviceUuid);

        log.info("[END] [TAKEN:{}ms] {} [findActive] [deviceUuid={}]", System.currentTimeMillis() - start, this.getClass().getCanonicalName(), deviceUuid);
        return hydroponicEcSensorSettings;
    }

    public HydroponicEcSensorSettings save(HydroponicEcSensorSettings data) {
        long start = System.currentTimeMillis();
        log.info("[START] {} [save] [{}]", this.getClass().getCanonicalName(), data);
        var saved = hydroponicEcSensorSettingsRepository.save(data);
        log.info("[END] [TAKEN:{}ms] {} [save] [saved={}]", System.currentTimeMillis() - start, this.getClass().getCanonicalName(), saved);
        return saved;
    }

    public void update(HydroponicEcSensorSettings data) {
        long start = System.currentTimeMillis();
        log.info("[START] {} [update] [{}]", this.getClass().getCanonicalName(), data);
        hydroponicEcSensorSettingsRepository.save(data);
        log.info("[END] [TAKEN:{}ms] {} [update]", System.currentTimeMillis() - start, this.getClass().getCanonicalName());
    }
}

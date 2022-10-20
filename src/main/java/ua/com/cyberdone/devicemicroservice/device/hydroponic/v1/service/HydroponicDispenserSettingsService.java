package ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.entity.HydroponicDispenserSettings;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.repository.HydroponicDispenserSettingsRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public record HydroponicDispenserSettingsService(
        HydroponicDispenserSettingsRepository hydroponicDispenserSettingsRepository) {

    public List<HydroponicDispenserSettings> findAll(String deviceUuid, Long page, Long limit, String type) {
        long start = System.currentTimeMillis();
        log.info("[START] {} [findAll] [deviceUuid={}]", this.getClass().getCanonicalName(), deviceUuid);

        var from = page * limit;
        var hydroponicDispenserSettingsList = hydroponicDispenserSettingsRepository.find(deviceUuid, from, limit, type);

        log.info("[END] [TAKEN:{}ms] {} [findAll] [deviceUuid={}]", System.currentTimeMillis() - start, this.getClass().getCanonicalName(), deviceUuid);
        return hydroponicDispenserSettingsList;
    }

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
//
//    public void deleteAll(String uuid) {
//        long start = System.currentTimeMillis();
//        log.info("Start HydroponicDataService.deleteAll uuid={}.", uuid);
//        dataV1Repository.deleteAllByUuid(uuid);
//        log.info("End HydroponicDataService.deleteAll uuid={}.", uuid);
//        log.info("Taken: {} ms. uuid={}", System.currentTimeMillis() - start, uuid);
//    }
//
//    public List<HydroponicData> findAllInRangeWithMinutesStep(String uuid, String fromDate, String toDate, int dataStep) {
//        long start = System.currentTimeMillis();
//        log.info("Start HydroponicDataService.findAllInRangeWithMinutesStep uuid={} fromDate={} toDate={} dataStep={}.",
//                uuid, fromDate, toDate, dataStep);
//        var dataInRange = dataV1Repository.findAllInRange(uuid, fromDate, toDate, dataStep);
//        log.info("End HydroponicDataService.findAllInRangeWithMinutesStep uuid={}.", uuid);
//        log.info("Taken: {} ms. uuid={}", System.currentTimeMillis() - start, uuid);
//        return dataInRange;
//    }
}

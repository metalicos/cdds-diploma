package ua.com.cyberdone.devicemicroservice.service.hydroponic.v1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.com.cyberdone.devicemicroservice.data.entity.hydroponic.v1.HydroponicData;
import ua.com.cyberdone.devicemicroservice.data.repository.hydroponic.v1.HydroponicDataRepository;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public record HydroponicDataService(HydroponicDataRepository dataV1Repository) {

    public List<HydroponicData> findAll(String uuid, Long page, Long limit) {
        long start = System.currentTimeMillis();
        log.info("Start HydroponicDataService.findAll. {}", uuid);
        var from = page * limit;
        var deviceMetadata = dataV1Repository.findAllByUuid(uuid, from, limit);
        log.info("End HydroponicDataService.findAll. {}", deviceMetadata);
        log.info("Taken: {} ms. DeviceMetadata={}", System.currentTimeMillis() - start, deviceMetadata);
        return deviceMetadata;
    }

    public HydroponicData save(HydroponicData data) {
        long start = System.currentTimeMillis();
        log.info("Start HydroponicDataService.save {}", data);
        dataV1Repository.save(data.getDeviceUuid(), data.getEcSolution(), data.getPhSolution(), data.getTSolution(),
                data.getTAir(), data.getHumidityAir(), data.getAtmosphericPressure(), LocalDateTime.now());
        log.info("End HydroponicDataService.save. {}", data);
        log.info("Taken: {} ms. MetadataID={}", System.currentTimeMillis() - start, data.getDeviceUuid());
        return data;
    }

    public void deleteAll(String uuid) {
        long start = System.currentTimeMillis();
        log.info("Start HydroponicDataService.deleteAll uuid={}.", uuid);
        dataV1Repository.deleteAllByUuid(uuid);
        log.info("End HydroponicDataService.deleteAll uuid={}.", uuid);
        log.info("Taken: {} ms. uuid={}", System.currentTimeMillis() - start, uuid);
    }

    public List<HydroponicData> findAllInRangeWithMinutesStep(String uuid, String fromDate, String toDate, int dataStep) {
        long start = System.currentTimeMillis();
        log.info("Start HydroponicDataService.findAllInRangeWithMinutesStep uuid={} fromDate={} toDate={} dataStep={}.",
                uuid, fromDate, toDate, dataStep);
        var dataInRange = dataV1Repository.findAllInRange(uuid, fromDate, toDate, dataStep);
        log.info("End HydroponicDataService.findAllInRangeWithMinutesStep uuid={}.", uuid);
        log.info("Taken: {} ms. uuid={}", System.currentTimeMillis() - start, uuid);
        return dataInRange;
    }
}

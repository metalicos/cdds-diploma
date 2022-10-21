package ua.com.cyberdone.devicemicroservice.device.common.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.com.cyberdone.devicemicroservice.device.common.entity.DeviceType;
import ua.com.cyberdone.devicemicroservice.device.common.repository.DeviceTypeRepository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public record DeviceTypeService(DeviceTypeRepository deviceTypeRepository) {

    public List<DeviceType> find() {
        long start = System.currentTimeMillis();
        log.info("[START] {} [findAll]", this.getClass().getCanonicalName());
        var typesList = new ArrayList<DeviceType>();
        deviceTypeRepository.findAll().forEach(typesList::add);
        log.info("[END] [TAKEN:{}ms] {} [findAll]", System.currentTimeMillis() - start, this.getClass().getCanonicalName());
        return typesList;
    }

    public DeviceType find(String type) {
        long start = System.currentTimeMillis();
        log.info("[START] {} [findAll] [type={}]", this.getClass().getCanonicalName(), type);
        var typesList = deviceTypeRepository.find(type).orElseThrow();
        log.info("[END] [TAKEN:{}ms] {} [findAll] [type={}]", System.currentTimeMillis() - start, this.getClass().getCanonicalName(), type);
        return typesList;
    }
}

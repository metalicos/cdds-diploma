package ua.com.cyberdone.devicemicroservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.com.cyberdone.devicemicroservice.data.entity.DeviceType;
import ua.com.cyberdone.devicemicroservice.data.repository.DeviceTypeRepository;
import ua.com.cyberdone.devicemicroservice.exception.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public record DeviceTypeService(DeviceTypeRepository deviceTypeRepository) {

    public List<String> findAll() throws NotFoundException {
        long start = System.currentTimeMillis();
        log.info("Start DeviceMetadataService.findAll.");
        var deviceMetadata = deviceTypeRepository.findAll().stream()
                .map(DeviceType::getType).collect(Collectors.toList());
        log.info("End DeviceMetadataService.findAll. Found={}", !deviceMetadata.isEmpty());
        log.info("Taken: {} ms. DeviceMetadata={}", System.currentTimeMillis() - start, deviceMetadata);
        return deviceMetadata;
    }
}

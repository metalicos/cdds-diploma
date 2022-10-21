package ua.com.cyberdone.devicemicroservice.device.common.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.com.cyberdone.devicemicroservice.device.common.entity.DeviceMetadata;
import ua.com.cyberdone.devicemicroservice.device.common.exception.NotFoundException;
import ua.com.cyberdone.devicemicroservice.device.common.repository.DeviceMetadataRepository;

import java.time.LocalDateTime;
import java.util.List;


@Slf4j
@Service
public record DeviceMetadataService(DeviceMetadataRepository deviceMetadataRepository) {

    public DeviceMetadata find(String uuid) throws NotFoundException {
        long start = System.currentTimeMillis();
        log.info("Start DeviceMetadataService.findByUuid. {}", uuid);
        var deviceMetadata = deviceMetadataRepository.findByUuid(uuid)
                .orElseThrow(NotFoundException::new);
        log.info("End DeviceMetadataService.findByUuid. {}", deviceMetadata);
        log.info("Taken: {} ms. DeviceMetadata={}", System.currentTimeMillis() - start, deviceMetadata);
        return deviceMetadata;
    }

    public List<DeviceMetadata> find(String tariff, Long deviceTypeId) throws NotFoundException {
        long start = System.currentTimeMillis();
        log.info("[START] {} [find] [tariff={}, deviceTypeId={}]", this.getClass().getCanonicalName(), tariff, deviceTypeId);
        var deviceMetadataList = deviceMetadataRepository.find(tariff, deviceTypeId);
        log.info("[END] [TAKEN:{}ms] {} [find] [tariff={}, deviceTypeId={}", System.currentTimeMillis() - start, this.getClass().getCanonicalName(), tariff, deviceTypeId);
        return deviceMetadataList;
    }

    public List<DeviceMetadata> findAllByDeviceType(Long deviceTypeId) {
       return deviceMetadataRepository.findAllByDeviceType(deviceTypeId);
    }

    public List<DeviceMetadata> findAll(Long ownerId) {
        long start = System.currentTimeMillis();
        log.info("Start DeviceMetadataService.findAllByOwnerId. ownerId={}", ownerId);
        var deviceMetadata = deviceMetadataRepository.find(ownerId);
        log.info("End DeviceMetadataService.findAllByOwnerId. ownerId={}", ownerId);
        log.info("Taken: {} ms. ownerId={}", System.currentTimeMillis() - start, deviceMetadata);
        return deviceMetadata;
    }

    public List<DeviceMetadata> findAll(Long ownerId, Long from, Long size) {
        long start = System.currentTimeMillis();
        log.info("Start DeviceMetadataService.findAllByOwnerId. ownerId={}", ownerId);
        var deviceMetadata = deviceMetadataRepository.find(ownerId, from, size);
        log.info("End DeviceMetadataService.findAllByOwnerId. ownerId={}", ownerId);
        log.info("Taken: {} ms. ownerId={}", System.currentTimeMillis() - start, deviceMetadata);
        return deviceMetadata;
    }

    public DeviceMetadata update(DeviceMetadata data) {
        long start = System.currentTimeMillis();
        log.info("[START] {} [update] [metadata={}]", this.getClass().getCanonicalName(), data);

        deviceMetadataRepository.findByUuid(data.getUuid()).ifPresent(metadata -> deviceMetadataRepository.save(DeviceMetadata.builder()
                .name(data.getName() == null ? metadata.getName() : data.getName())
                .description(data.getDescription() == null ? metadata.getDescription() : data.getDescription())
                .tariff(data.getTariff() == null ? metadata.getTariff() : data.getTariff())
                .delegationKey(data.getDelegationKey() == null ? metadata.getDelegationKey() : data.getDelegationKey())
                .logo(data.getLogo() == null ? metadata.getLogo() : data.getLogo())
                .updatedTimestamp(LocalDateTime.now())
                .build()));

        var saved = deviceMetadataRepository.save(data);
        log.info("[END] [TAKEN:{}ms] {} [update] [metadata={}]", System.currentTimeMillis() - start, this.getClass().getCanonicalName(), saved);
        return saved;
    }

    public DeviceMetadata save(DeviceMetadata data) {
        long start = System.currentTimeMillis();
        log.info("[START] {} [update] [metadata={}]", this.getClass().getCanonicalName(), data);
        var saved = deviceMetadataRepository.save(data);
        log.info("[END] [TAKEN:{}ms] {} [update] [metadata={}]", System.currentTimeMillis() - start, this.getClass().getCanonicalName(), saved);
        return saved;
    }

    public void linkWithOwnerDeviceMetadata(String uuid, Long ownerId) {
        long start = System.currentTimeMillis();
        log.info("Start DeviceMetadataService.linkWithOwnerDeviceMetadata uuid={}. ownerId={}", uuid, ownerId);
        if (deviceMetadataRepository.hasOwner(uuid)) {
            log.info("Taken: {} ms. uuid={}", System.currentTimeMillis() - start, uuid);
            throw new RuntimeException("Forbidden. Owner is already set.");
        }
        deviceMetadataRepository.linkWithOwner(uuid, ownerId);
        log.info("End DeviceMetadataService.linkWithOwnerDeviceMetadata uuid={}. ownerId={}", uuid, ownerId);
        log.info("Taken: {} ms. uuid={}", System.currentTimeMillis() - start, uuid);
    }

    public void unlinkWithOwnerDeviceMetadata(String uuid) {
        long start = System.currentTimeMillis();
        log.info("Start DeviceMetadataService.unlinkWithOwnerDeviceMetadata uuid={}.", uuid);
        deviceMetadataRepository.unlinkWithOwner(uuid);
        log.info("End DeviceMetadataService.unlinkWithOwnerDeviceMetadata uuid={}.", uuid);
        log.info("Taken: {} ms. uuid={}", System.currentTimeMillis() - start, uuid);
    }

    public void deleteByUuid(String uuid) {
        long start = System.currentTimeMillis();
        log.info("Start DeviceMetadataService.deleteByUuid uuid={}.", uuid);
        deviceMetadataRepository.deleteByUuid(uuid);
        log.info("End DeviceMetadataService.deleteByUuid uuid={}.", uuid);
        log.info("Taken: {} ms. uuid={}", System.currentTimeMillis() - start, uuid);
    }
}

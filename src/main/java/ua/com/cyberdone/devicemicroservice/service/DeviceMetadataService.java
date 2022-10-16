package ua.com.cyberdone.devicemicroservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ua.com.cyberdone.devicemicroservice.data.entity.DeviceMetadata;
import ua.com.cyberdone.devicemicroservice.data.repository.DeviceMetadataRepository;
import ua.com.cyberdone.devicemicroservice.exception.NotFoundException;
import ua.com.cyberdone.devicemicroservice.util.ImageStandards;
import ua.com.cyberdone.devicemicroservice.util.ImageUtils;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.SQLException;
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

    public List<DeviceMetadata> findAll(Long ownerId) {
        long start = System.currentTimeMillis();
        log.info("Start DeviceMetadataService.findAllByOwnerId. ownerId={}", ownerId);
        var deviceMetadata = deviceMetadataRepository.findAllByOwnerId(ownerId);
        log.info("End DeviceMetadataService.findAllByOwnerId. ownerId={}", ownerId);
        log.info("Taken: {} ms. ownerId={}", System.currentTimeMillis() - start, deviceMetadata);
        return deviceMetadata;
    }

    public List<DeviceMetadata> findAll(Long ownerId, Long from, Long size) {
        long start = System.currentTimeMillis();
        log.info("Start DeviceMetadataService.findAllByOwnerId. ownerId={}", ownerId);
        var deviceMetadata = deviceMetadataRepository.findAllByOwnerId(ownerId, from, size);
        log.info("End DeviceMetadataService.findAllByOwnerId. ownerId={}", ownerId);
        log.info("Taken: {} ms. ownerId={}", System.currentTimeMillis() - start, deviceMetadata);
        return deviceMetadata;
    }

    public DeviceMetadata update(String uuid, String name, String description) {
        long start = System.currentTimeMillis();
        log.info("Start DeviceMetadataService.update. uuid={} name={} description={}", uuid, name, description);
        deviceMetadataRepository.updateDeviceMetadata(uuid, name, description);
        var metadata = deviceMetadataRepository.findByUuid(uuid).orElseThrow();
        log.info("End DeviceMetadataService.update. {}", metadata);
        log.info("Taken: {} ms. MetadataID={}", System.currentTimeMillis() - start, metadata.getUuid());
        return metadata;
    }

    public DeviceMetadata update(String uuid, MultipartFile deviceImage) throws NotFoundException, IOException, SQLException {
        long start = System.currentTimeMillis();
        log.info("Start DeviceMetadataService.update LOGO. uuid={}", uuid);

        var meta = deviceMetadataRepository.findByUuid(uuid).orElseThrow(
                () -> new NotFoundException("Device Metadata Not Found for uuid=" + uuid));

        deviceMetadataRepository.updateDeviceMetadata(uuid, new SerialBlob(deviceImage.getBytes()));

        var lowerQualityLogo = ImageUtils.getScaledBase64OrElseOriginal(deviceImage.getBytes(), ImageStandards.DEVICE_CARD);
        meta.setLogo(new SerialBlob(lowerQualityLogo.getBytes()));

        log.info("End DeviceMetadataService.update. {}", meta);
        log.info("Taken: {} ms. MetadataID={}", System.currentTimeMillis() - start, meta.getUuid());
        return meta;
    }

    public DeviceMetadata saveMetadata(DeviceMetadata metadata) {
        long start = System.currentTimeMillis();
        log.info("Start DeviceMetadataService.saveMetadata. {}", metadata);
        deviceMetadataRepository.save(metadata.getUuid(), metadata.getName(), metadata.getDescription(), metadata.getOwnerId(),
                metadata.getDelegationKey(), metadata.getLogo(), metadata.getDeviceTypeId(), LocalDateTime.now());
        log.info("End DeviceMetadataService.saveMetadata. {}", metadata);
        log.info("Taken: {} ms. MetadataID={}", System.currentTimeMillis() - start, metadata.getUuid());
        return metadata;
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

package ua.com.cyberdone.devicemicroservice.device.common.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.com.cyberdone.devicemicroservice.device.common.entity.DeviceMetadata;
import ua.com.cyberdone.devicemicroservice.device.common.entity.DeviceType;
import ua.com.cyberdone.devicemicroservice.device.common.exception.NotFoundException;
import ua.com.cyberdone.devicemicroservice.device.common.model.UiDeviceMetadata;
import ua.com.cyberdone.devicemicroservice.device.common.repository.DeviceMetadataRepository;
import ua.com.cyberdone.devicemicroservice.device.common.util.ImageStandards;
import ua.com.cyberdone.devicemicroservice.device.common.util.ImageUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
public record DeviceMetadataService(DeviceMetadataRepository deviceMetadataRepository, DeviceTypeService deviceTypeService) {

    public UiDeviceMetadata findMetadataByUuid(String uuid) throws NotFoundException {
        log.info("[START] {} [findMetadataByUuid] [uuid={}]", this.getClass().getCanonicalName(), uuid);
        long start = System.currentTimeMillis();
        var deviceTypes = deviceTypeService.find();
        var deviceMetadata = deviceMetadataRepository.findByUuid(uuid)
                .map(meta -> toUiDeviceMetadata(meta, deviceTypes))
                .orElseThrow(NotFoundException::new);
        log.info("[END] [TAKEN:{}ms] {} [findMetadataByUuid] [uuid={}]", System.currentTimeMillis() - start, this.getClass().getCanonicalName(), uuid);
        return deviceMetadata;
    }

    public List<UiDeviceMetadata> findAllByDeviceType(Long deviceTypeId) {
        long start = System.currentTimeMillis();
        log.info("[START] {} [findAllByDeviceType] [deviceTypeId={}]", this.getClass().getCanonicalName(), deviceTypeId);
        var deviceTypes = deviceTypeService.find();
        var metadataList = deviceMetadataRepository.findAllByDeviceType(deviceTypeId)
                .stream().map(meta -> toUiDeviceMetadata(meta, deviceTypes)).collect(Collectors.toList());
        log.info("[END] [TAKEN:{}ms] {} [findAllByDeviceType] [deviceTypeId={}]", System.currentTimeMillis() - start, this.getClass().getCanonicalName(), deviceTypeId);
        return metadataList;
    }

    public List<UiDeviceMetadata> findAll(Long ownerId) {
        log.info("[START] {} [findAll] [ownerId={}]", this.getClass().getCanonicalName(), ownerId);
        long start = System.currentTimeMillis();
        var deviceTypes = deviceTypeService.find();
        var deviceMetadata = deviceMetadataRepository.find(ownerId)
                .stream().map(meta -> toUiDeviceMetadata(meta, deviceTypes)).collect(Collectors.toList());
        log.info("[END] [TAKEN:{}ms] {} [findAll] [ownerId={}, deviceMetadata={}]", System.currentTimeMillis() - start, this.getClass().getCanonicalName(), ownerId, deviceMetadata);
        return deviceMetadata;
    }

    public DeviceMetadata update(DeviceMetadata data) {
        long start = System.currentTimeMillis();
        log.info("[START] {} [update] [metadata={}]", this.getClass().getCanonicalName(), data);

        var saved = deviceMetadataRepository.findByUuid(data.getUuid())
                .map(metadata -> deviceMetadataRepository.save(DeviceMetadata.builder()
                        .id(metadata.getId())
                        .uuid(metadata.getUuid())
                        .name(data.getName() == null ? metadata.getName() : data.getName())
                        .description(data.getDescription() == null ? metadata.getDescription() : data.getDescription())
                        .tariff(data.getTariff() == null ? metadata.getTariff() : data.getTariff())
                        .ownerId(data.getOwnerId() == null ? metadata.getOwnerId() : data.getOwnerId())
                        .delegationKey(data.getDelegationKey() == null ? metadata.getDelegationKey() : data.getDelegationKey())
                        .logo(data.getLogo() == null ? metadata.getLogo() : data.getLogo())
                        .deviceTypeId(data.getDeviceTypeId() == null ? metadata.getDeviceTypeId() : data.getDeviceTypeId())
                        .createdTimestamp(metadata.getCreatedTimestamp())
                        .updatedTimestamp(LocalDateTime.now())
                        .build())).orElse(null);

        log.info("[END] [TAKEN:{}ms] {} [update] [metadata={}]", System.currentTimeMillis() - start, this.getClass().getCanonicalName(), saved);
        return saved;
    }

    public DeviceMetadata save(DeviceMetadata data) {
        long start = System.currentTimeMillis();
        log.info("[START] {} [save] [data={}]", this.getClass().getCanonicalName(), data);
        var saved = deviceMetadataRepository.save(data);
        log.info("[END] [TAKEN:{}ms] {} [save] [saved={}]", System.currentTimeMillis() - start, this.getClass().getCanonicalName(), saved);
        return saved;
    }

    public void linkWithOwnerDeviceMetadata(String uuid, Long ownerId) {
        long start = System.currentTimeMillis();
        log.info("[START] {} [linkWithOwnerDeviceMetadata] [uuid={} ownerId={}]", this.getClass().getCanonicalName(), uuid, ownerId);
        if (deviceMetadataRepository.hasOwner(uuid)) {
            log.info("[FORBIDDEN] [TAKEN:{}ms] {} [linkWithOwnerDeviceMetadata] [Owner is already set.]", System.currentTimeMillis() - start, this.getClass().getCanonicalName());
            return;
        }
        deviceMetadataRepository.linkWithOwner(uuid, ownerId);
        log.info("[END] [TAKEN:{}ms] {} [linkWithOwnerDeviceMetadata] [linked to ownerId={}]", System.currentTimeMillis() - start, this.getClass().getCanonicalName(), ownerId);
    }

    public void unlinkWithOwnerDeviceMetadata(String uuid) {
        long start = System.currentTimeMillis();
        log.info("[START] {} [unlinkWithOwnerDeviceMetadata] [uuid={}]", this.getClass().getCanonicalName(), uuid);
        deviceMetadataRepository.unlinkWithOwner(uuid);
        log.info("[END] [TAKEN:{}ms] {} [unlinkWithOwnerDeviceMetadata] [unlinked uuid={}]", System.currentTimeMillis() - start, this.getClass().getCanonicalName(), uuid);
    }

    public void deleteByUuid(String uuid) {
        long start = System.currentTimeMillis();
        log.info("[START] {} [deleteByUuid] [uuid={}]", this.getClass().getCanonicalName(), uuid);
        deviceMetadataRepository.deleteByUuid(uuid);
        log.info("[END] [TAKEN:{}ms] {} [deleteByUuid] [uuid={}]", System.currentTimeMillis() - start, this.getClass().getCanonicalName(), uuid);
    }

    private UiDeviceMetadata toUiDeviceMetadata(DeviceMetadata metadata, List<DeviceType> deviceTypes){
        return UiDeviceMetadata.builder()
                .id(metadata.getId())
                .uuid(metadata.getUuid())
                .name(metadata.getName())
                .description(metadata.getDescription())
                .tariff(metadata.getTariff())
                .ownerId(metadata.getOwnerId())
                .delegationKey(metadata.getDelegationKey())
                .logo(ImageUtils.getScaledBase64OrElseOriginal(metadata.getLogo(), ImageStandards.DEVICE_CARD))
                .deviceType(deviceTypes.stream()
                        .filter(type->type.getId().equals(metadata.getDeviceTypeId()))
                        .findFirst()
                        .orElse(new DeviceType(0L,"NOT_FOUND"))
                        .getType())
                .createdTimestamp(metadata.getCreatedTimestamp())
                .updatedTimestamp(metadata.getUpdatedTimestamp())
                .build();
    }
}

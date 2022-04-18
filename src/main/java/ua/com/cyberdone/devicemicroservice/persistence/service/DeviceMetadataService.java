package ua.com.cyberdone.devicemicroservice.persistence.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ua.com.cyberdone.devicemicroservice.exception.AlreadyExistException;
import ua.com.cyberdone.devicemicroservice.exception.NotFoundException;
import ua.com.cyberdone.devicemicroservice.persistence.entity.DeviceMetadata;
import ua.com.cyberdone.devicemicroservice.persistence.model.DeviceMetadataDto;
import ua.com.cyberdone.devicemicroservice.persistence.model.SaveDeviceMetadataDto;
import ua.com.cyberdone.devicemicroservice.persistence.repository.DeviceMetadataRepository;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeviceMetadataService {
    private final ModelMapper modelMapper;
    private final DeviceMetadataRepository deviceMetadataRepository;

    @Transactional
    public DeviceMetadataDto saveMetadata(SaveDeviceMetadataDto dto) throws AlreadyExistException {
        if (!deviceMetadataRepository.existsByUuid(dto.getUuid())) {
            var saved = deviceMetadataRepository.save(modelMapper.map(dto, DeviceMetadata.class));
            var savedDto = modelMapper.map(deviceMetadataRepository.save(saved), DeviceMetadataDto.class);
            savedDto.setDeviceImage(imageBytesToBase64(saved.getDeviceImage()));
            return savedDto;
        }
        throw new AlreadyExistException("Device already exists. chose another UUID.");
    }

    @Transactional
    public DeviceMetadataDto updateMetadata(String uuid, String name, String description) throws IOException, NotFoundException {
        var meta = deviceMetadataRepository.findByUuid(uuid).orElseThrow(
                () -> new NotFoundException("Device Metadata Not Found for uuid=" + uuid));
        meta.setName(name);
        meta.setDescription(description);
        return packMetadataWithImage(deviceMetadataRepository.save(meta));
    }

    public DeviceMetadataDto getMetadataByUuid(String uuid) throws NotFoundException {
        return modelMapper.map(deviceMetadataRepository.findByUuid(uuid).orElseThrow(() ->
                new NotFoundException("Device with UUID='" + uuid + "' is not found.")), DeviceMetadataDto.class);
    }

    public List<DeviceMetadataDto> getMetadataByUser(Long userId) {
        System.gc();
        return deviceMetadataRepository.findAllByUserId(userId).stream()
                .map(this::packMetadataWithImage)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteMetadata(String uuid) {
        deviceMetadataRepository.deleteByUuid(uuid);
    }

    @Transactional
    public void unlinkMetadataFromUser(String uuid) throws NotFoundException, AlreadyExistException {
        if (!deviceMetadataRepository.existsByUuid(uuid)) {
            throw new NotFoundException("Sorry, but device with UUID='" + uuid + "' is not found. Make sure you made no mistakes.");
        }
        if (!deviceMetadataRepository.isMetadataLinkedToAccount(uuid)) {
            throw new AlreadyExistException("This device is already removed from account.");
        }
        deviceMetadataRepository.unlinkDeviceMetadataFromUser(uuid);
    }

    @Transactional
    public void linkMetadataToUser(String uuid, long userId) throws NotFoundException, AlreadyExistException {
        if (!deviceMetadataRepository.existsByUuid(uuid)) {
            throw new NotFoundException("Sorry, but device with UUID='" + uuid + "' is not found. Make sure you made no mistakes.");
        }
        if (deviceMetadataRepository.isMetadataLinkedToAccount(uuid)) {
            throw new AlreadyExistException("This device is already added to account.");
        }
        deviceMetadataRepository.linkDeviceMetadataToUser(uuid, userId);
    }

    @Transactional
    public DeviceMetadataDto updateDeviceImage(String uuid, MultipartFile deviceImage) throws NotFoundException, IOException {
        var meta = deviceMetadataRepository.findByUuid(uuid).orElseThrow(
                () -> new NotFoundException("Device Metadata Not Found for uuid=" + uuid));
        meta.setDeviceImage(deviceImage.getBytes());
        var dto = modelMapper.map(deviceMetadataRepository.save(meta), DeviceMetadataDto.class);
        dto.setDeviceImage(imageBytesToBase64(deviceImage.getBytes()));
        return dto;
    }

    private static String imageBytesToBase64(byte[] image) {
        return image == null ? null : Base64.getEncoder().encodeToString(image);
    }

    private DeviceMetadataDto packMetadataWithImage(DeviceMetadata deviceMetadata){
        var deviceMetadataDto = modelMapper.map(deviceMetadata, DeviceMetadataDto.class);
        deviceMetadataDto.setDeviceImage(imageBytesToBase64(deviceMetadata.getDeviceImage()));
        return deviceMetadataDto;
    }
}

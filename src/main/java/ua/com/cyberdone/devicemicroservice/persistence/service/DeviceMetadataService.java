package ua.com.cyberdone.devicemicroservice.persistence.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ua.com.cyberdone.devicemicroservice.exception.AlreadyExistException;
import ua.com.cyberdone.devicemicroservice.exception.NotFoundException;
import ua.com.cyberdone.devicemicroservice.model.dto.DeviceMetadataDto;
import ua.com.cyberdone.devicemicroservice.model.dto.SaveDeviceMetadataDto;
import ua.com.cyberdone.devicemicroservice.persistence.entity.DeviceMetadata;
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

    private static final Converter<byte[], String> IMAGE_TO_BASE64 = context ->
            context.getSource() == null ? null : Base64.getEncoder().encodeToString(context.getSource());

    @Transactional
    public DeviceMetadataDto saveMetadata(SaveDeviceMetadataDto dto) {
        if (!deviceMetadataRepository.existsByUuid(dto.getUuid())) {
            var saved = deviceMetadataRepository.save(modelMapper.map(dto, DeviceMetadata.class));
            modelMapper.addConverter(IMAGE_TO_BASE64);
            return modelMapper.map(deviceMetadataRepository.save(saved), DeviceMetadataDto.class);
        }
        throw new AlreadyExistException("Device already exists. chose another UUID.");
    }

    @Transactional
    public DeviceMetadataDto updateMetadata(String uuid, String name, String description, MultipartFile image) throws IOException {
        var meta = deviceMetadataRepository.findByUuid(uuid).orElseThrow(
                () -> new NotFoundException("Device Metadata Not Found for uuid=" + uuid));
        meta.setName(name);
        meta.setDescription(description);
        meta.setDeviceImage(image.getBytes());
        modelMapper.addConverter(IMAGE_TO_BASE64);
        return modelMapper.map(deviceMetadataRepository.save(meta), DeviceMetadataDto.class);
    }

    public DeviceMetadataDto getMetadataByUuid(String uuid) {
        return modelMapper.map(deviceMetadataRepository.findByUuid(uuid)
                .orElse(new DeviceMetadata()), DeviceMetadataDto.class);
    }

    public List<DeviceMetadataDto> getMetadataByUser(Long userId) {
        System.gc();
        return deviceMetadataRepository.findAllByUserId(userId).stream()
                .map(metadata -> modelMapper.map(metadata, DeviceMetadataDto.class))
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
}

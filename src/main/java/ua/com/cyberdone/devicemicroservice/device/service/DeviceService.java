package ua.com.cyberdone.devicemicroservice.device.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import ua.com.cyberdone.devicemicroservice.device.domain.Device;
import ua.com.cyberdone.devicemicroservice.device.model.DeviceDTO;
import ua.com.cyberdone.devicemicroservice.device.model.DeviceType;
import ua.com.cyberdone.devicemicroservice.device.repos.DeviceRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeviceService implements ModelEntityMapper<Device, DeviceDTO> {
    private final DeviceRepository deviceRepository;
    private final DeviceDelegateSecretService deviceDelegateSecretService;

    public Page<DeviceDTO> findAll(Pageable pageable) {
        return new PageImpl<>(deviceRepository.findAll(pageable)
                .stream().map(device -> mapToDTO(device, new DeviceDTO()))
                .collect(Collectors.toList()));
    }

    public DeviceDTO findByUuid(String uuid) {
        return deviceRepository.findByUuid(uuid)
                .map(device -> mapToDTO(device, new DeviceDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public List<DeviceDTO> findAllByType(DeviceType deviceType) {
        return deviceRepository.findAllByType(deviceType)
                .stream()
                .map(device -> mapToDTO(device, new DeviceDTO()))
                .collect(Collectors.toList());
    }

    public DeviceDTO get(final Long id) {
        return deviceRepository.findById(id)
                .map(device -> mapToDTO(device, new DeviceDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public DeviceDTO create(final DeviceDTO deviceDTO) {
        final Device device = new Device();
        mapToEntity(deviceDTO, device);
        return mapToDTO(deviceRepository.save(device), new DeviceDTO());
    }

    public DeviceDTO update(final String uuid, final DeviceDTO deviceDTO) {
        final Device device = deviceRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(deviceDTO, device);
        return mapToDTO(deviceRepository.save(device), new DeviceDTO());
    }

    public void delete(final Long id) {
        deviceRepository.deleteById(id);
    }

    public boolean uuidExists(final String uuid) {
        return deviceRepository.existsByUuid(uuid);
    }

    public Page<DeviceDTO> findByOwnerId(Long ownerId) {
        return new PageImpl<>(deviceRepository.findAllByOwnerId(ownerId).stream()
                .map(device -> mapToDTO(device, new DeviceDTO()))
                .collect(Collectors.toList()));
    }

    @Transactional
    public void unlinkOwner(String uuid, Long userId) {
        var device = deviceRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (device.getOwnerId() != null && device.getOwnerId().equals(userId)) {
            device.setOwnerId(null);
            deviceRepository.save(device);
            log.info("Device owner={} unlinked device={}.", userId, uuid);
            deviceDelegateSecretService.deleteAllDelegatedSecretsByDeviceId(device.getId());
            log.info("All delegations for device={} are deleted.", uuid);
        }
    }

    public void linkOwner(String uuid, Long userId) {
        var device = deviceRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (device.getOwnerId() != null) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Device=" + device.getUuid() + " is already linked to user=" + device.getOwnerId());
        }

        device.setOwnerId(userId);
        deviceRepository.save(device);
    }

    @Override
    public DeviceDTO mapToDTO(final Device device, final DeviceDTO deviceDTO) {
        deviceDTO.setId(device.getId());
        deviceDTO.setName(device.getName());
        deviceDTO.setDescription(device.getDescription());
        deviceDTO.setType(device.getType());
        deviceDTO.setUuid(device.getUuid());
        deviceDTO.setOwnerId(device.getOwnerId());
        deviceDTO.setDelegatedAccountsNumber(device.getDelegatedAccountsNumber());
        deviceDTO.setDelegatable(device.getDelegatable());
        deviceDTO.setRepairedAmountNumber(device.getRepairedAmountNumber());
        deviceDTO.setCreatedTimestamp(device.getCreatedTimestamp());
        deviceDTO.setLastRepairedTimestamp(device.getLastRepairedTimestamp());
        return deviceDTO;
    }

    @Override
    public Device mapToEntity(final DeviceDTO deviceDTO, final Device device) {
        device.setName(deviceDTO.getName());
        device.setDescription(deviceDTO.getDescription());
        device.setType(deviceDTO.getType());
        device.setUuid(deviceDTO.getUuid());
        device.setOwnerId(deviceDTO.getOwnerId());
        device.setDelegatedAccountsNumber(deviceDTO.getDelegatedAccountsNumber());
        device.setDelegatable(deviceDTO.getDelegatable());
        device.setRepairedAmountNumber(deviceDTO.getRepairedAmountNumber());
        device.setCreatedTimestamp(deviceDTO.getCreatedTimestamp());
        device.setLastRepairedTimestamp(deviceDTO.getLastRepairedTimestamp());
        return device;
    }
}

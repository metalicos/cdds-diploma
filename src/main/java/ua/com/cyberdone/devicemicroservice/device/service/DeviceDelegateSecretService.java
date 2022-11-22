package ua.com.cyberdone.devicemicroservice.device.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ua.com.cyberdone.devicemicroservice.device.domain.Device;
import ua.com.cyberdone.devicemicroservice.device.domain.DeviceDelegateSecret;
import ua.com.cyberdone.devicemicroservice.device.model.DeviceDelegateSecretDTO;
import ua.com.cyberdone.devicemicroservice.device.repos.DeviceDelegateSecretRepository;
import ua.com.cyberdone.devicemicroservice.device.repos.DeviceRepository;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class DeviceDelegateSecretService implements ModelEntityMapper<DeviceDelegateSecret, DeviceDelegateSecretDTO> {
    private final DeviceDelegateSecretRepository deviceDelegateSecretRepository;
    private final DeviceRepository deviceRepository;

    public List<DeviceDelegateSecretDTO> findAll() {
        return deviceDelegateSecretRepository.findAll(Sort.by("id"))
                .stream()
                .map(deviceDelegateSecret -> mapToDTO(deviceDelegateSecret, new DeviceDelegateSecretDTO()))
                .collect(Collectors.toList());
    }

    public DeviceDelegateSecretDTO get(final Long id) {
        return deviceDelegateSecretRepository.findById(id)
                .map(deviceDelegateSecret -> mapToDTO(deviceDelegateSecret, new DeviceDelegateSecretDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final DeviceDelegateSecretDTO deviceDelegateSecretDTO) {
        final DeviceDelegateSecret deviceDelegateSecret = new DeviceDelegateSecret();
        mapToEntity(deviceDelegateSecretDTO, deviceDelegateSecret);
        return deviceDelegateSecretRepository.save(deviceDelegateSecret).getId();
    }

    public void update(final Long id, final DeviceDelegateSecretDTO deviceDelegateSecretDTO) {
        final DeviceDelegateSecret deviceDelegateSecret = deviceDelegateSecretRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(deviceDelegateSecretDTO, deviceDelegateSecret);
        deviceDelegateSecretRepository.save(deviceDelegateSecret);
    }

    public void delete(final Long id) {
        deviceDelegateSecretRepository.deleteById(id);
    }

    @Override
    public DeviceDelegateSecretDTO mapToDTO(final DeviceDelegateSecret deviceDelegateSecret,
                                            final DeviceDelegateSecretDTO deviceDelegateSecretDTO) {
        deviceDelegateSecretDTO.setId(deviceDelegateSecret.getId());
        deviceDelegateSecretDTO.setSecret(deviceDelegateSecret.getSecret());
        deviceDelegateSecretDTO.setAccountId(deviceDelegateSecret.getAccountId());
        deviceDelegateSecretDTO.setDeviceId(deviceDelegateSecret.getDevice() == null ? null : deviceDelegateSecret.getDevice().getId());
        return deviceDelegateSecretDTO;
    }

    @Override
    public DeviceDelegateSecret mapToEntity(final DeviceDelegateSecretDTO deviceDelegateSecretDTO,
                                            final DeviceDelegateSecret deviceDelegateSecret) {
        deviceDelegateSecret.setSecret(deviceDelegateSecretDTO.getSecret());
        deviceDelegateSecret.setAccountId(deviceDelegateSecretDTO.getAccountId());
        final Device device = deviceDelegateSecretDTO.getDeviceId() == null ? null : deviceRepository.findById(deviceDelegateSecretDTO.getDeviceId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "device not found"));
        deviceDelegateSecret.setDevice(device);
        return deviceDelegateSecret;
    }

    public void deleteAllDelegatedSecretsByDeviceId(Long deviceId) {
        deviceDelegateSecretRepository.deleteAllByDeviceId(deviceId);
    }
}

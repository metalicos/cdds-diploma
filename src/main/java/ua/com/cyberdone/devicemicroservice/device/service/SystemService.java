package ua.com.cyberdone.devicemicroservice.device.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ua.com.cyberdone.devicemicroservice.device.domain.Device;
import ua.com.cyberdone.devicemicroservice.device.model.SystemDTO;
import ua.com.cyberdone.devicemicroservice.device.repos.DeviceRepository;
import ua.com.cyberdone.devicemicroservice.device.repos.SystemRepository;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class SystemService implements ModelEntityMapper<ua.com.cyberdone.devicemicroservice.device.domain.System, SystemDTO> {
    private final SystemRepository systemRepository;
    private final DeviceRepository deviceRepository;

    public List<SystemDTO> findAll() {
        return systemRepository.findAll(Sort.by("id"))
                .stream()
                .map(system -> mapToDTO(system, new SystemDTO()))
                .collect(Collectors.toList());
    }

    public SystemDTO get(final Long id) {
        return systemRepository.findById(id)
                .map(system -> mapToDTO(system, new SystemDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final SystemDTO systemDTO) {
        var system = new ua.com.cyberdone.devicemicroservice.device.domain.System();
        mapToEntity(systemDTO, system);
        return systemRepository.save(system).getId();
    }

    public void update(final Long id, final SystemDTO systemDTO) {
        final ua.com.cyberdone.devicemicroservice.device.domain.System system = systemRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(systemDTO, system);
        systemRepository.save(system);
    }

    public void delete(final Long id) {
        systemRepository.deleteById(id);
    }

    @Override
    public SystemDTO mapToDTO(final ua.com.cyberdone.devicemicroservice.device.domain.System system, final SystemDTO systemDTO) {
        systemDTO.setId(system.getId());
        systemDTO.setUpdatedTimestamp(system.getUpdatedTimestamp());
        systemDTO.setDeviceUuid(system.getDevice() == null ? null : system.getDevice().getUuid());
        return systemDTO;
    }

    @Override
    public ua.com.cyberdone.devicemicroservice.device.domain.System mapToEntity(
            final SystemDTO systemDTO, final ua.com.cyberdone.devicemicroservice.device.domain.System system) {
        system.setUpdatedTimestamp(systemDTO.getUpdatedTimestamp());
        final Device device = systemDTO.getDeviceUuid() == null ? null : deviceRepository.findByUuid(systemDTO.getDeviceUuid())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "device not found"));
        system.setDevice(device);
        return system;
    }
}

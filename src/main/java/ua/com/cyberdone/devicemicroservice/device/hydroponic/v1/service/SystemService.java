package ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ua.com.cyberdone.devicemicroservice.device.domain.Device;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.domain.System;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.model.SystemDTO;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.model.SystemTemplateDTO;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.repos.SystemRepository;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.repos.SystemTemplateRepository;
import ua.com.cyberdone.devicemicroservice.device.repos.DeviceRepository;
import ua.com.cyberdone.devicemicroservice.device.service.ModelEntityMapper;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class SystemService implements ModelEntityMapper<System, SystemDTO> {
    private final SystemRepository systemRepository;
    private final SystemTemplateRepository systemTemplateRepository;
    private final SystemTemplateService systemTemplateService;
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

    public SystemDTO create(final SystemDTO systemDTO) {
        var system = new System();
        mapToEntity(systemDTO, system);
        return mapToDTO(systemRepository.save(system), new SystemDTO());
    }

    public SystemDTO update(final Long id, final SystemDTO systemDTO) {
        final System system = systemRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(systemDTO, system);
        return mapToDTO(systemRepository.save(system), new SystemDTO());
    }

    public void delete(final Long id) {
        systemRepository.deleteById(id);
    }

    public SystemDTO findByDeviceUuid(String uuid) {
        return systemRepository.findByDeviceUuid(uuid)
                .map(system -> mapToDTO(system, new SystemDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "device not found"));
    }

    @Override
    public SystemDTO mapToDTO(final System system, final SystemDTO systemDTO) {
        systemDTO.setId(system.getId());
        systemDTO.setUpdatedTimestamp(system.getUpdatedTimestamp());
        systemDTO.setDeviceUuid(system.getDevice() == null ? null : system.getDevice().getUuid());
        systemDTO.setSystemTemplateDTO(system.getSystemTemplate() == null ?
                null : systemTemplateService.mapToDTO(system.getSystemTemplate(), new SystemTemplateDTO()));
        return systemDTO;
    }

    @Override
    public System mapToEntity(final SystemDTO systemDTO, final System system) {
        system.setUpdatedTimestamp(systemDTO.getUpdatedTimestamp());
        final Device device = systemDTO.getDeviceUuid() == null ? null : deviceRepository.findByUuid(systemDTO.getDeviceUuid())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "device not found"));
        system.setDevice(device);
        system.setSystemTemplate(systemDTO.getSystemTemplateDTO() == null ? null : systemTemplateRepository.findById(systemDTO.getSystemTemplateDTO().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        return system;
    }
}

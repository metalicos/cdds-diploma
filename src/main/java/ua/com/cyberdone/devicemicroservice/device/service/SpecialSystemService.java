package ua.com.cyberdone.devicemicroservice.device.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ua.com.cyberdone.devicemicroservice.device.domain.Device;
import ua.com.cyberdone.devicemicroservice.device.domain.SpecialSystem;
import ua.com.cyberdone.devicemicroservice.device.model.SpecialSystemDTO;
import ua.com.cyberdone.devicemicroservice.device.repos.DeviceRepository;
import ua.com.cyberdone.devicemicroservice.device.repos.SpecialSystemRepository;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class SpecialSystemService implements ModelEntityMapper<SpecialSystem, SpecialSystemDTO> {
    private final SpecialSystemRepository specialSystemRepository;
    private final DeviceRepository deviceRepository;


    public List<SpecialSystemDTO> findAll() {
        return specialSystemRepository.findAll(Sort.by("id"))
                .stream()
                .map(specialSystem -> mapToDTO(specialSystem, new SpecialSystemDTO()))
                .collect(Collectors.toList());
    }

    public SpecialSystemDTO get(final Long id) {
        return specialSystemRepository.findById(id)
                .map(specialSystem -> mapToDTO(specialSystem, new SpecialSystemDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final SpecialSystemDTO specialSystemDTO) {
        final SpecialSystem specialSystem = new SpecialSystem();
        mapToEntity(specialSystemDTO, specialSystem);
        return specialSystemRepository.save(specialSystem).getId();
    }

    public void update(final Long id, final SpecialSystemDTO specialSystemDTO) {
        final SpecialSystem specialSystem = specialSystemRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(specialSystemDTO, specialSystem);
        specialSystemRepository.save(specialSystem);
    }

    public void delete(final Long id) {
        specialSystemRepository.deleteById(id);
    }

    @Override
    public SpecialSystemDTO mapToDTO(final SpecialSystem specialSystem,
                                     final SpecialSystemDTO specialSystemDTO) {
        specialSystemDTO.setId(specialSystem.getId());
        specialSystemDTO.setTime(specialSystem.getTime());
        specialSystemDTO.setSoftwareVersion(specialSystem.getSoftwareVersion());
        specialSystemDTO.setScheduleAmount(specialSystem.getScheduleAmount());
        specialSystemDTO.setDispensersAmount(specialSystem.getDispensersAmount());
        specialSystemDTO.setWifiRssi(specialSystem.getWifiRssi());
        specialSystemDTO.setWifiBsid(specialSystem.getWifiBsid());
        specialSystemDTO.setLocalIp(specialSystem.getLocalIp());
        specialSystemDTO.setSubnetMask(specialSystem.getSubnetMask());
        specialSystemDTO.setGatewayIp(specialSystem.getGatewayIp());
        specialSystemDTO.setMacAddr(specialSystem.getMacAddr());
        specialSystemDTO.setCreatedTimestamp(specialSystem.getCreatedTimestamp());
        specialSystemDTO.setUpdatedTimestamp(specialSystem.getUpdatedTimestamp());
        specialSystemDTO.setDeviceUuid(specialSystem.getDevice() == null ? null : specialSystem.getDevice().getUuid());
        return specialSystemDTO;
    }

    @Override
    public SpecialSystem mapToEntity(final SpecialSystemDTO specialSystemDTO,
                                     final SpecialSystem specialSystem) {
        specialSystem.setTime(specialSystemDTO.getTime());
        specialSystem.setSoftwareVersion(specialSystemDTO.getSoftwareVersion());
        specialSystem.setScheduleAmount(specialSystemDTO.getScheduleAmount());
        specialSystem.setDispensersAmount(specialSystemDTO.getDispensersAmount());
        specialSystem.setWifiRssi(specialSystemDTO.getWifiRssi());
        specialSystem.setWifiBsid(specialSystemDTO.getWifiBsid());
        specialSystem.setLocalIp(specialSystemDTO.getLocalIp());
        specialSystem.setSubnetMask(specialSystemDTO.getSubnetMask());
        specialSystem.setGatewayIp(specialSystemDTO.getGatewayIp());
        specialSystem.setMacAddr(specialSystemDTO.getMacAddr());
        specialSystem.setCreatedTimestamp(specialSystemDTO.getCreatedTimestamp());
        specialSystem.setUpdatedTimestamp(specialSystemDTO.getUpdatedTimestamp());
        final Device device = specialSystemDTO.getDeviceUuid() == null ? null : deviceRepository.findByUuid(specialSystemDTO.getDeviceUuid())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "device not found"));
        specialSystem.setDevice(device);
        return specialSystem;
    }

}

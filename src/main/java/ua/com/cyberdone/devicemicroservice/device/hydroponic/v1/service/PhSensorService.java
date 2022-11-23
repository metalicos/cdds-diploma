package ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ua.com.cyberdone.devicemicroservice.device.domain.Device;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.domain.PhSensor;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.model.PhSensorDTO;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.repos.PhSensorRepository;
import ua.com.cyberdone.devicemicroservice.device.repos.DeviceRepository;
import ua.com.cyberdone.devicemicroservice.device.service.ModelEntityMapper;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class PhSensorService implements ModelEntityMapper<PhSensor, PhSensorDTO> {
    private final PhSensorRepository phSensorRepository;
    private final DeviceRepository deviceRepository;

    public List<PhSensorDTO> findAll() {
        return phSensorRepository.findAll(Sort.by("id"))
                .stream()
                .map(phSensor -> mapToDTO(phSensor, new PhSensorDTO()))
                .collect(Collectors.toList());
    }

    public PhSensorDTO get(final Long id) {
        return phSensorRepository.findById(id)
                .map(phSensor -> mapToDTO(phSensor, new PhSensorDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public PhSensorDTO create(final PhSensorDTO phSensorDTO) {
        final PhSensor phSensor = new PhSensor();
        mapToEntity(phSensorDTO, phSensor);
        return mapToDTO(phSensorRepository.save(phSensor), new PhSensorDTO());
    }

    public PhSensorDTO update(final Long id, final PhSensorDTO phSensorDTO) {
        final PhSensor phSensor = phSensorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(phSensorDTO, phSensor);
        return mapToDTO(phSensorRepository.save(phSensor), new PhSensorDTO());
    }

    public void delete(final Long id) {
        phSensorRepository.deleteById(id);
    }

    @Override
    public PhSensorDTO mapToDTO(final PhSensor phSensor, final PhSensorDTO phSensorDTO) {
        phSensorDTO.setId(phSensor.getId());
        phSensorDTO.setUpdatedTimestamp(phSensor.getUpdatedTimestamp());
        phSensorDTO.setDeviceUuid(phSensor.getDevice() == null ? null : phSensor.getDevice().getUuid());
        return phSensorDTO;
    }

    @Override
    public PhSensor mapToEntity(final PhSensorDTO phSensorDTO, final PhSensor phSensor) {
        phSensor.setUpdatedTimestamp(phSensorDTO.getUpdatedTimestamp());
        final Device device = phSensorDTO.getDeviceUuid() == null ? null : deviceRepository.findByUuid(phSensorDTO.getDeviceUuid())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "device not found"));
        phSensor.setDevice(device);
        return phSensor;
    }

    public PhSensorDTO findByDeviceUuid(String uuid) {
        return phSensorRepository.findByDeviceUuid(uuid)
                .map(phSensor -> mapToDTO(phSensor, new PhSensorDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "device not found"));
    }
}

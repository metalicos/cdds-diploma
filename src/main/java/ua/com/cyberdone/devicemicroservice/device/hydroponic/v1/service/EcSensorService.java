package ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ua.com.cyberdone.devicemicroservice.device.domain.Device;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.domain.EcSensor;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.domain.EcSensorTemplate;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.model.EcSensorDTO;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.model.EcSensorTemplateDTO;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.repos.EcSensorRepository;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.repos.EcSensorTemplateRepository;
import ua.com.cyberdone.devicemicroservice.device.repos.DeviceRepository;
import ua.com.cyberdone.devicemicroservice.device.service.ModelEntityMapper;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;


@Transactional
@Service
@RequiredArgsConstructor
public class EcSensorService implements ModelEntityMapper<EcSensor, EcSensorDTO> {
    private final EcSensorRepository ecSensorRepository;
    private final EcSensorTemplateRepository ecSensorTemplateRepository;
    private final EcSensorTemplateService ecSensorTemplateService;
    private final DeviceRepository deviceRepository;

    public List<EcSensorDTO> findAll() {
        return ecSensorRepository.findAll(Sort.by("id"))
                .stream()
                .map(ecSensor -> mapToDTO(ecSensor, new EcSensorDTO()))
                .collect(Collectors.toList());
    }

    public EcSensorDTO get(final Long id) {
        return ecSensorRepository.findById(id)
                .map(ecSensor -> mapToDTO(ecSensor, new EcSensorDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public EcSensorDTO create(final EcSensorDTO ecSensorDTO) {
        final EcSensor ecSensor = new EcSensor();
        mapToEntity(ecSensorDTO, ecSensor);
        return mapToDTO(ecSensorRepository.save(ecSensor), new EcSensorDTO());
    }

    public EcSensorDTO update(final Long id, final EcSensorDTO ecSensorDTO) {
        final EcSensor ecSensor = ecSensorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(ecSensorDTO, ecSensor);
        return mapToDTO(ecSensorRepository.save(ecSensor), new EcSensorDTO());
    }

    public void delete(final Long id) {
        ecSensorRepository.deleteById(id);
    }

    @Override
    public EcSensorDTO mapToDTO(final EcSensor ecSensor, final EcSensorDTO ecSensorDTO) {
        ecSensorDTO.setId(ecSensor.getId());
        ecSensorDTO.setUpdatedTimestamp(ecSensor.getUpdatedTimestamp());
        ecSensorDTO.setEcSensorTemplateDTO(ecSensor.getEcSensorTemplate() == null ?
                null : ecSensorTemplateService.mapToDTO(ecSensor.getEcSensorTemplate(), new EcSensorTemplateDTO()));
        ecSensorDTO.setDeviceUuid(ecSensor.getDevice() == null ? null : ecSensor.getDevice().getUuid());
        return ecSensorDTO;
    }

    @Override
    public EcSensor mapToEntity(final EcSensorDTO ecSensorDTO, final EcSensor ecSensor) {
        ecSensor.setUpdatedTimestamp(ecSensorDTO.getUpdatedTimestamp());
        final EcSensorTemplate ecSensorTemplate = ecSensorTemplateRepository.findById(ecSensorDTO.getEcSensorTemplateDTO().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ecSensorTemplate not found"));
        ecSensor.setEcSensorTemplate(ecSensorTemplate);
        final Device device = ecSensorDTO.getDeviceUuid() == null ? null : deviceRepository.findByUuid(ecSensorDTO.getDeviceUuid())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "device not found"));
        ecSensor.setDevice(device);
        return ecSensor;
    }

    public EcSensorDTO findByDeviceUuid(String uuid) {
        return ecSensorRepository.findByDeviceUuid(uuid)
                .map(ecSensor -> mapToDTO(ecSensor, new EcSensorDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "device not found"));
    }
}
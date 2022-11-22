package ua.com.cyberdone.devicemicroservice.device.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ua.com.cyberdone.devicemicroservice.device.domain.Device;
import ua.com.cyberdone.devicemicroservice.device.domain.EcSensor;
import ua.com.cyberdone.devicemicroservice.device.domain.EcSensorTemplate;
import ua.com.cyberdone.devicemicroservice.device.model.EcSensorDTO;
import ua.com.cyberdone.devicemicroservice.device.repos.DeviceRepository;
import ua.com.cyberdone.devicemicroservice.device.repos.EcSensorRepository;
import ua.com.cyberdone.devicemicroservice.device.repos.EcSensorTemplateRepository;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Transactional
@Service
@RequiredArgsConstructor
public class EcSensorService implements ModelEntityMapper<EcSensor, EcSensorDTO> {
    private final EcSensorRepository ecSensorRepository;
    private final EcSensorTemplateRepository ecSensorTemplateRepository;
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

    public Long create(final EcSensorDTO ecSensorDTO) {
        final EcSensor ecSensor = new EcSensor();
        mapToEntity(ecSensorDTO, ecSensor);
        return ecSensorRepository.save(ecSensor).getId();
    }

    public void update(final Long id, final EcSensorDTO ecSensorDTO) {
        final EcSensor ecSensor = ecSensorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(ecSensorDTO, ecSensor);
        ecSensorRepository.save(ecSensor);
    }

    public void delete(final Long id) {
        ecSensorRepository.deleteById(id);
    }

    @Override
    public EcSensorDTO mapToDTO(final EcSensor ecSensor, final EcSensorDTO ecSensorDTO) {
        ecSensorDTO.setId(ecSensor.getId());
        ecSensorDTO.setUpdatedTimestamp(ecSensor.getUpdatedTimestamp());
        ecSensorDTO.setEcSensorTemplateIds(ecSensor.getEcSensorTemplateList() == null ? null : ecSensor.getEcSensorTemplateList().stream()
                .map(EcSensorTemplate::getId)
                .collect(Collectors.toList()));
        ecSensorDTO.setDeviceUuid(ecSensor.getDevice() == null ? null : ecSensor.getDevice().getUuid());
        return ecSensorDTO;
    }

    @Override
    public EcSensor mapToEntity(final EcSensorDTO ecSensorDTO, final EcSensor ecSensor) {
        ecSensor.setUpdatedTimestamp(ecSensorDTO.getUpdatedTimestamp());
        final List<EcSensorTemplate> ecSensorTemplates = ecSensorTemplateRepository.findAllById(
                ecSensorDTO.getEcSensorTemplateIds() == null ? Collections.emptyList() : ecSensorDTO.getEcSensorTemplateIds());
        if (ecSensorTemplates.size() != (ecSensorDTO.getEcSensorTemplateIds() == null ? 0 : ecSensorDTO.getEcSensorTemplateIds().size())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "one of ecSensorTemplates not found");
        }
        ecSensor.setEcSensorTemplateList(ecSensorTemplates);
        final Device device = ecSensorDTO.getDeviceUuid() == null ? null : deviceRepository.findByUuid(ecSensorDTO.getDeviceUuid())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "device not found"));
        ecSensor.setDevice(device);
        return ecSensor;
    }

}

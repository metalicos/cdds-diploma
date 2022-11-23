package ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.domain.PhSensor;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.domain.PhSensorTemplate;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.model.PhSensorTemplateDTO;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.repos.PhSensorRepository;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.repos.PhSensorTemplateRepository;
import ua.com.cyberdone.devicemicroservice.device.service.ModelEntityMapper;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Transactional
@Service
@RequiredArgsConstructor
public class PhSensorTemplateService implements ModelEntityMapper<PhSensorTemplate, PhSensorTemplateDTO> {
    private final PhSensorTemplateRepository phSensorTemplateRepository;
    private final PhSensorRepository phSensorRepository;


    public List<PhSensorTemplateDTO> findAll() {
        return phSensorTemplateRepository.findAll(Sort.by("id"))
                .stream()
                .map(phSensorTemplate -> mapToDTO(phSensorTemplate, new PhSensorTemplateDTO()))
                .collect(Collectors.toList());
    }

    public PhSensorTemplateDTO get(final Long id) {
        return phSensorTemplateRepository.findById(id)
                .map(phSensorTemplate -> mapToDTO(phSensorTemplate, new PhSensorTemplateDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public PhSensorTemplateDTO create(final PhSensorTemplateDTO phSensorTemplateDTO) {
        final PhSensorTemplate phSensorTemplate = new PhSensorTemplate();
        mapToEntity(phSensorTemplateDTO, phSensorTemplate);
        return mapToDTO(phSensorTemplateRepository.save(phSensorTemplate), new PhSensorTemplateDTO());
    }

    public PhSensorTemplateDTO update(final Long id, final PhSensorTemplateDTO phSensorTemplateDTO) {
        final PhSensorTemplate phSensorTemplate = phSensorTemplateRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(phSensorTemplateDTO, phSensorTemplate);
        return mapToDTO(phSensorTemplateRepository.save(phSensorTemplate), new PhSensorTemplateDTO());
    }

    public void delete(final Long id) {
        phSensorTemplateRepository.deleteById(id);
    }

    @Override
    public PhSensorTemplateDTO mapToDTO(final PhSensorTemplate phSensorTemplate,
                                        final PhSensorTemplateDTO phSensorTemplateDTO) {
        phSensorTemplateDTO.setId(phSensorTemplate.getId());
        phSensorTemplateDTO.setName(phSensorTemplate.getName());
        phSensorTemplateDTO.setDescription(phSensorTemplate.getDescription());
        phSensorTemplateDTO.setOwnerId(phSensorTemplate.getOwnerId());
        phSensorTemplateDTO.setTime(phSensorTemplate.getTime());
        phSensorTemplateDTO.setPoint(phSensorTemplate.getPoint());
        phSensorTemplateDTO.setValue(phSensorTemplate.getValue());
        phSensorTemplateDTO.setAdc(phSensorTemplate.getAdc());
        phSensorTemplateDTO.setSlope(phSensorTemplate.getSlope());
        phSensorTemplateDTO.setAdcOffset(phSensorTemplate.getAdcOffset());
        phSensorTemplateDTO.setOversampling(phSensorTemplate.getOversampling());
        phSensorTemplateDTO.setCreatedTimestamp(phSensorTemplate.getCreatedTimestamp());
        phSensorTemplateDTO.setUpdatedTimestamp(phSensorTemplate.getUpdatedTimestamp());
        phSensorTemplateDTO.setPhSensorTemplateIds(phSensorTemplate.getPhSensorList() == null ? null : phSensorTemplate.getPhSensorList().stream()
                .map(PhSensor::getId)
                .collect(Collectors.toList()));
        return phSensorTemplateDTO;
    }

    @Override
    public PhSensorTemplate mapToEntity(final PhSensorTemplateDTO phSensorTemplateDTO,
                                        final PhSensorTemplate phSensorTemplate) {
        phSensorTemplate.setName(phSensorTemplateDTO.getName());
        phSensorTemplate.setDescription(phSensorTemplateDTO.getDescription());
        phSensorTemplate.setOwnerId(phSensorTemplateDTO.getOwnerId());
        phSensorTemplate.setTime(phSensorTemplateDTO.getTime());
        phSensorTemplate.setPoint(phSensorTemplateDTO.getPoint());
        phSensorTemplate.setValue(phSensorTemplateDTO.getValue());
        phSensorTemplate.setAdc(phSensorTemplateDTO.getAdc());
        phSensorTemplate.setSlope(phSensorTemplateDTO.getSlope());
        phSensorTemplate.setAdcOffset(phSensorTemplateDTO.getAdcOffset());
        phSensorTemplate.setOversampling(phSensorTemplateDTO.getOversampling());
        phSensorTemplate.setCreatedTimestamp(phSensorTemplateDTO.getCreatedTimestamp());
        phSensorTemplate.setUpdatedTimestamp(phSensorTemplateDTO.getUpdatedTimestamp());
        final List<PhSensor> phSensorList = phSensorRepository.findAllById(
                phSensorTemplateDTO.getPhSensorTemplateIds() == null ? Collections.emptyList() : phSensorTemplateDTO.getPhSensorTemplateIds());
        if (phSensorList.size() != (phSensorTemplateDTO.getPhSensorTemplateIds() == null ? 0 : phSensorTemplateDTO.getPhSensorTemplateIds().size())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "one of phSensorList not found");
        }
        phSensorTemplate.setPhSensorList(phSensorList);
        return phSensorTemplate;
    }

    public Page<PhSensorTemplateDTO> getByOwnerId(Long ownerId, Integer page, Integer size) {
        return new PageImpl<>(phSensorTemplateRepository.findAllByOwnerId(ownerId, PageRequest.of(page, size)).stream()
                .map(phSensorTemplate -> mapToDTO(phSensorTemplate, new PhSensorTemplateDTO()))
                .collect(Collectors.toList()));
    }
}

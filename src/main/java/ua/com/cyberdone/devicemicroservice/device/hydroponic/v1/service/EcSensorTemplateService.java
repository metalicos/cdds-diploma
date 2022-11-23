package ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.domain.EcSensorTemplate;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.model.EcSensorTemplateDTO;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.repos.EcSensorTemplateRepository;
import ua.com.cyberdone.devicemicroservice.device.service.ModelEntityMapper;

import java.awt.print.Pageable;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class EcSensorTemplateService implements ModelEntityMapper<EcSensorTemplate, EcSensorTemplateDTO> {
    private final EcSensorTemplateRepository ecSensorTemplateRepository;


    public List<EcSensorTemplateDTO> findAll() {
        return ecSensorTemplateRepository.findAll(Sort.by("id"))
                .stream()
                .map(ecSensorTemplate -> mapToDTO(ecSensorTemplate, new EcSensorTemplateDTO()))
                .collect(Collectors.toList());
    }

    public EcSensorTemplateDTO get(final Long id) {
        return ecSensorTemplateRepository.findById(id)
                .map(ecSensorTemplate -> mapToDTO(ecSensorTemplate, new EcSensorTemplateDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public EcSensorTemplateDTO create(final EcSensorTemplateDTO ecSensorTemplateDTO) {
        final EcSensorTemplate ecSensorTemplate = new EcSensorTemplate();
        mapToEntity(ecSensorTemplateDTO, ecSensorTemplate);
        return mapToDTO(ecSensorTemplateRepository.save(ecSensorTemplate), new EcSensorTemplateDTO());
    }

    public EcSensorTemplateDTO update(final Long id, final EcSensorTemplateDTO ecSensorTemplateDTO) {
        final EcSensorTemplate ecSensorTemplate = ecSensorTemplateRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(ecSensorTemplateDTO, ecSensorTemplate);
        return mapToDTO(ecSensorTemplateRepository.save(ecSensorTemplate), new EcSensorTemplateDTO());
    }

    public void delete(final Long id) {
        ecSensorTemplateRepository.deleteById(id);
    }

    @Override
    public EcSensorTemplateDTO mapToDTO(final EcSensorTemplate ecSensorTemplate,
                                        final EcSensorTemplateDTO ecSensorTemplateDTO) {
        ecSensorTemplateDTO.setId(ecSensorTemplate.getId());
        ecSensorTemplateDTO.setName(ecSensorTemplate.getName());
        ecSensorTemplateDTO.setDescription(ecSensorTemplate.getDescription());
        ecSensorTemplateDTO.setOwnerId(ecSensorTemplate.getOwnerId());
        ecSensorTemplateDTO.setTime(ecSensorTemplate.getTime());
        ecSensorTemplateDTO.setKLowPoint(ecSensorTemplate.getKLowPoint());
        ecSensorTemplateDTO.setKHighPoint(ecSensorTemplate.getKHighPoint());
        ecSensorTemplateDTO.setRawEc(ecSensorTemplate.getRawEc());
        ecSensorTemplateDTO.setCreatedTimestamp(ecSensorTemplate.getCreatedTimestamp());
        ecSensorTemplateDTO.setUpdatedTimestamp(ecSensorTemplate.getUpdatedTimestamp());
        return ecSensorTemplateDTO;
    }

    @Override
    public EcSensorTemplate mapToEntity(final EcSensorTemplateDTO ecSensorTemplateDTO,
                                        final EcSensorTemplate ecSensorTemplate) {
        ecSensorTemplate.setName(ecSensorTemplateDTO.getName());
        ecSensorTemplate.setDescription(ecSensorTemplateDTO.getDescription());
        ecSensorTemplate.setOwnerId(ecSensorTemplateDTO.getOwnerId());
        ecSensorTemplate.setTime(ecSensorTemplateDTO.getTime());
        ecSensorTemplate.setKLowPoint(ecSensorTemplateDTO.getKLowPoint());
        ecSensorTemplate.setKHighPoint(ecSensorTemplateDTO.getKHighPoint());
        ecSensorTemplate.setRawEc(ecSensorTemplateDTO.getRawEc());
        ecSensorTemplate.setCreatedTimestamp(ecSensorTemplateDTO.getCreatedTimestamp());
        ecSensorTemplate.setUpdatedTimestamp(ecSensorTemplateDTO.getUpdatedTimestamp());
        return ecSensorTemplate;
    }

    public Page<EcSensorTemplateDTO> getByOwnerId(Long ownerId, Integer page, Integer size) {
        return new PageImpl<>(ecSensorTemplateRepository.findAllByOwnerId(ownerId, (Pageable) PageRequest.of(page, size)).stream()
                .map(ecSensorTemplate -> mapToDTO(ecSensorTemplate, new EcSensorTemplateDTO()))
                .collect(Collectors.toList()));
    }
}

package ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.domain.DispenserTemplate;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.model.DispenserTemplateDTO;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.repos.DispenserTemplateRepository;
import ua.com.cyberdone.devicemicroservice.device.service.ModelEntityMapper;

import java.awt.print.Pageable;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class DispenserTemplateService implements ModelEntityMapper<DispenserTemplate, DispenserTemplateDTO> {
    private final DispenserTemplateRepository dispenserTemplateRepository;


    public List<DispenserTemplateDTO> findAll() {
        return dispenserTemplateRepository.findAll(Sort.by("id"))
                .stream()
                .map(dispenserTemplate -> mapToDTO(dispenserTemplate, new DispenserTemplateDTO()))
                .collect(Collectors.toList());
    }

    public DispenserTemplateDTO get(final Long id) {
        return dispenserTemplateRepository.findById(id)
                .map(dispenserTemplate -> mapToDTO(dispenserTemplate, new DispenserTemplateDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public DispenserTemplateDTO create(final DispenserTemplateDTO dispenserTemplateDTO) {
        final DispenserTemplate dispenserTemplate = new DispenserTemplate();
        mapToEntity(dispenserTemplateDTO, dispenserTemplate);
        return mapToDTO(dispenserTemplateRepository.save(dispenserTemplate), new DispenserTemplateDTO());
    }

    public DispenserTemplateDTO update(final Long id, final DispenserTemplateDTO dispenserTemplateDTO) {
        final DispenserTemplate dispenserTemplate = dispenserTemplateRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(dispenserTemplateDTO, dispenserTemplate);
        return mapToDTO(dispenserTemplateRepository.save(dispenserTemplate), new DispenserTemplateDTO());
    }

    public void delete(final Long id) {
        dispenserTemplateRepository.deleteById(id);
    }

    @Override
    public DispenserTemplateDTO mapToDTO(final DispenserTemplate dispenserTemplate,
                                         final DispenserTemplateDTO dispenserTemplateDTO) {
        dispenserTemplateDTO.setId(dispenserTemplate.getId());
        dispenserTemplateDTO.setName(dispenserTemplate.getName());
        dispenserTemplateDTO.setDescription(dispenserTemplate.getDescription());
        dispenserTemplateDTO.setOwnerId(dispenserTemplate.getOwnerId());
        dispenserTemplateDTO.setIndex(dispenserTemplate.getIndex());
        dispenserTemplateDTO.setTime(dispenserTemplate.getTime());
        dispenserTemplateDTO.setDispenserName(dispenserTemplate.getDispenserName());
        dispenserTemplateDTO.setPinA(dispenserTemplate.getPinA());
        dispenserTemplateDTO.setPinB(dispenserTemplate.getPinB());
        dispenserTemplateDTO.setRegulationDirection(dispenserTemplate.getRegulationDirection());
        dispenserTemplateDTO.setEnable(dispenserTemplate.getEnable());
        dispenserTemplateDTO.setPolarity(dispenserTemplate.getPolarity());
        dispenserTemplateDTO.setSmartDose(dispenserTemplate.getSmartDose());
        dispenserTemplateDTO.setTotalAddedMl(dispenserTemplate.getTotalAddedMl());
        dispenserTemplateDTO.setMlPerMs(dispenserTemplate.getMlPerMs());
        dispenserTemplateDTO.setTargetValue(dispenserTemplate.getTargetValue());
        dispenserTemplateDTO.setError(dispenserTemplate.getError());
        dispenserTemplateDTO.setRecheckDispenserAfterSeconds(dispenserTemplate.getRecheckDispenserAfterSeconds());
        dispenserTemplateDTO.setLastDispenserRecheckTime(dispenserTemplate.getLastDispenserRecheckTime());
        dispenserTemplateDTO.setMixingVolumeMl(dispenserTemplate.getMixingVolumeMl());
        dispenserTemplateDTO.setUpdatedTimestamp(dispenserTemplate.getUpdatedTimestamp());
        return dispenserTemplateDTO;
    }

    @Override
    public DispenserTemplate mapToEntity(final DispenserTemplateDTO dispenserTemplateDTO,
                                         final DispenserTemplate dispenserTemplate) {
        dispenserTemplate.setName(dispenserTemplateDTO.getName());
        dispenserTemplate.setDescription(dispenserTemplateDTO.getDescription());
        dispenserTemplate.setOwnerId(dispenserTemplateDTO.getOwnerId());
        dispenserTemplate.setIndex(dispenserTemplateDTO.getIndex());
        dispenserTemplate.setTime(dispenserTemplateDTO.getTime());
        dispenserTemplate.setDispenserName(dispenserTemplateDTO.getDispenserName());
        dispenserTemplate.setPinA(dispenserTemplateDTO.getPinA());
        dispenserTemplate.setPinB(dispenserTemplateDTO.getPinB());
        dispenserTemplate.setRegulationDirection(dispenserTemplateDTO.getRegulationDirection());
        dispenserTemplate.setEnable(dispenserTemplateDTO.getEnable());
        dispenserTemplate.setPolarity(dispenserTemplateDTO.getPolarity());
        dispenserTemplate.setSmartDose(dispenserTemplateDTO.getSmartDose());
        dispenserTemplate.setTotalAddedMl(dispenserTemplateDTO.getTotalAddedMl());
        dispenserTemplate.setMlPerMs(dispenserTemplateDTO.getMlPerMs());
        dispenserTemplate.setTargetValue(dispenserTemplateDTO.getTargetValue());
        dispenserTemplate.setError(dispenserTemplateDTO.getError());
        dispenserTemplate.setRecheckDispenserAfterSeconds(dispenserTemplateDTO.getRecheckDispenserAfterSeconds());
        dispenserTemplate.setLastDispenserRecheckTime(dispenserTemplateDTO.getLastDispenserRecheckTime());
        dispenserTemplate.setMixingVolumeMl(dispenserTemplateDTO.getMixingVolumeMl());
        dispenserTemplate.setUpdatedTimestamp(dispenserTemplateDTO.getUpdatedTimestamp());
        return dispenserTemplate;
    }

    public Page<DispenserTemplateDTO> getByOwnerId(Long ownerId, Integer page, Integer size) {
        return new PageImpl<>(dispenserTemplateRepository.findAllByOwnerId(ownerId, (Pageable) PageRequest.of(page, size)).stream()
                .map(dispenserTemplate -> mapToDTO(dispenserTemplate, new DispenserTemplateDTO()))
                .collect(Collectors.toList()));
    }
}

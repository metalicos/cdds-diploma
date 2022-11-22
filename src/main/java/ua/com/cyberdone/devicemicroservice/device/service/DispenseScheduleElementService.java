package ua.com.cyberdone.devicemicroservice.device.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ua.com.cyberdone.devicemicroservice.device.domain.DispenseScheduleElement;
import ua.com.cyberdone.devicemicroservice.device.model.DispenseScheduleElementDTO;
import ua.com.cyberdone.devicemicroservice.device.repos.DispenseScheduleElementRepository;
import ua.com.cyberdone.devicemicroservice.device.repos.DispenseScheduleTemplateRepository;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class DispenseScheduleElementService implements ModelEntityMapper<DispenseScheduleElement, DispenseScheduleElementDTO> {
    private final DispenseScheduleElementRepository dispenseScheduleElementRepository;
    private final DispenseScheduleTemplateRepository dispenseScheduleTemplateRepository;

    public List<DispenseScheduleElementDTO> findAll() {
        return dispenseScheduleElementRepository.findAll(Sort.by("id"))
                .stream()
                .map(dispenseScheduleElement -> mapToDTO(dispenseScheduleElement, new DispenseScheduleElementDTO()))
                .collect(Collectors.toList());
    }

    public DispenseScheduleElementDTO get(final Long id) {
        return dispenseScheduleElementRepository.findById(id)
                .map(dispenseScheduleElement -> mapToDTO(dispenseScheduleElement, new DispenseScheduleElementDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final DispenseScheduleElementDTO dispenseScheduleElementDTO) {
        final DispenseScheduleElement dispenseScheduleElement = new DispenseScheduleElement();
        mapToEntity(dispenseScheduleElementDTO, dispenseScheduleElement);
        return dispenseScheduleElementRepository.save(dispenseScheduleElement).getId();
    }

    public void update(final Long id, final DispenseScheduleElementDTO dispenseScheduleElementDTO) {
        final DispenseScheduleElement dispenseScheduleElement = dispenseScheduleElementRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(dispenseScheduleElementDTO, dispenseScheduleElement);
        dispenseScheduleElementRepository.save(dispenseScheduleElement);
    }

    public void delete(final Long id) {
        dispenseScheduleElementRepository.deleteById(id);
    }

    @Override
    public DispenseScheduleElementDTO mapToDTO(
            final DispenseScheduleElement dispenseScheduleElement,
            final DispenseScheduleElementDTO dispenseScheduleElementDTO) {
        dispenseScheduleElementDTO.setId(dispenseScheduleElement.getId());
        dispenseScheduleElementDTO.setIndex(dispenseScheduleElement.getIndex());
        dispenseScheduleElementDTO.setTime(dispenseScheduleElement.getTime());
        dispenseScheduleElementDTO.setDay(dispenseScheduleElement.getDay());
        dispenseScheduleElementDTO.setTargetEc(dispenseScheduleElement.getTargetEc());
        dispenseScheduleElementDTO.setTargetPh(dispenseScheduleElement.getTargetPh());
        dispenseScheduleElementDTO.setEcError(dispenseScheduleElement.getEcError());
        dispenseScheduleElementDTO.setPhError(dispenseScheduleElement.getPhError());
        dispenseScheduleElementDTO.setRecheckAfterSec(dispenseScheduleElement.getRecheckAfterSec());
        dispenseScheduleElementDTO.setCreatedTimestamp(dispenseScheduleElement.getCreatedTimestamp());
        dispenseScheduleElementDTO.setUpdatedTimestamp(dispenseScheduleElement.getUpdatedTimestamp());
        dispenseScheduleElementDTO.setDispenseScheduleTemplateId(dispenseScheduleElement.getDispenseScheduleTemplate() == null ? null : dispenseScheduleElement.getDispenseScheduleTemplate().getId());
        return dispenseScheduleElementDTO;
    }

    @Override
    public DispenseScheduleElement mapToEntity(
            final DispenseScheduleElementDTO dispenseScheduleElementDTO,
            final DispenseScheduleElement dispenseScheduleElement) {
        dispenseScheduleElement.setIndex(dispenseScheduleElementDTO.getIndex());
        dispenseScheduleElement.setTime(dispenseScheduleElementDTO.getTime());
        dispenseScheduleElement.setDay(dispenseScheduleElementDTO.getDay());
        dispenseScheduleElement.setTargetEc(dispenseScheduleElementDTO.getTargetEc());
        dispenseScheduleElement.setTargetPh(dispenseScheduleElementDTO.getTargetPh());
        dispenseScheduleElement.setEcError(dispenseScheduleElementDTO.getEcError());
        dispenseScheduleElement.setPhError(dispenseScheduleElementDTO.getPhError());
        dispenseScheduleElement.setRecheckAfterSec(dispenseScheduleElementDTO.getRecheckAfterSec());
        dispenseScheduleElement.setCreatedTimestamp(dispenseScheduleElementDTO.getCreatedTimestamp());
        dispenseScheduleElement.setUpdatedTimestamp(dispenseScheduleElementDTO.getUpdatedTimestamp());
        var dispenseScheduleTemplate = dispenseScheduleElementDTO.getDispenseScheduleTemplateId() == null ? null : dispenseScheduleTemplateRepository.findById(dispenseScheduleElementDTO.getDispenseScheduleTemplateId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "dispenseScheduleTemplate not found"));
        dispenseScheduleElement.setDispenseScheduleTemplate(dispenseScheduleTemplate);
        return dispenseScheduleElement;
    }

}

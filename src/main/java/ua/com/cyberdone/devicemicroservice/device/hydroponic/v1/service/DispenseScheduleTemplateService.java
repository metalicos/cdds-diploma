package ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.domain.DispenseScheduleTemplate;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.model.DispenseScheduleTemplateDTO;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.repos.DispenseScheduleTemplateRepository;
import ua.com.cyberdone.devicemicroservice.device.service.ModelEntityMapper;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class DispenseScheduleTemplateService implements ModelEntityMapper<DispenseScheduleTemplate, DispenseScheduleTemplateDTO> {

    private final DispenseScheduleTemplateRepository dispenseScheduleTemplateRepository;

    public List<DispenseScheduleTemplateDTO> findAll() {
        return dispenseScheduleTemplateRepository.findAll(Sort.by("id"))
                .stream()
                .map(dispenseScheduleTemplate -> mapToDTO(dispenseScheduleTemplate, new DispenseScheduleTemplateDTO()))
                .collect(Collectors.toList());
    }

    public DispenseScheduleTemplateDTO get(final Long id) {
        return dispenseScheduleTemplateRepository.findById(id)
                .map(dispenseScheduleTemplate -> mapToDTO(dispenseScheduleTemplate, new DispenseScheduleTemplateDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public DispenseScheduleTemplateDTO create(final DispenseScheduleTemplateDTO dispenseScheduleTemplateDTO) {
        final DispenseScheduleTemplate dispenseScheduleTemplate = new DispenseScheduleTemplate();
        mapToEntity(dispenseScheduleTemplateDTO, dispenseScheduleTemplate);
        return mapToDTO(dispenseScheduleTemplateRepository.save(dispenseScheduleTemplate), new DispenseScheduleTemplateDTO());
    }

    public DispenseScheduleTemplateDTO update(final Long id,
                                              final DispenseScheduleTemplateDTO dispenseScheduleTemplateDTO) {
        final DispenseScheduleTemplate dispenseScheduleTemplate = dispenseScheduleTemplateRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(dispenseScheduleTemplateDTO, dispenseScheduleTemplate);
        return mapToDTO(dispenseScheduleTemplateRepository.save(dispenseScheduleTemplate), new DispenseScheduleTemplateDTO());
    }

    public void delete(final Long id) {
        dispenseScheduleTemplateRepository.deleteById(id);
    }

    @Override
    public DispenseScheduleTemplateDTO mapToDTO(
            final DispenseScheduleTemplate dispenseScheduleTemplate,
            final DispenseScheduleTemplateDTO dispenseScheduleTemplateDTO) {
        dispenseScheduleTemplateDTO.setId(dispenseScheduleTemplate.getId());
        dispenseScheduleTemplateDTO.setName(dispenseScheduleTemplate.getName());
        dispenseScheduleTemplateDTO.setDescription(dispenseScheduleTemplate.getDescription());
        dispenseScheduleTemplateDTO.setOwnerId(dispenseScheduleTemplate.getOwnerId());
        dispenseScheduleTemplateDTO.setCreatedTimestamp(dispenseScheduleTemplate.getCreatedTimestamp());
        dispenseScheduleTemplateDTO.setUpdatedTimestamp(dispenseScheduleTemplate.getUpdatedTimestamp());
        return dispenseScheduleTemplateDTO;
    }

    @Override
    public DispenseScheduleTemplate mapToEntity(
            final DispenseScheduleTemplateDTO dispenseScheduleTemplateDTO,
            final DispenseScheduleTemplate dispenseScheduleTemplate) {
        dispenseScheduleTemplate.setName(dispenseScheduleTemplateDTO.getName());
        dispenseScheduleTemplate.setDescription(dispenseScheduleTemplateDTO.getDescription());
        dispenseScheduleTemplate.setOwnerId(dispenseScheduleTemplateDTO.getOwnerId());
        dispenseScheduleTemplate.setCreatedTimestamp(dispenseScheduleTemplateDTO.getCreatedTimestamp());
        dispenseScheduleTemplate.setUpdatedTimestamp(dispenseScheduleTemplateDTO.getUpdatedTimestamp());
        return dispenseScheduleTemplate;
    }

    public Page<DispenseScheduleTemplateDTO> getByOwnerId(Long ownerId, Integer page, Integer size) {
        return new PageImpl<>(dispenseScheduleTemplateRepository.findAllByOwnerId(ownerId, PageRequest.of(page, size)).stream()
                .map(dispenserTemplate -> mapToDTO(dispenserTemplate, new DispenseScheduleTemplateDTO()))
                .collect(Collectors.toList()));
    }
}

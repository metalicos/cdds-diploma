package ua.com.cyberdone.devicemicroservice.device.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ua.com.cyberdone.devicemicroservice.device.domain.DispenseScheduleTemplate;
import ua.com.cyberdone.devicemicroservice.device.model.DispenseScheduleTemplateDTO;
import ua.com.cyberdone.devicemicroservice.device.repos.DispenseScheduleTemplateRepository;

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

    public Long create(final DispenseScheduleTemplateDTO dispenseScheduleTemplateDTO) {
        final DispenseScheduleTemplate dispenseScheduleTemplate = new DispenseScheduleTemplate();
        mapToEntity(dispenseScheduleTemplateDTO, dispenseScheduleTemplate);
        return dispenseScheduleTemplateRepository.save(dispenseScheduleTemplate).getId();
    }

    public void update(final Long id,
                       final DispenseScheduleTemplateDTO dispenseScheduleTemplateDTO) {
        final DispenseScheduleTemplate dispenseScheduleTemplate = dispenseScheduleTemplateRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(dispenseScheduleTemplateDTO, dispenseScheduleTemplate);
        dispenseScheduleTemplateRepository.save(dispenseScheduleTemplate);
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
}

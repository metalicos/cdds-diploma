package ua.com.cyberdone.devicemicroservice.device.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ua.com.cyberdone.devicemicroservice.device.domain.System;
import ua.com.cyberdone.devicemicroservice.device.domain.SystemTemplate;
import ua.com.cyberdone.devicemicroservice.device.model.SystemTemplateDTO;
import ua.com.cyberdone.devicemicroservice.device.repos.SystemRepository;
import ua.com.cyberdone.devicemicroservice.device.repos.SystemTemplateRepository;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Transactional
@Service
@RequiredArgsConstructor
public class SystemTemplateService implements ModelEntityMapper<SystemTemplate, SystemTemplateDTO> {
    private final SystemTemplateRepository systemTemplateRepository;
    private final SystemRepository systemRepository;


    public List<SystemTemplateDTO> findAll() {
        return systemTemplateRepository.findAll(Sort.by("id"))
                .stream()
                .map(systemTemplate -> mapToDTO(systemTemplate, new SystemTemplateDTO()))
                .collect(Collectors.toList());
    }

    public SystemTemplateDTO get(final Long id) {
        return systemTemplateRepository.findById(id)
                .map(systemTemplate -> mapToDTO(systemTemplate, new SystemTemplateDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final SystemTemplateDTO systemTemplateDTO) {
        final SystemTemplate systemTemplate = new SystemTemplate();
        mapToEntity(systemTemplateDTO, systemTemplate);
        return systemTemplateRepository.save(systemTemplate).getId();
    }

    public void update(final Long id, final SystemTemplateDTO systemTemplateDTO) {
        final SystemTemplate systemTemplate = systemTemplateRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(systemTemplateDTO, systemTemplate);
        systemTemplateRepository.save(systemTemplate);
    }

    public void delete(final Long id) {
        systemTemplateRepository.deleteById(id);
    }

    @Override
    public SystemTemplateDTO mapToDTO(final SystemTemplate systemTemplate,
                                      final SystemTemplateDTO systemTemplateDTO) {
        systemTemplateDTO.setId(systemTemplate.getId());
        systemTemplateDTO.setName(systemTemplate.getName());
        systemTemplateDTO.setDescription(systemTemplate.getDescription());
        systemTemplateDTO.setOwnerId(systemTemplate.getOwnerId());
        systemTemplateDTO.setTime(systemTemplate.getTime());
        systemTemplateDTO.setTimeZone(systemTemplate.getTimeZone());
        systemTemplateDTO.setNtpServer(systemTemplate.getNtpServer());
        systemTemplateDTO.setWifiSsid(systemTemplate.getWifiSsid());
        systemTemplateDTO.setWifiPass(systemTemplate.getWifiPass());
        systemTemplateDTO.setRestarts(systemTemplate.getRestarts());
        systemTemplateDTO.setGrowingDay(systemTemplate.getGrowingDay());
        systemTemplateDTO.setIsGrowing(systemTemplate.getIsGrowing());
        systemTemplateDTO.setGrowStartDate(systemTemplate.getGrowStartDate());
        systemTemplateDTO.setDispensersEnable(systemTemplate.getDispensersEnable());
        systemTemplateDTO.setSensorsEnable(systemTemplate.getSensorsEnable());
        systemTemplateDTO.setCreatedTimestamp(systemTemplate.getCreatedTimestamp());
        systemTemplateDTO.setUpdatedTimestamp(systemTemplate.getUpdatedTimestamp());
        systemTemplateDTO.setSystemTemplateIds(systemTemplate.getSystemList() == null ? null : systemTemplate.getSystemList().stream()
                .map(System::getId)
                .collect(Collectors.toList()));
        return systemTemplateDTO;
    }

    @Override
    public SystemTemplate mapToEntity(final SystemTemplateDTO systemTemplateDTO,
                                      final SystemTemplate systemTemplate) {
        systemTemplate.setName(systemTemplateDTO.getName());
        systemTemplate.setDescription(systemTemplateDTO.getDescription());
        systemTemplate.setOwnerId(systemTemplateDTO.getOwnerId());
        systemTemplate.setTime(systemTemplateDTO.getTime());
        systemTemplate.setTimeZone(systemTemplateDTO.getTimeZone());
        systemTemplate.setNtpServer(systemTemplateDTO.getNtpServer());
        systemTemplate.setWifiSsid(systemTemplateDTO.getWifiSsid());
        systemTemplate.setWifiPass(systemTemplateDTO.getWifiPass());
        systemTemplate.setRestarts(systemTemplateDTO.getRestarts());
        systemTemplate.setGrowingDay(systemTemplateDTO.getGrowingDay());
        systemTemplate.setIsGrowing(systemTemplateDTO.getIsGrowing());
        systemTemplate.setGrowStartDate(systemTemplateDTO.getGrowStartDate());
        systemTemplate.setDispensersEnable(systemTemplateDTO.getDispensersEnable());
        systemTemplate.setSensorsEnable(systemTemplateDTO.getSensorsEnable());
        systemTemplate.setCreatedTimestamp(systemTemplateDTO.getCreatedTimestamp());
        systemTemplate.setUpdatedTimestamp(systemTemplateDTO.getUpdatedTimestamp());
        final List<System> systemList = systemRepository.findAllById(
                systemTemplateDTO.getSystemTemplateIds() == null ? Collections.emptyList() : systemTemplateDTO.getSystemTemplateIds());
        if (systemList.size() != (systemTemplateDTO.getSystemTemplateIds() == null ? 0 : systemTemplateDTO.getSystemTemplateIds().size())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "one of systemList not found");
        }
        systemTemplate.setSystemList(systemList);
        return systemTemplate;
    }

}

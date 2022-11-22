package ua.com.cyberdone.devicemicroservice.device.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ua.com.cyberdone.devicemicroservice.device.domain.Device;
import ua.com.cyberdone.devicemicroservice.device.domain.DispenseSchedule;
import ua.com.cyberdone.devicemicroservice.device.domain.DispenseScheduleTemplate;
import ua.com.cyberdone.devicemicroservice.device.model.DispenseScheduleDTO;
import ua.com.cyberdone.devicemicroservice.device.repos.DeviceRepository;
import ua.com.cyberdone.devicemicroservice.device.repos.DispenseScheduleRepository;
import ua.com.cyberdone.devicemicroservice.device.repos.DispenseScheduleTemplateRepository;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Transactional
@Service
@RequiredArgsConstructor
public class DispenseScheduleService implements ModelEntityMapper<DispenseSchedule, DispenseScheduleDTO> {
    private final DispenseScheduleRepository dispenseScheduleRepository;
    private final DeviceRepository deviceRepository;
    private final DispenseScheduleTemplateRepository dispenseScheduleTemplateRepository;


    public List<DispenseScheduleDTO> findAll() {
        return dispenseScheduleRepository.findAll(Sort.by("id"))
                .stream()
                .map(dispenseSchedule -> mapToDTO(dispenseSchedule, new DispenseScheduleDTO()))
                .collect(Collectors.toList());
    }

    public DispenseScheduleDTO get(final Long id) {
        return dispenseScheduleRepository.findById(id)
                .map(dispenseSchedule -> mapToDTO(dispenseSchedule, new DispenseScheduleDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final DispenseScheduleDTO dispenseScheduleDTO) {
        final DispenseSchedule dispenseSchedule = new DispenseSchedule();
        mapToEntity(dispenseScheduleDTO, dispenseSchedule);
        return dispenseScheduleRepository.save(dispenseSchedule).getId();
    }

    public void update(final Long id, final DispenseScheduleDTO dispenseScheduleDTO) {
        final DispenseSchedule dispenseSchedule = dispenseScheduleRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(dispenseScheduleDTO, dispenseSchedule);
        dispenseScheduleRepository.save(dispenseSchedule);
    }

    public void delete(final Long id) {
        dispenseScheduleRepository.deleteById(id);
    }

    @Override
    public DispenseScheduleDTO mapToDTO(final DispenseSchedule dispenseSchedule,
                                        final DispenseScheduleDTO dispenseScheduleDTO) {
        dispenseScheduleDTO.setId(dispenseSchedule.getId());
        dispenseScheduleDTO.setUpdatedTimestamp(dispenseSchedule.getUpdatedTimestamp());
        dispenseScheduleDTO.setDeviceUuid(dispenseSchedule.getDevice() == null ? null : dispenseSchedule.getDevice().getId());
        dispenseScheduleDTO.setDispenseScheduleTemplateIds(dispenseSchedule.getDispenseScheduleTemplateList() == null ? null : dispenseSchedule.getDispenseScheduleTemplateList().stream()
                .map(DispenseScheduleTemplate::getId)
                .collect(Collectors.toList()));
        return dispenseScheduleDTO;
    }

    @Override
    public DispenseSchedule mapToEntity(final DispenseScheduleDTO dispenseScheduleDTO,
                                        final DispenseSchedule dispenseSchedule) {
        dispenseSchedule.setUpdatedTimestamp(dispenseScheduleDTO.getUpdatedTimestamp());
        final Device device = dispenseScheduleDTO.getDeviceUuid() == null ? null : deviceRepository.findById(dispenseScheduleDTO.getDeviceUuid())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "device not found"));
        dispenseSchedule.setDevice(device);
        final List<DispenseScheduleTemplate> dispenseScheduleTemplateList = dispenseScheduleTemplateRepository.findAllById(
                dispenseScheduleDTO.getDispenseScheduleTemplateIds() == null ? Collections.emptyList() : dispenseScheduleDTO.getDispenseScheduleTemplateIds());
        if (dispenseScheduleTemplateList.size() != (dispenseScheduleDTO.getDispenseScheduleTemplateIds() == null ? 0 : dispenseScheduleDTO.getDispenseScheduleTemplateIds().size())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "one of dispenseScheduleTemplateList not found");
        }
        dispenseSchedule.setDispenseScheduleTemplateList(dispenseScheduleTemplateList);
        return dispenseSchedule;
    }

}

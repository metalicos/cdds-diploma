package ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ua.com.cyberdone.devicemicroservice.device.domain.Device;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.domain.DispenseSchedule;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.model.DispenseScheduleDTO;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.model.DispenseScheduleTemplateDTO;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.repos.DispenseScheduleDispenseScheduleTemplateRepository;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.repos.DispenseScheduleRepository;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.repos.DispenseScheduleTemplateRepository;
import ua.com.cyberdone.devicemicroservice.device.repos.DeviceRepository;
import ua.com.cyberdone.devicemicroservice.device.service.ModelEntityMapper;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;


@Transactional
@Service
@RequiredArgsConstructor
public class DispenseScheduleService implements ModelEntityMapper<DispenseSchedule, DispenseScheduleDTO> {
    private final DispenseScheduleRepository dispenseScheduleRepository;
    private final DeviceRepository deviceRepository;
    private final DispenseScheduleDispenseScheduleTemplateRepository dispenseScheduleDispenseScheduleTemplateRepository;
    private final DispenseScheduleTemplateService dispenseScheduleTemplateService;


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

    public DispenseScheduleDTO create(final DispenseScheduleDTO dispenseScheduleDTO) {
        final DispenseSchedule dispenseSchedule = new DispenseSchedule();
        mapToEntity(dispenseScheduleDTO, dispenseSchedule);
        return mapToDTO(dispenseScheduleRepository.save(dispenseSchedule), new DispenseScheduleDTO());
    }

    public DispenseScheduleDTO update(final Long id, final DispenseScheduleDTO dispenseScheduleDTO) {
        final DispenseSchedule dispenseSchedule = dispenseScheduleRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(dispenseScheduleDTO, dispenseSchedule);
        return mapToDTO(dispenseScheduleRepository.save(dispenseSchedule), new DispenseScheduleDTO());
    }

    public void delete(final Long id) {
        dispenseScheduleRepository.deleteById(id);
    }

    @Override
    public DispenseScheduleDTO mapToDTO(final DispenseSchedule dispenseSchedule,
                                        final DispenseScheduleDTO dispenseScheduleDTO) {
        dispenseScheduleDTO.setId(dispenseSchedule.getId());
        dispenseScheduleDTO.setUpdatedTimestamp(dispenseSchedule.getUpdatedTimestamp());
        dispenseScheduleDTO.setDeviceUuid(dispenseSchedule.getDevice() == null ? null : dispenseSchedule.getDevice().getUuid());

        if (dispenseSchedule.getDispenseScheduleDispenseScheduleTemplate() != null) {
            var template = dispenseSchedule.getDispenseScheduleDispenseScheduleTemplate().getDispenseScheduleTemplate();
            dispenseScheduleDTO.setDispenseScheduleTemplateDTO(dispenseScheduleTemplateService.mapToDTO(template, new DispenseScheduleTemplateDTO()));
        }

        return dispenseScheduleDTO;
    }

    @Override
    public DispenseSchedule mapToEntity(final DispenseScheduleDTO dispenseScheduleDTO, final DispenseSchedule dispenseSchedule) {
        dispenseSchedule.setUpdatedTimestamp(dispenseScheduleDTO.getUpdatedTimestamp());
        final Device device = dispenseScheduleDTO.getDeviceUuid() == null ? null : deviceRepository.findByUuid(dispenseScheduleDTO.getDeviceUuid())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "device not found"));
        dispenseSchedule.setDevice(device);

        if (dispenseScheduleDTO.getDispenseScheduleTemplateDTO() != null && dispenseScheduleDTO.getDispenseScheduleTemplateDTO().getId() != null) {
            var dispScheduleTemplateId = dispenseScheduleDTO.getDispenseScheduleTemplateDTO().getId();

            var mapping = dispenseScheduleDispenseScheduleTemplateRepository.findByDispenseScheduleIdAndDispenseScheduleTemplateId(
                    dispenseSchedule.getId(), dispScheduleTemplateId).orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "mapping with dispScheduleId=" + dispenseSchedule.getId() +
                            " and dispSchedTemplId=" + dispScheduleTemplateId + " not found"));

            dispenseSchedule.setDispenseScheduleDispenseScheduleTemplate(mapping);
        }

        return dispenseSchedule;
    }

    public DispenseScheduleDTO findByDeviceUuid(String uuid) {
        return dispenseScheduleRepository.findByDevice(uuid)
                .map(dispenseSchedule -> mapToDTO(dispenseSchedule, new DispenseScheduleDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}

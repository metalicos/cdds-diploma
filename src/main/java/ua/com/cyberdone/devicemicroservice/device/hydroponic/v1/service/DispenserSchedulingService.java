package ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.domain.DispenserScheduling;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.model.DispenserSchedulingDTO;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.repos.DispenseScheduleElementRepository;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.repos.DispenserSchedulingRepository;
import ua.com.cyberdone.devicemicroservice.device.service.ModelEntityMapper;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class DispenserSchedulingService implements ModelEntityMapper<DispenserScheduling, DispenserSchedulingDTO> {
    private final DispenserSchedulingRepository dispenserSchedulingRepository;
    private final DispenseScheduleElementRepository dispenseScheduleElementRepository;

    public List<DispenserSchedulingDTO> findAll() {
        return dispenserSchedulingRepository.findAll(Sort.by("id"))
                .stream()
                .map(dispenserScheduling -> mapToDTO(dispenserScheduling, new DispenserSchedulingDTO()))
                .collect(Collectors.toList());
    }

    public DispenserSchedulingDTO get(final Long id) {
        return dispenserSchedulingRepository.findById(id)
                .map(dispenserScheduling -> mapToDTO(dispenserScheduling, new DispenserSchedulingDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final DispenserSchedulingDTO dispenserSchedulingDTO) {
        var dispenserScheduling = new DispenserScheduling();
        mapToEntity(dispenserSchedulingDTO, dispenserScheduling);
        return dispenserSchedulingRepository.save(dispenserScheduling).getId();
    }

    public void update(final Long id, final DispenserSchedulingDTO dispenserSchedulingDTO) {
        var dispenserScheduling = dispenserSchedulingRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(dispenserSchedulingDTO, dispenserScheduling);
        dispenserSchedulingRepository.save(dispenserScheduling);
    }

    public void delete(final Long id) {
        dispenserSchedulingRepository.deleteById(id);
    }

    @Override
    public DispenserSchedulingDTO mapToDTO(final DispenserScheduling dispenserScheduling,
                                           final DispenserSchedulingDTO dispenserSchedulingDTO) {
        dispenserSchedulingDTO.setId(dispenserScheduling.getId());
        dispenserSchedulingDTO.setIndex(dispenserScheduling.getIndex());
        dispenserSchedulingDTO.setLabel(dispenserScheduling.getLabel());
        dispenserSchedulingDTO.setDoseMl(dispenserScheduling.getDoseMl());
        dispenserSchedulingDTO.setIsActive(dispenserScheduling.getIsActive());
        dispenserSchedulingDTO.setCreatedTimestamp(dispenserScheduling.getCreatedTimestamp());
        dispenserSchedulingDTO.setUpdatedTimestamp(dispenserScheduling.getUpdatedTimestamp());
        dispenserSchedulingDTO.setDispenseScheduleElementId(dispenserScheduling.getDispenseScheduleElement() == null ? null : dispenserScheduling.getDispenseScheduleElement().getId());
        return dispenserSchedulingDTO;
    }

    @Override
    public DispenserScheduling mapToEntity(final DispenserSchedulingDTO dispenserSchedulingDTO,
                                           final DispenserScheduling dispenserScheduling) {
        dispenserScheduling.setIndex(dispenserSchedulingDTO.getIndex());
        dispenserScheduling.setLabel(dispenserSchedulingDTO.getLabel());
        dispenserScheduling.setDoseMl(dispenserSchedulingDTO.getDoseMl());
        dispenserScheduling.setIsActive(dispenserSchedulingDTO.getIsActive());
        dispenserScheduling.setCreatedTimestamp(dispenserSchedulingDTO.getCreatedTimestamp());
        dispenserScheduling.setUpdatedTimestamp(dispenserSchedulingDTO.getUpdatedTimestamp());
        var dispenseScheduleElement = dispenserSchedulingDTO.getDispenseScheduleElementId() == null ? null : dispenseScheduleElementRepository.findById(dispenserSchedulingDTO.getDispenseScheduleElementId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "dispenseScheduleElement not found"));
        dispenserScheduling.setDispenseScheduleElement(dispenseScheduleElement);
        return dispenserScheduling;
    }

}

package ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ua.com.cyberdone.devicemicroservice.device.domain.Device;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.domain.Dispenser;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.model.DispenserDTO;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.model.DispenserTemplateDTO;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.repos.DispenserRepository;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.repos.DispenserTemplateRepository;
import ua.com.cyberdone.devicemicroservice.device.repos.DeviceRepository;
import ua.com.cyberdone.devicemicroservice.device.service.ModelEntityMapper;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;


@Transactional
@Service
@RequiredArgsConstructor
public class DispenserService implements ModelEntityMapper<Dispenser, DispenserDTO> {
    private final DispenserRepository dispenserRepository;
    private final DeviceRepository deviceRepository;
    private final DispenserTemplateRepository dispenserTemplateRepository;
    private final DispenserTemplateService dispenserTemplateService;

    public List<DispenserDTO> findAll() {
        return dispenserRepository.findAll(Sort.by("id"))
                .stream()
                .map(dispenser -> mapToDTO(dispenser, new DispenserDTO()))
                .collect(Collectors.toList());
    }

    public DispenserDTO get(final Long id) {
        return dispenserRepository.findById(id)
                .map(dispenser -> mapToDTO(dispenser, new DispenserDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public DispenserDTO create(final DispenserDTO dispenserDTO) {
        final Dispenser dispenser = new Dispenser();
        mapToEntity(dispenserDTO, dispenser);
        return mapToDTO(dispenserRepository.save(dispenser), new DispenserDTO());
    }

    public DispenserDTO update(final Long id, final DispenserDTO dispenserDTO) {
        final Dispenser dispenser = dispenserRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(dispenserDTO, dispenser);
        return mapToDTO(dispenserRepository.save(dispenser), new DispenserDTO());
    }

    public void delete(final Long id) {
        dispenserRepository.deleteById(id);
    }

    @Override
    public DispenserDTO mapToDTO(final Dispenser dispenser, final DispenserDTO dispenserDTO) {
        dispenserDTO.setId(dispenser.getId());
        dispenserDTO.setUpdatedTimestamp(dispenser.getUpdatedTimestamp());
        dispenserDTO.setDeviceUuid(dispenser.getDevice() == null ? null : dispenser.getDevice().getUuid());
        dispenserDTO.setDispenserTemplateDTO(dispenserTemplateService.mapToDTO(dispenser.getDispenserTemplate(), new DispenserTemplateDTO()));
        return dispenserDTO;
    }

    @Override
    public Dispenser mapToEntity(final DispenserDTO dispenserDTO, final Dispenser dispenser) {
        dispenser.setUpdatedTimestamp(dispenserDTO.getUpdatedTimestamp());
        final Device device = dispenserDTO.getDeviceUuid() == null ? null : deviceRepository.findByUuid(dispenserDTO.getDeviceUuid())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "device not found"));
        dispenser.setDevice(device);
        var dispenserTemplate = dispenserTemplateRepository.findById(dispenserDTO.getDispenserTemplateDTO().getId())
                .orElse(null);

        dispenser.setDispenserTemplate(dispenserTemplate);
        return dispenser;
    }

    public Page<DispenserDTO> findAllByDeviceUuid(String uuid, Pageable pageable) {
        return new PageImpl<>(dispenserRepository.findDispensersByDevice(uuid, pageable).stream()
                .map(dispenser -> {
                    DispenserDTO dispenserDTO = new DispenserDTO();
                    dispenserDTO.setId(dispenser.getId());
                    dispenserDTO.setUpdatedTimestamp(dispenser.getUpdatedTimestamp());
                    dispenserDTO.setDeviceUuid(dispenser.getDevice() == null ? null : dispenser.getDevice().getUuid());
                    dispenserDTO.setDispenserTemplateDTO(dispenserTemplateRepository.findById(dispenser.getDispenserTemplate().getId())
                            .map(dispenserTemplate -> dispenserTemplateService.mapToDTO(dispenserTemplate, new DispenserTemplateDTO()))
                            .orElse(null));
                    return dispenserDTO;
                }).collect(Collectors.toList()));
    }
}

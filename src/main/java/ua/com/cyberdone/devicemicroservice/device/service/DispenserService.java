package ua.com.cyberdone.devicemicroservice.device.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ua.com.cyberdone.devicemicroservice.device.domain.Device;
import ua.com.cyberdone.devicemicroservice.device.domain.Dispenser;
import ua.com.cyberdone.devicemicroservice.device.domain.DispenserTemplate;
import ua.com.cyberdone.devicemicroservice.device.model.DispenserDTO;
import ua.com.cyberdone.devicemicroservice.device.repos.DeviceRepository;
import ua.com.cyberdone.devicemicroservice.device.repos.DispenserRepository;
import ua.com.cyberdone.devicemicroservice.device.repos.DispenserTemplateRepository;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Transactional
@Service
@RequiredArgsConstructor
public class DispenserService implements ModelEntityMapper<Dispenser, DispenserDTO> {
    private final DispenserRepository dispenserRepository;
    private final DeviceRepository deviceRepository;
    private final DispenserTemplateRepository dispenserTemplateRepository;

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

    public Long create(final DispenserDTO dispenserDTO) {
        final Dispenser dispenser = new Dispenser();
        mapToEntity(dispenserDTO, dispenser);
        return dispenserRepository.save(dispenser).getId();
    }

    public void update(final Long id, final DispenserDTO dispenserDTO) {
        final Dispenser dispenser = dispenserRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(dispenserDTO, dispenser);
        dispenserRepository.save(dispenser);
    }

    public void delete(final Long id) {
        dispenserRepository.deleteById(id);
    }

    @Override
    public DispenserDTO mapToDTO(final Dispenser dispenser, final DispenserDTO dispenserDTO) {
        dispenserDTO.setId(dispenser.getId());
        dispenserDTO.setUpdatedTimestamp(dispenser.getUpdatedTimestamp());
        dispenserDTO.setDeviceUuid(dispenser.getDevice() == null ? null : dispenser.getDevice().getUuid());
        dispenserDTO.setDispenserTemplateIds(dispenser.getDispenserTemplateList() == null ? null : dispenser.getDispenserTemplateList().stream()
                .map(DispenserTemplate::getId)
                .collect(Collectors.toList()));
        return dispenserDTO;
    }

    @Override
    public Dispenser mapToEntity(final DispenserDTO dispenserDTO, final Dispenser dispenser) {
        dispenser.setUpdatedTimestamp(dispenserDTO.getUpdatedTimestamp());
        final Device device = dispenserDTO.getDeviceUuid() == null ? null : deviceRepository.findByUuid(dispenserDTO.getDeviceUuid())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "device not found"));
        dispenser.setDevice(device);
        final List<DispenserTemplate> dispenserTemplateList = dispenserTemplateRepository.findAllById(
                dispenserDTO.getDispenserTemplateIds() == null ? Collections.emptyList() : dispenserDTO.getDispenserTemplateIds());
        if (dispenserTemplateList.size() != (dispenserDTO.getDispenserTemplateIds() == null ? 0 : dispenserDTO.getDispenserTemplateIds().size())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "one of dispenserTemplateList not found");
        }
        dispenser.setDispenserTemplateList(dispenserTemplateList);
        return dispenser;
    }

}

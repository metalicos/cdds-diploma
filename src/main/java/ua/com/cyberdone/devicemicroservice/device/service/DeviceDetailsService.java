package ua.com.cyberdone.devicemicroservice.device.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ua.com.cyberdone.devicemicroservice.device.domain.Device;
import ua.com.cyberdone.devicemicroservice.device.domain.DeviceDetails;
import ua.com.cyberdone.devicemicroservice.device.model.DeviceDetailsDTO;
import ua.com.cyberdone.devicemicroservice.device.repos.DeviceDetailsRepository;
import ua.com.cyberdone.devicemicroservice.device.repos.DeviceRepository;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class DeviceDetailsService implements ModelEntityMapper<DeviceDetails, DeviceDetailsDTO> {

    private final DeviceDetailsRepository deviceDetailsRepository;
    private final DeviceRepository deviceRepository;

    public List<DeviceDetailsDTO> findAll() {
        return deviceDetailsRepository.findAll(Sort.by("id"))
                .stream()
                .map(deviceDetails -> mapToDTO(deviceDetails, new DeviceDetailsDTO()))
                .collect(Collectors.toList());
    }

    public DeviceDetailsDTO get(final Long id) {
        return deviceDetailsRepository.findById(id)
                .map(deviceDetails -> mapToDTO(deviceDetails, new DeviceDetailsDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final DeviceDetailsDTO deviceDetailsDTO) {
        final DeviceDetails deviceDetails = new DeviceDetails();
        mapToEntity(deviceDetailsDTO, deviceDetails);
        return deviceDetailsRepository.save(deviceDetails).getId();
    }

    public void update(final Long id, final DeviceDetailsDTO deviceDetailsDTO) {
        final DeviceDetails deviceDetails = deviceDetailsRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(deviceDetailsDTO, deviceDetails);
        deviceDetailsRepository.save(deviceDetails);
    }

    public void delete(final Long id) {
        deviceDetailsRepository.deleteById(id);
    }

    @Override
    public DeviceDetailsDTO mapToDTO(final DeviceDetails deviceDetails,
                                     final DeviceDetailsDTO deviceDetailsDTO) {
        deviceDetailsDTO.setId(deviceDetails.getId());
        deviceDetailsDTO.setVersion(deviceDetails.getVersion());
        deviceDetailsDTO.setModification(deviceDetails.getModification());
        deviceDetailsDTO.setManufacturedTimestamp(deviceDetails.getManufacturedTimestamp());
        deviceDetailsDTO.setManufacturedCountry(deviceDetails.getManufacturedCountry());
        deviceDetailsDTO.setSoldTimestamp(deviceDetails.getSoldTimestamp());
        deviceDetailsDTO.setSoldCountry(deviceDetails.getSoldCountry());
        deviceDetailsDTO.setWarrantyFrom(deviceDetails.getWarrantyFrom());
        deviceDetailsDTO.setWarrantyTo(deviceDetails.getWarrantyTo());
        deviceDetailsDTO.setDeviceId(deviceDetails.getDevice() == null ? null : deviceDetails.getDevice().getId());
        return deviceDetailsDTO;
    }

    @Override
    public DeviceDetails mapToEntity(final DeviceDetailsDTO deviceDetailsDTO,
                                     final DeviceDetails deviceDetails) {
        deviceDetails.setVersion(deviceDetailsDTO.getVersion());
        deviceDetails.setModification(deviceDetailsDTO.getModification());
        deviceDetails.setManufacturedTimestamp(deviceDetailsDTO.getManufacturedTimestamp());
        deviceDetails.setManufacturedCountry(deviceDetailsDTO.getManufacturedCountry());
        deviceDetails.setSoldTimestamp(deviceDetailsDTO.getSoldTimestamp());
        deviceDetails.setSoldCountry(deviceDetailsDTO.getSoldCountry());
        deviceDetails.setWarrantyFrom(deviceDetailsDTO.getWarrantyFrom());
        deviceDetails.setWarrantyTo(deviceDetailsDTO.getWarrantyTo());
        final Device device = deviceDetailsDTO.getDeviceId() == null ? null : deviceRepository.findById(deviceDetailsDTO.getDeviceId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "device not found"));
        deviceDetails.setDevice(device);
        return deviceDetails;
    }

}

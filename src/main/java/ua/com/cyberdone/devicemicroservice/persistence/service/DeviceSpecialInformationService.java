package ua.com.cyberdone.devicemicroservice.persistence.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ua.com.cyberdone.devicemicroservice.model.microcontrollers.hydroponic.HydroponicDataDto;
import ua.com.cyberdone.devicemicroservice.persistence.entity.DeviceSpecialInformation;
import ua.com.cyberdone.devicemicroservice.persistence.repository.DeviceSpecialInformationRepository;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoField.SECOND_OF_DAY;
import static ua.com.cyberdone.devicemicroservice.constant.ValidationConstants.VALUE_IS_NULL_MSG;

@Service
@RequiredArgsConstructor
public class DeviceSpecialInformationService {
    private final ModelMapper modelMapper;
    private final DeviceSpecialInformationRepository specialInformationRepository;

    @Transactional
    public void saveSpecialInformation(
            @NotNull(message = VALUE_IS_NULL_MSG) DeviceSpecialInformation specialInformation) {
        specialInformationRepository.save(specialInformation);
    }

    @Transactional
    public void deleteDataById(Long id) {
        specialInformationRepository.deleteById(id);
    }

    public List<HydroponicDataDto> getLastDataByUuid(String uuid, int page, int limit) {
        System.gc();
        return specialInformationRepository.findLastInformation(uuid, PageRequest.of(page, limit)).stream()
                .map(d -> modelMapper.map(d, HydroponicDataDto.class))
                .sorted(Comparator.comparingLong(v -> v.getMicrocontrollerTime().getLong(SECOND_OF_DAY)))
                .collect(Collectors.toList());
    }
}

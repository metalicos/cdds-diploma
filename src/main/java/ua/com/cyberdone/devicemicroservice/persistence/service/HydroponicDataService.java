package ua.com.cyberdone.devicemicroservice.persistence.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ua.com.cyberdone.devicemicroservice.model.microcontrollers.hydroponic.HydroponicDataDto;
import ua.com.cyberdone.devicemicroservice.persistence.entity.hydroponic.HydroponicData;
import ua.com.cyberdone.devicemicroservice.persistence.repository.HydroponicDataRepository;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoField.SECOND_OF_DAY;
import static ua.com.cyberdone.devicemicroservice.constant.ValidationConstants.VALUE_IS_NULL_MSG;

@Service
@RequiredArgsConstructor
public class HydroponicDataService {
    private final ModelMapper modelMapper;
    private final HydroponicDataRepository hydroponicDataRepository;

    @Transactional
    public void saveData(@NotNull(message = VALUE_IS_NULL_MSG) HydroponicData data) {
        hydroponicDataRepository.save(data);
    }

    @Transactional
    public void deleteDataByUuid(String uuid) {
        hydroponicDataRepository.deleteAllDataForUuid(uuid);
    }

    public List<HydroponicDataDto> getLastDataByUuid(String uuid, int page, int limit) {
        System.gc();
        return hydroponicDataRepository.findLastData(uuid, PageRequest.of(page, limit)).stream()
                .map(d -> modelMapper.map(d, HydroponicDataDto.class))
                .sorted(Comparator.comparingLong(v -> v.getMicrocontrollerTime().getLong(SECOND_OF_DAY)))
                .collect(Collectors.toList());
    }
}

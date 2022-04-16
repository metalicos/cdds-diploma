package ua.com.cyberdone.devicemicroservice.persistence.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ua.com.cyberdone.devicemicroservice.persistence.entity.hydroponic.HydroponicData;
import ua.com.cyberdone.devicemicroservice.persistence.model.microcontrollers.hydroponic.HydroponicDataDto;
import ua.com.cyberdone.devicemicroservice.persistence.repository.HydroponicDataRepository;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoField.SECOND_OF_DAY;
import static ua.com.cyberdone.devicemicroservice.util.ValidationConstants.VALUE_IS_NULL_MSG;

@Slf4j
@Service
@RequiredArgsConstructor
public class HydroponicDataService {
    private final ModelMapper modelMapper;
    private final HydroponicDataRepository hydroponicDataRepository;

    @Transactional
    public HydroponicData saveData(@NotNull(message = VALUE_IS_NULL_MSG) HydroponicData data) {
        try {
            return hydroponicDataRepository.save(data);
        } catch (Exception ex) {
            log.error("Cannot save hydroponic data. Reason: ", ex);
            return null;
        }
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

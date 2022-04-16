package ua.com.cyberdone.devicemicroservice.persistence.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ua.com.cyberdone.devicemicroservice.persistence.entity.hydroponic.HydroponicCalibrationData;
import ua.com.cyberdone.devicemicroservice.persistence.model.microcontrollers.hydroponic.HydroponicCalibrationDataDto;
import ua.com.cyberdone.devicemicroservice.persistence.repository.HydroponicCalibrationDataRepository;

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
public class HydroponicCalibrationDataService {
    private final ModelMapper modelMapper;
    private final HydroponicCalibrationDataRepository calibrationDataRepository;

    @Transactional
    public HydroponicCalibrationData saveCalibrationData(@NotNull(message = VALUE_IS_NULL_MSG) HydroponicCalibrationData data) {
        try {
            return calibrationDataRepository.save(data);
        } catch (Exception ex) {
            log.error("Cannot save hydroponic calibration data. Reason: ", ex);
            return null;
        }
    }

    @Transactional
    public void deleteCalibrationByUuid(String uuid) {
        calibrationDataRepository.deleteAllDataForUuid(uuid);
    }

    public List<HydroponicCalibrationDataDto> getLastCalibrationByUuid(String uuid, int page, int limit) {
        System.gc();
        return calibrationDataRepository.findLastData(uuid, PageRequest.of(page, limit)).stream()
                .map(d -> modelMapper.map(d, HydroponicCalibrationDataDto.class))
                .sorted(Comparator.comparingLong(v -> v.getMicrocontrollerTime().getLong(SECOND_OF_DAY)))
                .collect(Collectors.toList());
    }
}

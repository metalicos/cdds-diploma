package ua.com.cyberdone.devicemicroservice.persistence.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ua.com.cyberdone.devicemicroservice.model.dto.microcontrollers.hydroponic.HydroponicCalibrationDataDto;
import ua.com.cyberdone.devicemicroservice.persistence.entity.hydroponic.HydroponicCalibrationData;
import ua.com.cyberdone.devicemicroservice.persistence.repository.HydroponicCalibrationDataRepository;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoField.SECOND_OF_DAY;
import static ua.com.cyberdone.devicemicroservice.validation.ValidationConstants.VALUE_IS_NULL_MSG;

@Service
@RequiredArgsConstructor
public class HydroponicCalibrationDataService {
    private final ModelMapper modelMapper;
    private final HydroponicCalibrationDataRepository calibrationDataRepository;

    @Transactional
    public void saveCalibrationData(@NotNull(message = VALUE_IS_NULL_MSG) HydroponicCalibrationData data) {
        calibrationDataRepository.save(data);
    }

    @Transactional
    public void deleteCalibrationById(Long id) {
        calibrationDataRepository.deleteById(id);
    }

    public List<HydroponicCalibrationDataDto> getLastCalibrationByUuid(String uuid, int page, int limit) {
        return calibrationDataRepository.findLastData(uuid, PageRequest.of(page, limit)).stream()
                .map(d -> modelMapper.map(d, HydroponicCalibrationDataDto.class))
                .sorted(Comparator.comparingLong(v -> v.getMicrocontrollerTime().getLong(SECOND_OF_DAY)))
                .collect(Collectors.toList());
    }
}

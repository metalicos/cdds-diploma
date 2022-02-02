package ua.com.cyberdone.devicemicroservice.persistence.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ua.com.cyberdone.devicemicroservice.model.dto.microcontrollers.hydroponic.HydroponicSettingsDto;
import ua.com.cyberdone.devicemicroservice.persistence.entity.hydroponic.HydroponicSettings;
import ua.com.cyberdone.devicemicroservice.persistence.repository.HydroponicSettingsRepository;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoField.SECOND_OF_DAY;
import static ua.com.cyberdone.devicemicroservice.validation.ValidationConstants.VALUE_IS_NULL_MSG;

@Service
@RequiredArgsConstructor
public class HydroponicSettingsService {
    private final ModelMapper modelMapper;
    private final HydroponicSettingsRepository hydroponicSettingsRepository;

    @Transactional
    public void saveSetting(@NotNull(message = VALUE_IS_NULL_MSG) HydroponicSettings message) {
        hydroponicSettingsRepository.save(message);
    }

    public List<HydroponicSettingsDto> getLastSettingsByUuid(String uuid, int page, int limit) {
        return hydroponicSettingsRepository.findLastSettings(uuid, PageRequest.of(page, limit)).stream()
                .map(d -> modelMapper.map(d, HydroponicSettingsDto.class))
                .sorted(Comparator.comparingLong(v -> v.getMicrocontrollerTime().getLong(SECOND_OF_DAY)))
                .collect(Collectors.toList());
    }
}

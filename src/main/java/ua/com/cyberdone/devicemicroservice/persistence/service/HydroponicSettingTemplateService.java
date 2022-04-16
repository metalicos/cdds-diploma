package ua.com.cyberdone.devicemicroservice.persistence.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ua.com.cyberdone.devicemicroservice.persistence.entity.hydroponic.HydroponicSettingTemplate;
import ua.com.cyberdone.devicemicroservice.persistence.model.microcontrollers.hydroponic.HydroponicSettingTemplateDto;
import ua.com.cyberdone.devicemicroservice.persistence.model.microcontrollers.hydroponic.HydroponicSettingsDto;
import ua.com.cyberdone.devicemicroservice.persistence.repository.HydroponicSettingTemplateRepository;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoField.SECOND_OF_DAY;

@Service
@RequiredArgsConstructor
public class HydroponicSettingTemplateService {
    private final ModelMapper modelMapper;
    private final HydroponicSettingTemplateRepository hydroponicSettingTemplateRepository;

    public HydroponicSettingTemplateDto saveHydroponicSettingTemplate(HydroponicSettingTemplateDto settingTemplateDto) {
        var settTemplate = modelMapper.map(settingTemplateDto, HydroponicSettingTemplate.class);
        return modelMapper.map(settTemplate, HydroponicSettingTemplateDto.class);
    }

    public void deleteHydroponicSettingTemplate(Long templateId) {
        hydroponicSettingTemplateRepository.deleteById(templateId);
    }

    public void updateHydroponicSettingTemplate(HydroponicSettingTemplateDto settingTemplateDto) {
        var settTemplate = modelMapper.map(settingTemplateDto, HydroponicSettingTemplate.class);
        hydroponicSettingTemplateRepository.findById(settTemplate.getId())
                .ifPresentOrElse(settings -> hydroponicSettingTemplateRepository.updateHydroponicSettings(
                                settTemplate.getUserId(), settTemplate.getName(), settTemplate.getDescription(),
                                settTemplate.getSetupPhValue(), settTemplate.getSetupTdsValue(),
                                settTemplate.getRegulateErrorPh(), settTemplate.getRegulateErrorFertilizer(),
                                settTemplate.getMlPerMillisecond(), settTemplate.getPhUpDoseMl(),
                                settTemplate.getPhDownDoseMl(), settTemplate.getFertilizerDoseMl(),
                                settTemplate.getRecheckDispensersAfterMs(), settTemplate.getRestartCounter(),
                                settTemplate.getDispensersEnable(), settTemplate.getSensorsEnable(),
                                settTemplate.getAutotime(), settTemplate.getTimeZone(), settTemplate.getWifiSsid(),
                                settTemplate.getWifiPass(), settTemplate.getMicrocontrollerTime(), LocalDateTime.now())
                        , () -> hydroponicSettingTemplateRepository.save(settTemplate));
    }

    public List<HydroponicSettingsDto> getLastHydroponicSettingTemplates(Long userId, int page, int limit) {
        System.gc();
        return hydroponicSettingTemplateRepository.findAllByUserId(userId, PageRequest.of(page, limit)).stream()
                .map(d -> modelMapper.map(d, HydroponicSettingsDto.class))
                .sorted(Comparator.comparingLong(v -> v.getCreatedTimestamp().getLong(SECOND_OF_DAY)))
                .collect(Collectors.toList());
    }
}

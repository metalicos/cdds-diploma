package ua.com.cyberdone.devicemicroservice.model.dto.microcontrollers.hydroponic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class HydroponicSettingTemplateDto {
    private Long id;
    private String name;
    private String description;
    private Long userId;
    private Double setupPhValue;
    private Long setupTdsValue;
    private Double regulateErrorPh;
    private Double regulateErrorFertilizer;
    private Double mlPerMillisecond;
    private Double phUpDoseMl;
    private Double phDownDoseMl;
    private Double fertilizerDoseMl;
    private Long recheckDispensersAfterMs;
    private Long restartCounter;
    private Boolean dispensersEnable;
    private Boolean sensorsEnable;
    private Boolean autotime;
    private String timeZone;
    private String wifiSsid;
    private String wifiPass;
    private LocalDateTime microcontrollerTime;
    private LocalDateTime createdTimestamp;
    private LocalDateTime updatedTimestamp;
}

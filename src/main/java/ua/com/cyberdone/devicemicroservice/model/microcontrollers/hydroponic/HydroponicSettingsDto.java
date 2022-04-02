package ua.com.cyberdone.devicemicroservice.model.microcontrollers.hydroponic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HydroponicSettingsDto {
    private String uuid;
    private Double mlPerMillisecond;
    private Double regulateErrorPh;
    private Double regulateErrorFertilizer;
    private Double phUpDoseMl;
    private Double phDownDoseMl;
    private Double fertilizerDoseMl;
    private Long recheckDispensersAfterMs;
    private Double setupPhValue;
    private Long setupTdsValue;
    private Boolean dispensersEnable;
    private Boolean sensorsEnable;
    private Long restartCounter;
    private String wifiSSID;
    private String wifiPASS;
    private Boolean autotime;
    private String timeZone;
    private LocalDateTime microcontrollerTime;
    private LocalDateTime createdTimestamp;
    private LocalDateTime updatedTimestamp;
}

package ua.com.cyberdone.devicemicroservice.persistence.model.microcontrollers.hydroponic;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HydroponicAllDataDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 756734223L;

    private Double phValue;
    private Double temperatureValue;
    private Double tdsValue;
    private Boolean isDispenserPhUpOpen;
    private Boolean isDispenserPhDownOpen;
    private Boolean isDispenserTdsOpen;
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
    private Integer wifiRssi;
    private String wifiBsid;
    private String localIp;
    private String subnetMask;
    private String gatewayIP;
    private String macAddr;

    private Double tdsCalibrationCoefficientValue;
    private Integer tdsOversampling;
    private Integer phCalibrationValuePoint;
    private Double phCalibrationEtalonValue1;
    private Double phCalibrationEtalonValue2;
    private Long phCalibrationAdcValue1;
    private Long phCalibrationAdcValue2;
    private Double phCalibrationSlope;
    private Long phCalibrationValueOffset;
    private Integer phOversampling;
    private LocalDateTime microcontrollerTime;
    private String uuid;
}
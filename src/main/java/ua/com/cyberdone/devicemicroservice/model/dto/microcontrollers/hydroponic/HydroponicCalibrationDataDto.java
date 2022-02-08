package ua.com.cyberdone.devicemicroservice.model.dto.microcontrollers.hydroponic;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HydroponicCalibrationDataDto {
    public Double tdsCalibrationCoefficientValue;
    public Integer tdsOversampling;
    public Integer phCalibrationValuePoint;
    public Double phCalibrationEtalonValue1;
    public Double phCalibrationEtalonValue2;
    public Long phCalibrationAdcValue1;
    public Long phCalibrationAdcValue2;
    public Double phCalibrationSlope;
    public Long phCalibrationValueOffset;
    public Integer phOversampling;
    private Long id;
    private String uuid;
    private LocalDateTime microcontrollerTime;
    private LocalDateTime createdTimestamp;
    private LocalDateTime updatedTimestamp;
}

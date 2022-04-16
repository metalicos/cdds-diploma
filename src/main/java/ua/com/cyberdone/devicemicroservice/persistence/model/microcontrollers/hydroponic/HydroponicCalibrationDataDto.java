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
public class HydroponicCalibrationDataDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 37223999953L;

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

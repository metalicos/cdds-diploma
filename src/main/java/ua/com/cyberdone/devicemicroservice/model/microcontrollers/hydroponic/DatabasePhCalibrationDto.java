package ua.com.cyberdone.devicemicroservice.model.microcontrollers.hydroponic;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DatabasePhCalibrationDto {
    private String uuid;
    private Integer calibrationValuePoint;
    private Double calibrationReferenceValue1;
    private Double calibrationReferenceValue2;
    private Long calibrationAdcValue1;
    private Long calibrationAdcValue2;
    private Double calibrationSlope;
    private Long calibrationValueOffset;
    private Integer oversampling;
}

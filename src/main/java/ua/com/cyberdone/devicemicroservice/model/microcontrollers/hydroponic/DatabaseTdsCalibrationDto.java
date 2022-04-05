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
public class DatabaseTdsCalibrationDto {
    private String uuid;
    private Double calibrationCoefficientValue;
    private Integer oversampling;
}

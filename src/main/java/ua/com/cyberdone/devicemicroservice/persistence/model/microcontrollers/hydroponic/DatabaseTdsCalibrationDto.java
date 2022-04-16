package ua.com.cyberdone.devicemicroservice.persistence.model.microcontrollers.hydroponic;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DatabaseTdsCalibrationDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 37211123423L;

    private String uuid;
    private Double calibrationCoefficientValue;
    private Integer oversampling;
}

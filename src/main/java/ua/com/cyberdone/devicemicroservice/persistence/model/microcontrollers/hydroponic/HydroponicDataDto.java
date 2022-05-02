package ua.com.cyberdone.devicemicroservice.persistence.model.microcontrollers.hydroponic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HydroponicDataDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 111111111145L;
    private Double phValue;
    private Double temperatureValue;
    private Integer tdsValue;
    private LocalDateTime microcontrollerTime;
    private LocalDateTime createdTimestamp;
    private LocalDateTime updatedTimestamp;
}

package ua.com.cyberdone.devicemicroservice.model.dto.microcontrollers.hydroponic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HydroponicDataDto {
    private String uuid;
    private Double phValue;
    private Double temperatureValue;
    private Integer tdsValue;
    private LocalDateTime microcontrollerTime;
    private LocalDateTime createdTimestamp;
    private LocalDateTime updatedTimestamp;
}

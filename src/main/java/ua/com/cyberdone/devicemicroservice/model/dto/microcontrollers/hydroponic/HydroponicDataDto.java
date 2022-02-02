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
    private LocalDateTime receiveTime;
    private Double phValue;
    private Double temperatureValue;
    private Double ecValue;
    private Integer tdsValue;
    private LocalDateTime microcontrollerTime;
}

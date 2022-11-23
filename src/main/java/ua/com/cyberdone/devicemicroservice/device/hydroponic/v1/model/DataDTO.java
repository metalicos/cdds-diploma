package ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DataDTO {
    private Long id;
    private Double ecSolution;
    private Double phSolution;
    private Double tSolution;
    private Double tAir;
    private Double humidityAir;
    private Double atmosphericPressure;
    private LocalDateTime createdTimestamp;
    private String deviceUuid;
}

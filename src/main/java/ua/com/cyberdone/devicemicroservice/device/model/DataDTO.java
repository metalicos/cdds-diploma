package ua.com.cyberdone.devicemicroservice.device.model;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@RequiredArgsConstructor
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
    private LocalDateTime updatedTimestamp;
    private String deviceUuid;
}

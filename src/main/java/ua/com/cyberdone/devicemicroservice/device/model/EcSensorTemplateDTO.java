package ua.com.cyberdone.devicemicroservice.device.model;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class EcSensorTemplateDTO {
    private Long id;
    private String name;
    private String description;
    private Long ownerId;
    private LocalDateTime time;
    private Double kLowPoint;
    private Double kHighPoint;
    private Double rawEc;
    private LocalDateTime createdTimestamp;
    private LocalDateTime updatedTimestamp;
}

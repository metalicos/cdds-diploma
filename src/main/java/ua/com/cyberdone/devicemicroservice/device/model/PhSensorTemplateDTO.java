package ua.com.cyberdone.devicemicroservice.device.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class PhSensorTemplateDTO {
    private Long id;
    private String name;
    private String description;
    private Long ownerId;
    private LocalDateTime time;
    private Double point;
    private Double value;
    private Integer adc;
    private Double slope;
    private Integer adcOffset;
    private Integer oversampling;
    private LocalDateTime createdTimestamp;
    private LocalDateTime updatedTimestamp;
    private List<Long> phSensorTemplateIds;
}

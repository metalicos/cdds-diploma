package ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhSensorTemplateDTO {
    private Long id;
    private String name;
    private String description;
    private Long ownerId;
    private LocalDateTime time;
    private Double point;
    private List<Double> value;
    private List<Integer> adc;
    private Double slope;
    private Integer adcOffset;
    private Integer oversampling;
    private LocalDateTime createdTimestamp;
    private LocalDateTime updatedTimestamp;
    private List<Long> phSensorTemplateIds;
}

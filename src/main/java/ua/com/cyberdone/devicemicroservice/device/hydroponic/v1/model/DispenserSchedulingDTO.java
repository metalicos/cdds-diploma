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
public class DispenserSchedulingDTO {
    private Long id;
    private Integer index;
    private String label;
    private Double doseMl;
    private Boolean isActive;
    private LocalDateTime createdTimestamp;
    private LocalDateTime updatedTimestamp;
    private Long dispenseScheduleElementId;
}

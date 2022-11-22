package ua.com.cyberdone.devicemicroservice.device.model;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@RequiredArgsConstructor
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

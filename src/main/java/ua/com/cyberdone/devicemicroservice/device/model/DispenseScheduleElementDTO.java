package ua.com.cyberdone.devicemicroservice.device.model;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class DispenseScheduleElementDTO {
    private Long id;
    private Integer index;
    private LocalDateTime time;
    private Integer day;
    private Double targetEc;
    private Double targetPh;
    private Double ecError;
    private Double phError;
    private Long recheckAfterSec;
    private LocalDateTime createdTimestamp;
    private LocalDateTime updatedTimestamp;
    private Long dispenseScheduleTemplateId;
}

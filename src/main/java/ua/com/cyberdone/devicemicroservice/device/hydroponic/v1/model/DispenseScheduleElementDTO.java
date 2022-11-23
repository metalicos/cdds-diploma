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

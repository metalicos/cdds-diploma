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
public class DispenseScheduleTemplateDTO {
    private Long id;
    private String name;
    private String description;
    private Long ownerId;
    private LocalDateTime createdTimestamp;
    private LocalDateTime updatedTimestamp;
}

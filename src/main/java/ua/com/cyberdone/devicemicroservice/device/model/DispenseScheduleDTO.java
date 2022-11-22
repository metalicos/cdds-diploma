package ua.com.cyberdone.devicemicroservice.device.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class DispenseScheduleDTO {
    private Long id;
    private LocalDateTime updatedTimestamp;
    private Long deviceUuid;
    private List<Long> dispenseScheduleTemplateIds;
}

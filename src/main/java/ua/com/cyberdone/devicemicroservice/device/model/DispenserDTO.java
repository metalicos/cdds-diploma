package ua.com.cyberdone.devicemicroservice.device.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class DispenserDTO {
    private Long id;
    private LocalDateTime updatedTimestamp;
    private String deviceUuid;
    private List<Long> dispenserTemplateIds;
}

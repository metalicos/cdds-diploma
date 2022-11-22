package ua.com.cyberdone.devicemicroservice.device.model;

import lombok.*;
import ua.com.cyberdone.devicemicroservice.device.domain.DispenserTemplate;

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
    private DispenserTemplateDTO dispenserTemplateDTO;
}

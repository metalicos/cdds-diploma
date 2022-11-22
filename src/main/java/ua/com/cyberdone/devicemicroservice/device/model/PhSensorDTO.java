package ua.com.cyberdone.devicemicroservice.device.model;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class PhSensorDTO {
    private Long id;
    private LocalDateTime updatedTimestamp;
    private String deviceUuid;
}

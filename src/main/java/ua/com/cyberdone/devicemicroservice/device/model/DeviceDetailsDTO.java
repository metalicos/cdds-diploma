package ua.com.cyberdone.devicemicroservice.device.model;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class DeviceDetailsDTO {
    private Long id;
    private String version;
    private String modification;
    private LocalDateTime manufacturedTimestamp;
    private String manufacturedCountry;
    private LocalDateTime soldTimestamp;
    private String soldCountry;
    private LocalDateTime warrantyFrom;
    private LocalDateTime warrantyTo;
    private String deviceUuid;
}

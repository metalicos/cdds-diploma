package ua.com.cyberdone.devicemicroservice.device.model;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class SpecialSystemDTO {
    private Long id;
    private LocalDateTime time;
    private String softwareVersion;
    private Integer scheduleAmount;
    private Integer dispensersAmount;
    private String wifiRssi;
    private String wifiBsid;
    private String localIp;
    private String subnetMask;
    private String gatewayIp;
    private String macAddr;
    private LocalDateTime createdTimestamp;
    private LocalDateTime updatedTimestamp;
    private String deviceUuid;
}

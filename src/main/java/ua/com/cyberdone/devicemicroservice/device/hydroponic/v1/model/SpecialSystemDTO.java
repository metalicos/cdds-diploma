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

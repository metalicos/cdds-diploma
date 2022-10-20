package ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpecialSystemSettings implements Serializable {
    private String deviceUuid;
    private LocalDateTime time;
    private String softwareVersion;
    private int scheduleAmount;
    private int dispensersAmount;
    private String wifiRssi;
    private String wifiBsid;
    private String localIp;
    private String subnetMask;
    private String gatewayIp;
    private String macAddr;
}

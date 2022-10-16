package ua.com.cyberdone.devicemicroservice.model.hydroponic.v1;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpecialSystemSettings implements Serializable {
    private String uuid;
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

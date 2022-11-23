package ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.model.mqtt;

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
public class SystemSettings implements Serializable {
    private String deviceUuid;
    private LocalDateTime time;
    private String timeZone;
    private String ntpServer;
    private String wifiSSID;
    private String wifiPASS;
    private int restarts;
    private int growingDay;
    private boolean isGrowing;
    private int growStartDate;
    private boolean dispensersEnable;
    private boolean sensorsEnable;
}

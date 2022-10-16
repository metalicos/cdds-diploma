package ua.com.cyberdone.devicemicroservice.model.hydroponic.v1;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SystemSettings implements Serializable {
    private String uuid;
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

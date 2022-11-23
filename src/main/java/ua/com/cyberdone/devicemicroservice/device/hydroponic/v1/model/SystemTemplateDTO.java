package ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SystemTemplateDTO {
    private Long id;
    private String name;
    private String description;
    private Long ownerId;
    private LocalDateTime time;
    private String timeZone;
    private String ntpServer;
    private String wifiSsid;
    private String wifiPass;
    private Long restarts;
    private Integer growingDay;
    private Boolean isGrowing;
    private Long growStartDate;
    private Boolean dispensersEnable;
    private Boolean sensorsEnable;
    private LocalDateTime createdTimestamp;
    private LocalDateTime updatedTimestamp;
    private List<Long> systemTemplateIds;
}

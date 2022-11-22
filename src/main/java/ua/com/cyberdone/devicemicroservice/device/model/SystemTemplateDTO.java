package ua.com.cyberdone.devicemicroservice.device.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@RequiredArgsConstructor
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

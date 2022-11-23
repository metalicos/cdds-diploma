package ua.com.cyberdone.devicemicroservice.device.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeviceDTO {
    private Long id;
    private DeviceType type;
    private String uuid;
    private String name;
    private String description;
    private Long ownerId;
    private Integer delegatedAccountsNumber;
    private Boolean delegatable;
    private Integer repairedAmountNumber;
    private LocalDateTime createdTimestamp;
    private LocalDateTime lastRepairedTimestamp;
}

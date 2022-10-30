package ua.com.cyberdone.devicemicroservice.device.common.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@JsonPropertyOrder({"id", "uuid", "name", "description", "tariff", "ownerId",
        "delegationKey", "logo", "deviceTypeId", "createdTimestamp", "updatedTimestamp"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UiDeviceMetadata {
    private Long id;
    private String uuid;
    private String name;
    private String description;
    private String tariff;
    private Long ownerId;
    private String delegationKey;
    private String logo;
    private String deviceType;
    private LocalDateTime createdTimestamp;
    private LocalDateTime updatedTimestamp;
}

package ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DispenserTemplateDTO {
    private Long id;
    private String name;
    private String description;
    private Long ownerId;
    private Integer index;
    private LocalDateTime time;
    private String dispenserName;
    private Integer pinA;
    private Integer pinB;
    private Boolean regulationDirection;
    private Boolean enable;
    private Boolean polarity;
    private Boolean smartDose;
    private Double totalAddedMl;
    private Double mlPerMs;
    private Double targetValue;
    private Double error;
    private Long recheckDispenserAfterSeconds;
    private Long lastDispenserRecheckTime;
    private Integer mixingVolumeMl;
    private LocalDateTime updatedTimestamp;
}

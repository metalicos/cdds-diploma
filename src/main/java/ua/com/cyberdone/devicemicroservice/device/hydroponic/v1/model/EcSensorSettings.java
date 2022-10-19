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
public class EcSensorSettings implements Serializable {
    private String uuid;
    private LocalDateTime time;
    private double kLowPoint;
    private double kHighPoint;
    private double rawEc;
}
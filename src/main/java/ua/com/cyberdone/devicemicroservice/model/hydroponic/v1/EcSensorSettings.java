package ua.com.cyberdone.devicemicroservice.model.hydroponic.v1;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EcSensorSettings implements Serializable {
    private String uuid;
    private LocalDateTime time;
    private double kLowPoint;
    private double kHighPoint;
    private double rawEc;
}
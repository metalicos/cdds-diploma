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
public class DispenserSettings implements Serializable {
    private String uuid;
    private int index;
    private LocalDateTime time;
    private String dispenserName;
    private int pinA;
    private int pinB;
    private boolean regulationDirection;
    private boolean enable;
    private boolean polarity;
    private boolean smartDose;
    private double totalAddedMl;
    private double mlPerMs;
    private double targetValue;
    private double error;
    private int recheckDispenserAfterSeconds;
    private int lastDispenserRecheckTime;
    private int mixingVolumeMl;
}

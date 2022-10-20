package ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DispenseScheduleSettings implements Serializable {
    private String deviceUuid;
    private int index;
    private LocalDateTime time;
    private int day;
    private double targetEc;
    private double targetPh;
    private double ecError;
    private double phError;
    private int recheckAfterSec;
    private ArrayList<Boolean> isActive;
    private ArrayList<Double> doseMl;
}

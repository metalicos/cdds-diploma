package ua.com.cyberdone.devicemicroservice.model.hydroponic.v1;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DispenseScheduleSettings implements Serializable {
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

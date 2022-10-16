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
public class PhSensorSettings implements Serializable {
    private String uuid;
    private LocalDateTime time;
    private String point;
    private ArrayList<Double> value;
    private ArrayList<Integer> adc;
    private double slope;
    private int adcOffset;
    private int oversampling;
}
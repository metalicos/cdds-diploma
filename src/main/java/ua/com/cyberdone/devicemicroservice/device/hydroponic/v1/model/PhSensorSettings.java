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
public class PhSensorSettings implements Serializable {
    private String deviceUuid;
    private LocalDateTime time;
    private String point;
    private ArrayList<Double> value;
    private ArrayList<Integer> adc;
    private double slope;
    private int adcOffset;
    private int oversampling;
}
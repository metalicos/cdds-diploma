package ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DispenseSchedulesSettings implements Serializable {
    private String uuid;
    private int amount;
    private ArrayList<DispenseScheduleSettings> scheduleSettingsList;
}

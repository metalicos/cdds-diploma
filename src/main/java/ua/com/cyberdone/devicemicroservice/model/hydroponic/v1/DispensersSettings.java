package ua.com.cyberdone.devicemicroservice.model.hydroponic.v1;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DispensersSettings implements Serializable {
    private String uuid;
    private int amount;
    private ArrayList<DispenserSettings> settingsList;
}

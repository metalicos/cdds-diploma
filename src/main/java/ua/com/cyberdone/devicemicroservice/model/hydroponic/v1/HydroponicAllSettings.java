package ua.com.cyberdone.devicemicroservice.model.hydroponic.v1;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HydroponicAllSettings {
    private DispensersSettings dispensersSettings;
    private DispenseSchedulesSettings dispenseSchedulesSettings;
    private SystemSettings systemSettings;
    private SpecialSystemSettings specialSystemSettings;
    private EcSensorSettings ecSensorSettings;
    private PhSensorSettings phSensorSettings;
}

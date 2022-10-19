package ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HydroponicAllSettings {
    private String dispensersSettings;
    private String dispenseSchedulesSettings;
    private String systemSettings;
    private String specialSystemSettings;
    private String ecSensorSettings;
    private String phSensorSettings;
}

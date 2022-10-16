package ua.com.cyberdone.devicemicroservice.data.entity.hydroponic.v1;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import ua.com.cyberdone.devicemicroservice.model.hydroponic.v1.*;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("HYDROPONIC_DATA_V1")
public class HydroponicSettings {
    @Id
    @Column("id")
    private Long id;

    @Column("device_uuid")
    private String deviceUuid;

    @Column("dispensers_sett")
    private DispensersSettings dispensersSettings;

    @Column("dispense_schedules_sett")
    private DispenseSchedulesSettings dispenseSchedulesSettings;

    @Column("system_sett")
    private SystemSettings systemSettings;

    @Column("special_system_sett")
    private SpecialSystemSettings specialSystemSettings;

    @Column("ph_sensor_sett")
    private PhSensorSettings phSensorSettings;

    @Column("ec_sensor_sett")
    private EcSensorSettings ecSensorSettings;

    @Column("type")
    private String type;

    @Column("created_timestamp")
    private Timestamp createdTimestamp;
}
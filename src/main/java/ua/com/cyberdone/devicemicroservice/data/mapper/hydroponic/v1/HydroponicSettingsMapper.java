package ua.com.cyberdone.devicemicroservice.data.mapper.hydroponic.v1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;
import ua.com.cyberdone.devicemicroservice.data.entity.hydroponic.v1.HydroponicSettings;
import ua.com.cyberdone.devicemicroservice.model.hydroponic.v1.*;

import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
public class HydroponicSettingsMapper implements RowMapper<HydroponicSettings> {
    @Override
    public HydroponicSettings mapRow(ResultSet rs, int rowNum) throws SQLException {
        long start = System.currentTimeMillis();
        log.info("Start HydroponicSettingsV1Mapper.mapRow()");
        var settings = new HydroponicSettings();
        settings.setId(rs.getLong("id"));
        settings.setDeviceUuid(rs.getString("device_uuid"));
        settings.setDispensersSettings(rs.getObject("dispensers_sett", DispensersSettings.class));
        settings.setDispenseSchedulesSettings(rs.getObject("dispense_schedules_sett", DispenseSchedulesSettings.class));
        settings.setSystemSettings(rs.getObject("system_sett", SystemSettings.class));
        settings.setSpecialSystemSettings(rs.getObject("special_system_sett", SpecialSystemSettings.class));
        settings.setPhSensorSettings(rs.getObject("ph_sensor_sett", PhSensorSettings.class));
        settings.setEcSensorSettings(rs.getObject("ec_sensor_sett", EcSensorSettings.class));
        settings.setType(rs.getString("type"));
        settings.setCreatedTimestamp(rs.getTimestamp("created_timestamp"));

        log.info("End HydroponicSettingsV1Mapper.mapRow(). Taken: {} ms", System.currentTimeMillis() - start);
        return settings;
    }
}

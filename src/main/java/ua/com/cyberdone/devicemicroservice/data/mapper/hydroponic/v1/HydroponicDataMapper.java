package ua.com.cyberdone.devicemicroservice.data.mapper.hydroponic.v1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;
import ua.com.cyberdone.devicemicroservice.data.entity.hydroponic.v1.HydroponicData;

import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
public class HydroponicDataMapper implements RowMapper<HydroponicData> {
    @Override
    public HydroponicData mapRow(ResultSet rs, int rowNum) throws SQLException {
        long start = System.currentTimeMillis();
        log.info("Start HydroponicDataV1Mapper.mapRow()");

        var data = new HydroponicData();
        data.setId(rs.getLong("id"));
        data.setDeviceUuid(rs.getString("device_uuid"));
        data.setEcSolution(rs.getDouble("ec_solution"));
        data.setPhSolution(rs.getDouble("ph_solution"));
        data.setTSolution(rs.getDouble("t_solution"));
        data.setTAir(rs.getDouble("t_air"));
        data.setHumidityAir(rs.getDouble("humidity_air"));
        data.setAtmosphericPressure(rs.getDouble("atmospheric_pressure"));
        data.setCreatedTimestamp(rs.getTimestamp("created_timestamp"));

        log.info("End HydroponicDataV1Mapper.mapRow(). Taken: {} ms", System.currentTimeMillis() - start);
        return data;
    }
}

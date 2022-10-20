package ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.mapper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.entity.HydroponicEcSensorSettings;

import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
public class HydroponicEcSensorSettingsMapper implements RowMapper<HydroponicEcSensorSettings> {
    @Override
    public HydroponicEcSensorSettings mapRow(ResultSet rs, int rowNum) throws SQLException {
        long start = System.currentTimeMillis();
        log.info("[START] {} [mapRow]", this.getClass().getCanonicalName());

        var settings = HydroponicEcSensorSettings.builder()
                .id(rs.getLong("id"))
                .settingDetailId(rs.getLong("setting_detail_id"))
                .deviceUuid(rs.getString("device_uuid"))
                .time(rs.getTimestamp("time").toLocalDateTime())
                .kLowPoint(rs.getDouble("k_low_point"))
                .kHighPoint(rs.getDouble("k_high_point"))
                .rawEc(rs.getDouble("raw_ec"))
                .createdTimestamp(rs.getTimestamp("created_timestamp") != null ? rs.getTimestamp("created_timestamp").toLocalDateTime() : null)
                .updatedTimestamp(rs.getTimestamp("updated_timestamp") != null ? rs.getTimestamp("updated_timestamp").toLocalDateTime() : null)
                .build();

        log.info("[END] {} [mapRow] [TAKEN:{}ms]", this.getClass().getCanonicalName(), System.currentTimeMillis() - start);
        return settings;
    }
}

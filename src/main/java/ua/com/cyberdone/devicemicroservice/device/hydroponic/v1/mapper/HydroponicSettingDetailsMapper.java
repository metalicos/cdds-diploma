package ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.mapper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.entity.HydroponicSettingDetails;

import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
public class HydroponicSettingDetailsMapper implements RowMapper<HydroponicSettingDetails> {
    @Override
    public HydroponicSettingDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
        long start = System.currentTimeMillis();
        log.info("[START] {} [mapRow]", this.getClass().getCanonicalName());

        var settings = HydroponicSettingDetails.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .description(rs.getString("description"))
                .settingType(rs.getString("setting_type"))
                .createdTimestamp(rs.getTimestamp("created_timestamp") != null ? rs.getTimestamp("created_timestamp").toLocalDateTime() : null)
                .updatedTimestamp(rs.getTimestamp("updated_timestamp") != null ? rs.getTimestamp("updated_timestamp").toLocalDateTime() : null)
                .build();

        log.info("[END] {} [mapRow] [TAKEN:{}ms]", this.getClass().getCanonicalName(), System.currentTimeMillis() - start);
        return settings;
    }
}

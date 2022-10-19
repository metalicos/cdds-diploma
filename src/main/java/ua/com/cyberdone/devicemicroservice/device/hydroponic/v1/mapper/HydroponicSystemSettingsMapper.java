package ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.mapper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.entity.HydroponicSystemSettings;

import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
public class HydroponicSystemSettingsMapper implements RowMapper<HydroponicSystemSettings> {
    @Override
    public HydroponicSystemSettings mapRow(ResultSet rs, int rowNum) throws SQLException {
        long start = System.currentTimeMillis();
        log.info("[START] {} [mapRow]", this.getClass().getCanonicalName());

        var settings = HydroponicSystemSettings.builder()
                .id(rs.getLong("id"))
                .settingDetailId(rs.getLong("setting_detail_id"))
                .deviceUuid(rs.getString("device_uuid"))
                .time(rs.getTimestamp("time").toLocalDateTime())

                .timeZone(rs.getString("time_zone"))
                .ntpServer(rs.getString("ntp_server"))
                .wifiSSID(rs.getString("wifi_ssid"))
                .wifiPASS(rs.getString("wifi_pass"))
                .restarts(rs.getInt("restarts"))
                .growingDay(rs.getInt("growing_day"))
                .isGrowing(rs.getBoolean("is_growing"))
                .growStartDate(rs.getInt("grow_start_date"))
                .dispensersEnable(rs.getBoolean("dispensers_enable"))
                .sensorsEnable(rs.getBoolean("sensors_enable"))
                .createdTimestamp(rs.getTimestamp("created_timestamp").toLocalDateTime())
                .updatedTimestamp(rs.getTimestamp("updated_timestamp").toLocalDateTime())
                .build();


        log.info("[END] {} [mapRow] [TAKEN:{}ms]", this.getClass().getCanonicalName(), System.currentTimeMillis() - start);
        return settings;
    }
}

package ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.mapper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.entity.HydroponicSpecialSystemSettings;

import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
public class HydroponicSpecialSystemSettingsMapper implements RowMapper<HydroponicSpecialSystemSettings> {
    @Override
    public HydroponicSpecialSystemSettings mapRow(ResultSet rs, int rowNum) throws SQLException {
        long start = System.currentTimeMillis();
        log.info("[START] {} [mapRow]", this.getClass().getCanonicalName());

        var settings = HydroponicSpecialSystemSettings.builder()
                .id(rs.getLong("id"))
                .settingDetailId(rs.getLong("setting_detail_id"))
                .deviceUuid(rs.getString("device_uuid"))
                .time(rs.getTimestamp("time").toLocalDateTime())

                .softwareVersion(rs.getString("software_version"))
                .scheduleAmount(rs.getInt("schedule_amount"))
                .dispensersAmount(rs.getInt("dispensers_amount"))
                .wifiRssi(rs.getString("wifi_rssi"))
                .wifiBsid(rs.getString("wifi_bsid"))
                .localIp(rs.getString("local_ip"))
                .subnetMask(rs.getString("subnet_mask"))
                .gatewayIp(rs.getString("gateway_ip"))
                .macAddr(rs.getString("mac_addr"))
                .createdTimestamp(rs.getTimestamp("created_timestamp").toLocalDateTime())
                .updatedTimestamp(rs.getTimestamp("updated_timestamp").toLocalDateTime())
                .build();

        log.info("[END] {} [mapRow] [TAKEN:{}ms]", this.getClass().getCanonicalName(), System.currentTimeMillis() - start);
        return settings;
    }
}

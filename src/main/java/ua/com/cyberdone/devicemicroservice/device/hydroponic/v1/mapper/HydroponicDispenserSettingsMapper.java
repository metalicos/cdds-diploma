package ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.mapper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.entity.HydroponicDispenserSettings;

import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
public class HydroponicDispenserSettingsMapper implements RowMapper<HydroponicDispenserSettings> {
    @Override
    public HydroponicDispenserSettings mapRow(ResultSet rs, int rowNum) throws SQLException {
        long start = System.currentTimeMillis();
        log.info("[START] {} [mapRow]", this.getClass().getCanonicalName());

        var settings = HydroponicDispenserSettings.builder()
                .id(rs.getLong("id"))
                .settingDetailId(rs.getLong("setting_detail_id"))
                .deviceUuid(rs.getString("device_uuid"))
                .index(rs.getInt("index"))
                .time(rs.getTimestamp("time").toLocalDateTime())
                .dispenserName(rs.getString("dispenser_name"))
                .pinA(rs.getInt("pin_a"))
                .pinB(rs.getInt("pin_b"))
                .regulationDirection(rs.getBoolean("regulation_direction"))
                .enable(rs.getBoolean("enable"))
                .polarity(rs.getBoolean("polarity"))
                .smartDose(rs.getBoolean("smart_dose"))
                .totalAddedMl(rs.getDouble("total_added_ml"))
                .mlPerMs(rs.getDouble("ml_per_ms"))
                .targetValue(rs.getDouble("target_value"))
                .error(rs.getDouble("error"))
                .recheckDispenserAfterSeconds(rs.getInt("recheck_dispenser_after_seconds"))
                .lastDispenserRecheckTime(rs.getInt("last_dispenser_recheck_time"))
                .mixingVolumeMl(rs.getInt("mixing_volume_ml"))
                .createdTimestamp(rs.getTimestamp("created_timestamp").toLocalDateTime())
                .updatedTimestamp(rs.getTimestamp("updated_timestamp").toLocalDateTime())
                .build();

        log.info("[END] {} [mapRow] [TAKEN:{}ms]", this.getClass().getCanonicalName(), System.currentTimeMillis() - start);
        return settings;
    }
}

package ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.mapper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.entity.HydroponicDispenseScheduleSettings;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

@Slf4j
public class HydroponicDispenseScheduleSettingsMapper implements RowMapper<HydroponicDispenseScheduleSettings> {
    @Override
    public HydroponicDispenseScheduleSettings mapRow(ResultSet rs, int rowNum) throws SQLException {
        long start = System.currentTimeMillis();
        log.info("[START] {} [mapRow]", this.getClass().getCanonicalName());

        var settings = HydroponicDispenseScheduleSettings.builder()
                .id(rs.getLong("id"))
                .settingDetailId(rs.getLong("setting_detail_id"))
                .deviceUuid(rs.getString("device_uuid"))
                .index(rs.getInt("index"))
                .time(rs.getTimestamp("time").toLocalDateTime())
                .day(rs.getInt("day"))
                .targetEc(rs.getDouble("target_ec"))
                .targetPh(rs.getDouble("target_ph"))
                .ecError(rs.getDouble("ec_error"))
                .phError(rs.getDouble("ph_error"))
                .recheckAfterSec(rs.getInt("recheck_after_sec"))
                .isActive((ArrayList<Boolean>) Arrays.stream((Boolean[]) rs.getArray("is_active")
                        .getArray()).toList())
                .doseMl((ArrayList<Double>) Arrays.stream((Double[]) rs.getArray("dose_ml")
                        .getArray()).toList())
                .createdTimestamp(rs.getTimestamp("created_timestamp").toLocalDateTime())
                .updatedTimestamp(rs.getTimestamp("updated_timestamp").toLocalDateTime())
                .build();

        log.info("[END] {} [mapRow] [TAKEN:{}ms]", this.getClass().getCanonicalName(), System.currentTimeMillis() - start);
        return settings;
    }
}

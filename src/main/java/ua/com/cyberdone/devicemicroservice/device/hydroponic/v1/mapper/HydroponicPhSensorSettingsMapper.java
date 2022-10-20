package ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.mapper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.entity.HydroponicPhSensorSettings;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

@Slf4j
public class HydroponicPhSensorSettingsMapper implements RowMapper<HydroponicPhSensorSettings> {
    @Override
    public HydroponicPhSensorSettings mapRow(ResultSet rs, int rowNum) throws SQLException {
        long start = System.currentTimeMillis();
        log.info("[START] {} [mapRow]", this.getClass().getCanonicalName());

        var settings = HydroponicPhSensorSettings.builder()
                .id(rs.getLong("id"))
                .settingDetailId(rs.getLong("setting_detail_id"))
                .deviceUuid(rs.getString("device_uuid"))
                .time(rs.getTimestamp("time").toLocalDateTime())
                .point(rs.getString("point"))
                .value((ArrayList<Double>) Arrays.stream((Double[]) rs.getArray("value")
                        .getArray()).toList())
                .adc((ArrayList<Integer>) Arrays.stream((Integer[]) rs.getArray("adc")
                        .getArray()).toList())
                .slope(rs.getDouble("slope"))
                .adcOffset(rs.getInt("adc_offset"))
                .oversampling(rs.getInt("oversampling"))
                .createdTimestamp(rs.getTimestamp("created_timestamp") != null ? rs.getTimestamp("created_timestamp").toLocalDateTime() : null)
                .updatedTimestamp(rs.getTimestamp("updated_timestamp") != null ? rs.getTimestamp("updated_timestamp").toLocalDateTime() : null)
                .build();

        log.info("[END] {} [mapRow] [TAKEN:{}ms]", this.getClass().getCanonicalName(), System.currentTimeMillis() - start);
        return settings;
    }
}

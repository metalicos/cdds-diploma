package ua.com.cyberdone.devicemicroservice.data.mapper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;
import ua.com.cyberdone.devicemicroservice.data.entity.DeviceType;

import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
public class DeviceTypeMapper implements RowMapper<DeviceType> {
    @Override
    public DeviceType mapRow(ResultSet rs, int rowNum) throws SQLException {
        long start = System.currentTimeMillis();
        log.info("Start DeviceTypeMapper.mapRow()");

        var deviceMetadata = new DeviceType();
        deviceMetadata.setId(rs.getLong("id"));
        deviceMetadata.setType(rs.getString("type"));

        log.info("End DeviceTypeMapper.mapRow(). Taken: {} ms", System.currentTimeMillis() - start);
        return deviceMetadata;
    }
}

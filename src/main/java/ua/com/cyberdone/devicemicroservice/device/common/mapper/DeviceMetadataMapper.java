package ua.com.cyberdone.devicemicroservice.device.common.mapper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;
import ua.com.cyberdone.devicemicroservice.device.common.entity.DeviceMetadata;

import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
public class DeviceMetadataMapper implements RowMapper<DeviceMetadata> {
    @Override
    public DeviceMetadata mapRow(ResultSet rs, int rowNum) throws SQLException {
        long start = System.currentTimeMillis();
        log.info("Start DeviceMetadataMapper.mapRow()");

        var deviceMetadata = new DeviceMetadata();
        deviceMetadata.setId(rs.getLong("id"));
        deviceMetadata.setUuid(rs.getString("uuid"));
        deviceMetadata.setName(rs.getString("name"));
        deviceMetadata.setDescription(rs.getString("description"));
        deviceMetadata.setTariff(rs.getString("tariff"));
        deviceMetadata.setOwnerId(rs.getLong("owner_id"));
        deviceMetadata.setDelegationKey(rs.getString("delegation_key"));
        deviceMetadata.setLogo(rs.getBlob("logo"));
        deviceMetadata.setDeviceTypeId(rs.getLong("device_type_id"));
        deviceMetadata.setCreatedTimestamp(rs.getTimestamp("created_timestamp") != null ? rs.getTimestamp("created_timestamp").toLocalDateTime() : null);
        deviceMetadata.setUpdatedTimestamp(rs.getTimestamp("updated_timestamp") != null ? rs.getTimestamp("updated_timestamp").toLocalDateTime() : null);

        log.info("End DeviceMetadataMapper.mapRow(). Taken: {} ms", System.currentTimeMillis() - start);
        return deviceMetadata;
    }
}

package ua.com.cyberdone.devicemicroservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.QueryMappingConfiguration;
import org.springframework.data.jdbc.repository.config.DefaultQueryMappingConfiguration;
import ua.com.cyberdone.devicemicroservice.data.entity.DeviceMetadata;
import ua.com.cyberdone.devicemicroservice.data.entity.DeviceType;
import ua.com.cyberdone.devicemicroservice.data.entity.hydroponic.v1.HydroponicData;
import ua.com.cyberdone.devicemicroservice.data.entity.hydroponic.v1.HydroponicSettings;
import ua.com.cyberdone.devicemicroservice.data.mapper.DeviceMetadataMapper;
import ua.com.cyberdone.devicemicroservice.data.mapper.DeviceTypeMapper;
import ua.com.cyberdone.devicemicroservice.data.mapper.hydroponic.v1.HydroponicDataMapper;
import ua.com.cyberdone.devicemicroservice.data.mapper.hydroponic.v1.HydroponicSettingsMapper;


@Configuration
public class JdbcConfiguration {

    @Bean
    QueryMappingConfiguration rowMappers() {
        return new DefaultQueryMappingConfiguration()
                .registerRowMapper(DeviceType.class, new DeviceTypeMapper())
                .registerRowMapper(DeviceMetadata.class, new DeviceMetadataMapper())
                .registerRowMapper(HydroponicData.class, new HydroponicDataMapper())
                .registerRowMapper(HydroponicSettings.class, new HydroponicSettingsMapper());
    }
}

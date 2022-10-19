package ua.com.cyberdone.devicemicroservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.QueryMappingConfiguration;
import org.springframework.data.jdbc.repository.config.DefaultQueryMappingConfiguration;
import ua.com.cyberdone.devicemicroservice.device.common.entity.DeviceMetadata;
import ua.com.cyberdone.devicemicroservice.device.common.entity.DeviceType;
import ua.com.cyberdone.devicemicroservice.device.common.mapper.DeviceMetadataMapper;
import ua.com.cyberdone.devicemicroservice.device.common.mapper.DeviceTypeMapper;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.entity.*;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.mapper.*;


@Configuration
public class JdbcConfiguration {

    @Bean
    QueryMappingConfiguration rowMappers() {
        return new DefaultQueryMappingConfiguration()
                .registerRowMapper(DeviceType.class, new DeviceTypeMapper())
                .registerRowMapper(DeviceMetadata.class, new DeviceMetadataMapper())
                .registerRowMapper(HydroponicData.class, new HydroponicDataMapper())
                .registerRowMapper(HydroponicDispenserSettings.class, new HydroponicDispenserSettingsMapper())
                .registerRowMapper(HydroponicDispenseScheduleSettings.class, new HydroponicDispenseScheduleSettingsMapper())
                .registerRowMapper(HydroponicEcSensorSettings.class, new HydroponicEcSensorSettingsMapper())
                .registerRowMapper(HydroponicPhSensorSettings.class, new HydroponicPhSensorSettingsMapper())
                .registerRowMapper(HydroponicSettingDetails.class, new HydroponicSettingDetailsMapper())
                .registerRowMapper(HydroponicSpecialSystemSettings.class, new HydroponicSpecialSystemSettingsMapper())
                .registerRowMapper(HydroponicSystemSettings.class, new HydroponicSystemSettingsMapper());
    }
}

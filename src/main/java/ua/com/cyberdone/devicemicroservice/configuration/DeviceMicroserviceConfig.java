package ua.com.cyberdone.devicemicroservice.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeviceMicroserviceConfig {

    @Bean
    public ObjectMapper getObjectMapper() {
        return JsonMapper.builder()
                .findAndAddModules()
                .build();
    }

    @Bean
    public ModelMapper modelMapper() {
        var modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setImplicitMappingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PACKAGE_PRIVATE);
        return modelMapper;
    }
}

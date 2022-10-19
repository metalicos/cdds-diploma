package ua.com.cyberdone.devicemicroservice.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties("device.control.send-command")
public class SendOperationConfiguration {
    private Integer messageQualityOfService;
    private Boolean messageRetained;
    private Integer retryAmount;
    private Integer startDelayMs;
    private Integer periodMs;
}

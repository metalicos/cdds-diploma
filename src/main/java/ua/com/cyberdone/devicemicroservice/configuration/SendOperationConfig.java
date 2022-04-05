package ua.com.cyberdone.devicemicroservice.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties("device.control.send-command")
public class SendOperationConfig {
    private Integer messageQualityOfService;
    private Boolean messageRetained;
    private Integer retryAmount;
    private Integer startDelayMs;
    private Integer periodMs;
}

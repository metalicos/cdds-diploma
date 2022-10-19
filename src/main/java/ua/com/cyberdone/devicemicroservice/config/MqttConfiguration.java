package ua.com.cyberdone.devicemicroservice.config;

import lombok.Getter;
import lombok.Setter;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties("mqtt")
public class MqttConfiguration {
    private String username;
    private char[] password;
    private Boolean cleanSession;
    private Integer connectionTimeout;
    private Boolean autoReconnect;
    private String clientName;
    private String clientUrl;
    private Integer maxInFlightMessages;
    private Integer maxReconnectDelayMs;
    private Integer qos;

    @Bean
    public MqttClient createMqttClient() throws MqttException {
        return new MqttClient(clientUrl, clientName, new MemoryPersistence());
    }

    @Bean
    public MqttConnectOptions createConnectionOptions() {
        var options = new MqttConnectOptions();
        options.setCleanSession(cleanSession);
        options.setConnectionTimeout(connectionTimeout);
        options.setUserName(username);
        options.setPassword(password);
        options.setAutomaticReconnect(autoReconnect);
        options.setMaxInflight(maxInFlightMessages);
        options.setMaxReconnectDelay(maxReconnectDelayMs);
        return options;
    }
}

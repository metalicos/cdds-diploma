package ua.com.cyberdone.devicemicroservice.service;

import lombok.RequiredArgsConstructor;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MqttSetupService {

    @Value("${mqtt.username}")
    private String mqttUsername;

    @Value("${mqtt.password}")
    private char[] mqttPassword;

    @Value("${mqtt.clean-session}")
    private Boolean mqttCleanSession;

    @Value("${mqtt.connection-timeout}")
    private Integer mqttConnectionTimeout;

    @Value("${mqtt.auto-reconnect}")
    private Boolean mqttAutoReconnect;

    @Value("${mqtt.client.name}")
    private String mqttClientName;

    @Value("${mqtt.client.url}")
    private String mqttClientUrl;

    @Value("${mqtt.max-in-flight-messages}")
    private Integer mqttMaxInFlightMessages;

    public MqttClient createMqttClient() throws MqttException {
        return new MqttClient(mqttClientUrl, mqttClientName + UUID.randomUUID(), new MemoryPersistence());
    }

    public MqttConnectOptions createConnectionOptions() {
        var options = new MqttConnectOptions();
        options.setCleanSession(mqttCleanSession);
        options.setConnectionTimeout(mqttConnectionTimeout);
        options.setUserName(mqttUsername);
        options.setPassword(mqttPassword);
        options.setAutomaticReconnect(mqttAutoReconnect);
        options.setMaxInflight(mqttMaxInFlightMessages);
        return options;
    }
}

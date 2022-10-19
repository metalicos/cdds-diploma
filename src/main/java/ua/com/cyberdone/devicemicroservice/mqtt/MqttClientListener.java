package ua.com.cyberdone.devicemicroservice.mqtt;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import ua.com.cyberdone.devicemicroservice.config.MqttConfiguration;
import ua.com.cyberdone.devicemicroservice.mqtt.callback.Callback;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MqttClientListener implements CommandLineRunner, MqttCallback {
    private final Map<String, Callback> callbacks;
    private final MqttConfiguration mqttConfig;
    private final MqttConnectOptions connectOptions;
    private final MqttClient client;

    @Override
    public void run(String... args) throws Exception {
        try {
            log.info("Starting mqtt ...");
            client.setCallback(this);
            client.connect(connectOptions);
            for (String topic : callbacks.keySet()) {
                client.subscribe(topic, mqttConfig.getQos());
            }
            log.info("""
                                        
                                        CONNECTED TO MQTT
                            -------------------------------------
                            Time:               {}
                            Mqtt-client-name:   {}
                            Mqtt-client-url:    {}
                            Auto-reconnect:     {}
                            Reconnect-delay-ms: {}
                            """, LocalDateTime.now(),
                    mqttConfig.getClientName(),
                    mqttConfig.getClientUrl(),
                    mqttConfig.getAutoReconnect(),
                    mqttConfig.getMaxReconnectDelayMs());
        } catch (MqttException e) {
            try {
                client.disconnectForcibly();
            } catch (MqttException ex) {
                log.error("[{}] Mqtt disconnect failed {}", ex.getMessage(), LocalDateTime.now());
            }
        }
    }

    @Override
    public void connectionLost(Throwable cause) {
        // nothing
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        log.info("Central interceptor msg: {}", message);
        callbacks.get(topic).execute(client, Optional.ofNullable(message)
                .orElseThrow(() -> new IllegalStateException("Argument: MqttMessage is not valid")));
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        // nothing
    }
}

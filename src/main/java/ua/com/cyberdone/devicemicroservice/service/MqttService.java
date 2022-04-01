package ua.com.cyberdone.devicemicroservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.stereotype.Service;
import ua.com.cyberdone.devicemicroservice.callback.Callback;
import ua.com.cyberdone.devicemicroservice.configuration.MqttConfig;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

import static java.util.Objects.nonNull;

@Slf4j
@Service
@RequiredArgsConstructor
public class MqttService implements MqttCallback {
    private final Map<String, Callback> callbacks;
    private final MqttConfig mqttConfig;
    private final MqttConnectOptions connectOptions;
    private MqttClient client = null;

    @Override
    public void connectionLost(Throwable cause) {
        // not needed for implementation
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) {
        callbacks.get(topic).execute(client, Optional.ofNullable(message)
                .orElseThrow(() -> new IllegalStateException("Argument: MqttMessage is not valid")));
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
    }

    public void start() {
        try {
            client = mqttConfig.createMqttClient();
            client.setCallback(this);
            client.connect(connectOptions);
            for (String topic : callbacks.keySet()) {
                client.subscribe(topic, 2);
            }
            log.info("Connection started... Time:{}", LocalDateTime.now());
        } catch (MqttException e) {
            log.error("Mqtt connect failed {}", e.getMessage());
            stop();
        }
    }

    public void stop() {
        try {
            if (nonNull(client)) {
                client.disconnectForcibly();
                log.info("Connection stopped... Time:{}", LocalDateTime.now());
            }
        } catch (MqttException e) {
            log.error("Mqtt disconnect failed {}", e.getMessage());
        }
    }

    public void sendData(String topic, byte[] data, int qos, boolean retained) throws MqttException {
        client.publish(topic, data, qos, retained);
    }
}

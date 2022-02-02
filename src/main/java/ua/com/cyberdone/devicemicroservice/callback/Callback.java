package ua.com.cyberdone.devicemicroservice.callback;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public interface Callback {
    void execute(MqttClient client, MqttMessage message);
}

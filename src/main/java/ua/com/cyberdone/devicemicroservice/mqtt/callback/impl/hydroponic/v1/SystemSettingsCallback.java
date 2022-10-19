package ua.com.cyberdone.devicemicroservice.mqtt.callback.impl.hydroponic.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.stereotype.Service;
import ua.com.cyberdone.devicemicroservice.mqtt.callback.Callback;

@Slf4j
@RequiredArgsConstructor
@Service("hydroponic/v1/system-settings")
public class SystemSettingsCallback implements Callback {
    private final ObjectMapper objectMapper;

    @Override
    public void execute(MqttClient client, MqttMessage message) {

    }
}
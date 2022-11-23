package ua.com.cyberdone.devicemicroservice.mqtt.callback.impl.hydroponic.v1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.model.mqtt.DispenserSettings;
import ua.com.cyberdone.devicemicroservice.mqtt.callback.Callback;

import java.nio.charset.StandardCharsets;

@Slf4j
@RequiredArgsConstructor
@Service("hydroponic/v1/dispenser-settings")
public class DispenserSettingsCallback implements Callback {
    private final ObjectMapper objectMapper;

    @Async
    @Override
    @Transactional
    public void execute(MqttClient client, MqttMessage message) {
        long start = System.currentTimeMillis();
        log.info("[START] {} [execute]", this.getClass().getCanonicalName());
        var jsonMsg = new String(message.getPayload(), StandardCharsets.UTF_8);
        try {
            var dispenserSettings = objectMapper.readValue(jsonMsg, DispenserSettings.class);
            log.info("[Dispenser settings]:{}", dispenserSettings);
        } catch (JsonProcessingException ex) {
            log.error("Error to precess DispenserSettings.", ex);
        }
        log.info("[END] [TAKEN:{}ms] {} [execute] ", System.currentTimeMillis() - start, this.getClass().getCanonicalName());
    }
}

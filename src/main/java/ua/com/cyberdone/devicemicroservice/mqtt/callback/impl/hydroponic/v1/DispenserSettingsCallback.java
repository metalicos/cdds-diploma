package ua.com.cyberdone.devicemicroservice.mqtt.callback.impl.hydroponic.v1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.stereotype.Service;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.entity.HydroponicDispenserSettings;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.model.DispenserSettings;
import ua.com.cyberdone.devicemicroservice.mqtt.callback.Callback;

import java.nio.charset.StandardCharsets;

@Slf4j
@RequiredArgsConstructor
@Service("hydroponic/v1/dispenser-settings")
public class DispenserSettingsCallback implements Callback {
    private final ObjectMapper objectMapper;
    private final MqttClient client;


    @Override
    public void execute(MqttClient client, MqttMessage message) {
        var jsonMsg = new String(message.getPayload(), StandardCharsets.UTF_8);
        try {
            var dispenserSettings = objectMapper.readValue(jsonMsg, DispenserSettings.class);
            log.info("Dispenser settings: {}", dispenserSettings);
//            hydroponicDispenserSettingsService.findActive(dispenserSettings.getUuid())
//                    .ifPresentOrElse(oldSettings -> updateSettingsInDb(oldSettings, dispenserSettings),
//                            () -> saveSettingsInDb(dispenserSettings));
        } catch (JsonProcessingException ex) {
            log.error("Error to precess");
        }
    }

    private void saveSettingsInDb(DispenserSettings dispenserSettings) {
        if (dispenserSettings == null) {
            log.error("Dispenser settings are null");
            return;
        }
        long start = System.currentTimeMillis();
        log.info("[START] {} [mapRow]", this.getClass().getCanonicalName());

        //save

        log.info("[END] {} [mapRow] [TAKEN:{}ms]", this.getClass().getCanonicalName(), System.currentTimeMillis() - start);
    }

    private void updateSettingsInDb(HydroponicDispenserSettings hydroponicDispenserSettings, DispenserSettings newSettings) {
        if (newSettings == null) {
            log.error("Dispenser settings are null");
            return;
        }
        long start = System.currentTimeMillis();
        log.info("[START] {} [mapRow]", this.getClass().getCanonicalName());

        //update

        log.info("[END] {} [mapRow] [TAKEN:{}ms]", this.getClass().getCanonicalName(), System.currentTimeMillis() - start);
    }
}

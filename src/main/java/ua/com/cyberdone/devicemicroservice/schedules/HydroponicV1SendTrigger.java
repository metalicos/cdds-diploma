package ua.com.cyberdone.devicemicroservice.schedules;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.stereotype.Service;
import ua.com.cyberdone.devicemicroservice.config.MqttConfiguration;
import ua.com.cyberdone.devicemicroservice.device.model.DeviceDTO;

import java.nio.charset.StandardCharsets;

@Slf4j
@RequiredArgsConstructor
@Service("HYDROPONIC_V1SCH12D6")
public class HydroponicV1SendTrigger implements DeviceSendTrigger {

    private final MqttClient mqttClient;
    private final MqttConfiguration mqttConfiguration;

    @Override
    public void trigger(DeviceDTO deviceDTO) {
        var topic = "cyberdone/" + deviceDTO.getUuid() + "/action/send-data";

        try {
            mqttClient.publish(topic, """
                    {"operation":"send-dispenser-settings"}
                    """.getBytes(StandardCharsets.UTF_8), mqttConfiguration.getQos(), false);
            mqttClient.publish(topic, """
                    {"operation":"send-dispense-schedule-settings"}
                    """.getBytes(StandardCharsets.UTF_8), mqttConfiguration.getQos(), false);
            mqttClient.publish(topic, """
                    {"operation":"send-system-settings"}
                    """.getBytes(StandardCharsets.UTF_8), mqttConfiguration.getQos(), false);
            mqttClient.publish(topic, """
                    {"operation":"send-special-system-settings"}
                    """.getBytes(StandardCharsets.UTF_8), mqttConfiguration.getQos(), false);
            mqttClient.publish(topic, """
                    {"operation":"send-ph-settings"}
                    """.getBytes(StandardCharsets.UTF_8), mqttConfiguration.getQos(), false);
            mqttClient.publish(topic, """
                    {"operation":"send-ec-settings"}
                    """.getBytes(StandardCharsets.UTF_8), mqttConfiguration.getQos(), false);

        } catch (MqttException ex) {
            log.error("HydroponicV1SendTrigger failure.", ex);
            throw new RuntimeException(ex);
        }
    }
}

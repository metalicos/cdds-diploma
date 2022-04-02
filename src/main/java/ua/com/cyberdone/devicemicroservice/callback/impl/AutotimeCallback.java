package ua.com.cyberdone.devicemicroservice.callback.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ua.com.cyberdone.devicemicroservice.callback.Callback;
import ua.com.cyberdone.devicemicroservice.model.microcontrollers.hydroponic.HydroponicTimeDto;
import ua.com.cyberdone.devicemicroservice.service.EncDecService;

import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static ua.com.cyberdone.devicemicroservice.util.MqttVariableEncoderDecoderUtils.encode;


@Slf4j
@Service("device-microservice/autotime")
@RequiredArgsConstructor
public class AutotimeCallback implements Callback {
    public static final int DATE_MISMATCH_ERROR_SECONDS = 3;
    private final ObjectMapper objectMapper;
    private final EncDecService encDecService;
    @Value("${security.callback.show-decrypted-message.autotime}")
    private boolean showDecryptedMessage;

    @Override
    @SneakyThrows
    public void execute(MqttClient client, MqttMessage message) {
        var decryptedData = encDecService.decrypt(new String(message.getPayload()));
        if (showDecryptedMessage) {
            log.info("{}", decryptedData);
        }
        try {
            keepInTime(client, objectMapper.readValue(decryptedData, HydroponicTimeDto.class));
        } catch (JsonProcessingException | ParseException ex) {
            log.error("Error read Microcontroller's Date. {}", ex.getMessage());
        }
    }

    private void keepInTime(MqttClient client, HydroponicTimeDto hydroponicTimeDto) throws ParseException {
        var hydroponicTime = hydroponicTimeDto.getMicrocontrollerTime();
        var timeZone = hydroponicTimeDto.getMicrocontrollerTimeZone();
        var currentTimeForItsTimezone = LocalDateTime.now(ZoneId.of(timeZone));
        var lowRange = currentTimeForItsTimezone.minusSeconds(DATE_MISMATCH_ERROR_SECONDS);
        var highRange = currentTimeForItsTimezone.plusSeconds(DATE_MISMATCH_ERROR_SECONDS);

        if (hydroponicTime.isBefore(lowRange) || hydroponicTime.isAfter(highRange)) {
            try {
                client.publish("cyberdone/" + hydroponicTimeDto.getUuid() + "/updateTime",
                        new MqttMessage(encode(currentTimeForItsTimezone).getBytes(StandardCharsets.UTF_8)));
                log.info("Time updated. Device UUID={}", hydroponicTimeDto.getUuid());
            } catch (MqttException e) {
                log.error("Can not update time in microcontroller. Device UUID={} Message={}",
                        hydroponicTimeDto.getUuid(), e.getMessage());
            }
        }
    }
}

package ua.com.cyberdone.devicemicroservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.com.cyberdone.devicemicroservice.persistence.entity.ValueType;


@Slf4j
@Service
public class WebRelayOperationService extends AbstractOperationService {

    public WebRelayOperationService(MqttService mqttService) {
        super(mqttService);
    }

    public void changeRelayState(String uuid, int relayNumber, String data, ValueType type) {
        sendEncodedData(uuid, "relay/" + relayNumber, data, type);
    }
}

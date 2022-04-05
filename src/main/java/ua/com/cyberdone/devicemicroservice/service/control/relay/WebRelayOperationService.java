package ua.com.cyberdone.devicemicroservice.service.control.relay;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.com.cyberdone.devicemicroservice.configuration.SendOperationConfig;
import ua.com.cyberdone.devicemicroservice.persistence.entity.ValueType;
import ua.com.cyberdone.devicemicroservice.service.MqttService;
import ua.com.cyberdone.devicemicroservice.service.control.BaseOperationService;

import static ua.com.cyberdone.devicemicroservice.topic.relay.WebRelayTopicEnum.RELAY;


@Slf4j
@Service
public class WebRelayOperationService extends BaseOperationService {

    public WebRelayOperationService(MqttService mqttService, SendOperationConfig sendOperationConfig) {
        super(mqttService, sendOperationConfig);
    }

    public void changeRelayState(String uuid, int relayNumber, String data, ValueType type) {
        sendEncodedData(uuid, RELAY.getVal() + SLASH + relayNumber, data, type);
    }
}

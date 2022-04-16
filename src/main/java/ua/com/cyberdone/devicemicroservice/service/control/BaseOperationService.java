package ua.com.cyberdone.devicemicroservice.service.control;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttException;
import ua.com.cyberdone.devicemicroservice.configuration.SendOperationConfig;
import ua.com.cyberdone.devicemicroservice.persistence.entity.ValueType;
import ua.com.cyberdone.devicemicroservice.service.MqttService;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

import static ua.com.cyberdone.devicemicroservice.persistence.entity.ValueType.NONE;
import static ua.com.cyberdone.devicemicroservice.persistence.entity.ValueType.TIME;
import static ua.com.cyberdone.devicemicroservice.topic.CommonTopicEnum.AUTOTIME;
import static ua.com.cyberdone.devicemicroservice.topic.CommonTopicEnum.READ_ALL;
import static ua.com.cyberdone.devicemicroservice.topic.CommonTopicEnum.RESTART;
import static ua.com.cyberdone.devicemicroservice.topic.CommonTopicEnum.RESTART_COUNTER;
import static ua.com.cyberdone.devicemicroservice.topic.CommonTopicEnum.SAVE_ALL;
import static ua.com.cyberdone.devicemicroservice.topic.CommonTopicEnum.TIMEZONE;
import static ua.com.cyberdone.devicemicroservice.topic.CommonTopicEnum.UPDATE_TIME;
import static ua.com.cyberdone.devicemicroservice.topic.CommonTopicEnum.WIFI_PASS;
import static ua.com.cyberdone.devicemicroservice.topic.CommonTopicEnum.WIFI_SSID;
import static ua.com.cyberdone.devicemicroservice.util.MqttVariableEncoderDecoderUtils.encode;
import static ua.com.cyberdone.devicemicroservice.util.MqttVariableEncoderDecoderUtils.encodeConsideringToValueType;

@Slf4j
@RequiredArgsConstructor
public abstract class BaseOperationService {
    protected static final String SLASH = "/";
    private static final String CYBERDONE_START_PATH = "cyberdone";
    private final MqttService mqttService;
    private final SendOperationConfig sendOperationConfig;


    public void sendEncodedData(String uuid, String topic, String data, ValueType valueType) {
        sendData(uuid, topic, encodeConsideringToValueType(data, valueType));
    }

    public void sendData(String uuid, String topic, String data) {
        var timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            int retryCounter = sendOperationConfig.getRetryAmount();

            @Override
            public void run() {
                try {
                    mqttService.sendData(
                            CYBERDONE_START_PATH + SLASH + uuid + SLASH + topic,
                            data.getBytes(StandardCharsets.UTF_8),
                            sendOperationConfig.getMessageQualityOfService(),
                            sendOperationConfig.getMessageRetained()
                    );
                    log.info("Sent data='{}' to topic='{}'", data, CYBERDONE_START_PATH + SLASH + uuid + SLASH + topic);
                    timer.cancel();
                } catch (MqttException ex) {
                    log.error("Fail to send data to device uuid={} topic={} data={}. ", uuid, topic, data, ex);
                    if (retryCounter <= 0) {
                        timer.cancel();
                    }
                    retryCounter -= 1;
                }
            }
        }, sendOperationConfig.getStartDelayMs(), sendOperationConfig.getPeriodMs());
    }

    public void updateTime(String uuid, LocalDateTime time) {
        log.info("Updating time to: {} to UUID: {}", time, uuid);
        sendData(uuid, UPDATE_TIME.getVal(), encode(time));
    }

    public void updateTime(String uuid, String time) {
        log.info("Updating time to: {} to UUID: {}", time, uuid);
        sendEncodedData(uuid, UPDATE_TIME.getVal(), time, TIME);
    }

    public void changeAutoTimeSetting(String uuid, String value, ValueType type) {
        log.info("Updating auto time flag to: {} to UUID: {}", value, uuid);
        sendEncodedData(uuid, AUTOTIME.getVal(), value, type);
    }

    public void changeTimeZoneSetting(String uuid, String value, ValueType type) {
        log.info("Updating timezone to: {} to UUID: {}", value, uuid);
        sendEncodedData(uuid, TIMEZONE.getVal(), value, type);
    }

    public void restart(String uuid) {
        log.info("Restarting UUID: {}", uuid);
        sendEncodedData(uuid, RESTART.getVal(), null, NONE);
    }

    public void restartCounter(String uuid, String value, ValueType type) {
        log.info("Updating restart counter to: {} to UUID: {}", value, uuid);
        sendEncodedData(uuid, RESTART_COUNTER.getVal(), value, type);
    }

    public void saveAllSettings(String uuid) {
        log.info("Saving all settings to memory in UUID: {}", uuid);
        sendEncodedData(uuid, SAVE_ALL.getVal(), null, NONE);
    }

    public void readAllSettings(String uuid) {
        log.info("Applying settings from memory in UUID: {}", uuid);
        sendEncodedData(uuid, READ_ALL.getVal(), null, NONE);
    }

    public void updateWifiPassword(String uuid, String password, ValueType type) {
        log.info("Updating wifi password to {} in UUID: {}", password, uuid);
        sendEncodedData(uuid, WIFI_PASS.getVal(), password, type);
    }

    public void updateWifiSsid(String uuid, String ssid, ValueType type) {
        log.info("Updating wifi SSID to {} in UUID: {}", ssid, uuid);
        sendEncodedData(uuid, WIFI_SSID.getVal(), ssid, type);
    }
}

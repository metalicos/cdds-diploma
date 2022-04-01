package ua.com.cyberdone.devicemicroservice.service;

import org.slf4j.Logger;
import ua.com.cyberdone.devicemicroservice.persistence.entity.ValueType;
import ua.com.cyberdone.devicemicroservice.service.control.AbstractOperationService;
import ua.com.cyberdone.devicemicroservice.topic.CommonTopicEnum;

public interface ScheduleControllable {
    void control(ValueType type, String data, String topic, String uuid);

    default void controlCommonTopics(AbstractOperationService operationService, ValueType type,
                                     String data, String topic, String uuid, Logger log) {
        switch (CommonTopicEnum.topic(topic)) {
            case UPDATE_TIME -> operationService.updateTime(uuid, data);
            case TIMEZONE -> operationService.changeTimeZoneSetting(uuid, data, type);
            case AUTOTIME -> operationService.changeAutoTimeSetting(uuid, data, type);
            case RESTART -> operationService.restart(uuid);
            case RESTART_COUNTER -> operationService.restartCounter(uuid, data, type);
            case READ_ALL -> operationService.readAllSettings(uuid);
            case SAVE_ALL -> operationService.saveAllSettings(uuid);
            case WIFI_SSID -> operationService.updateWifiSsid(uuid, data, type);
            case WIFI_PASS -> operationService.updateWifiPassword(uuid, data, type);
            case UNKNOWN -> log.info("Topic={} is not of type HydroponicTopic", topic);
        }
    }
}

package ua.com.cyberdone.devicemicroservice.topic;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum CommonTopicEnum {
    UPDATE_TIME("updateTime"),
    TIMEZONE("timezone"),
    AUTOTIME("autotime"),
    RESTART("restart"),
    RESTART_COUNTER("restartCounter"),
    READ_ALL("readAll"),
    SAVE_ALL("saveAll"),
    WIFI_SSID("wifiSSID"),
    WIFI_PASS("wifiPASS"),
    UNKNOWN("");

    private final String val;

    public static CommonTopicEnum topic(String val) {
        return Arrays.stream(CommonTopicEnum.values()).filter(t -> t.val.equals(val)).findFirst().orElse(UNKNOWN);
    }
}

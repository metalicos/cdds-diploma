package ua.com.cyberdone.devicemicroservice.topic.relay;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum WebRelayTopicEnum {
    RELAY("relay"),
    UNKNOWN("");

    private final String val;

    public static WebRelayTopicEnum topic(String val) {
        return Arrays.stream(WebRelayTopicEnum.values()).filter(t -> t.val.equals(val)).findFirst().orElse(UNKNOWN);
    }
}

package ua.com.cyberdone.devicemicroservice.persistence.entity.hydroponic;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;


@Getter
@AllArgsConstructor
public enum DirectionEnum {
    LEFT("0"),
    RIGHT("1"),
    STOP("2");

    private String val;

    public static DirectionEnum direction(String val) {
        return Arrays.stream(DirectionEnum.values()).filter(e -> e.val.equals(val)).findFirst().orElse(STOP);
    }
}

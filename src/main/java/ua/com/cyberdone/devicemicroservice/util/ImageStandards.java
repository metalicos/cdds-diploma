package ua.com.cyberdone.devicemicroservice.util;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ImageStandards {
    PROFILE_CARD(168, 168),
    DEVICE_CARD(125, 125);

    private final int width;
    private final int height;
}

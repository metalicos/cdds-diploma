package ua.com.cyberdone.devicemicroservice.schedules;

import ua.com.cyberdone.devicemicroservice.device.common.model.UiDeviceMetadata;

public interface DeviceSendTrigger {
    void trigger(UiDeviceMetadata metadata);
}

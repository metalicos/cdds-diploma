package ua.com.cyberdone.devicemicroservice.schedules;

import ua.com.cyberdone.devicemicroservice.device.common.entity.DeviceMetadata;

public interface DeviceSendTrigger {
    void trigger(DeviceMetadata metadata);
}

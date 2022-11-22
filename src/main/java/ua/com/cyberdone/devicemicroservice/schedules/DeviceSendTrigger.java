package ua.com.cyberdone.devicemicroservice.schedules;


import ua.com.cyberdone.devicemicroservice.device.model.DeviceDTO;

public interface DeviceSendTrigger {
    void trigger(DeviceDTO deviceDTO);
}

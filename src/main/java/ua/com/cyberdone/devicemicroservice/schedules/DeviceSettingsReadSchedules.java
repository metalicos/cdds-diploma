package ua.com.cyberdone.devicemicroservice.schedules;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ua.com.cyberdone.devicemicroservice.device.model.DeviceDTO;
import ua.com.cyberdone.devicemicroservice.device.model.DeviceType;
import ua.com.cyberdone.devicemicroservice.device.service.DeviceService;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RequiredArgsConstructor
public class DeviceSettingsReadSchedules {
    private ConcurrentHashMap<DeviceType, List<DeviceDTO>> cache = new ConcurrentHashMap<>();
    private final DeviceService deviceService;
    private final Map<String, DeviceSendTrigger> triggers;

    @Scheduled(fixedRate = 3, timeUnit = TimeUnit.MINUTES)
    public void cacheRefresh() {
        cache = new ConcurrentHashMap<>();
        Arrays.asList(DeviceType.values()).forEach(deviceType ->
                cache.put(deviceType, deviceService.findAllByType(deviceType)));
        log.info("Cache Refresh");
    }

    @Scheduled(fixedRate = 1, timeUnit = TimeUnit.MINUTES)
    public void readHydroponicSettings() {
        log.info("[START] [HYDROPONIC_V1SCH12D6]");
        Optional.ofNullable(cache.get(DeviceType.HYDROPONIC_V1SCH12D6)).orElse(Collections.emptyList())
                .forEach(device -> triggers.get(device.getType()).trigger(device));
        log.info("[END] [HYDROPONIC_V1SCH12D6]");
    }
}

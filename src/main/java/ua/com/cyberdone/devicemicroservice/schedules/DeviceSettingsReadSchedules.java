package ua.com.cyberdone.devicemicroservice.schedules;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ua.com.cyberdone.devicemicroservice.device.common.entity.DeviceMetadata;
import ua.com.cyberdone.devicemicroservice.device.common.model.UiDeviceMetadata;
import ua.com.cyberdone.devicemicroservice.device.common.service.DeviceMetadataService;
import ua.com.cyberdone.devicemicroservice.device.common.service.DeviceTypeService;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RequiredArgsConstructor
public class DeviceSettingsReadSchedules {
    public static final String TARIFF_NAME_STANDARD = "STANDARD";
    public static final String TARIFF_NAME_PREMIUM = "PREMIUM";

    private ConcurrentHashMap<String, List<UiDeviceMetadata>> cache = new ConcurrentHashMap<>();

    private final DeviceMetadataService deviceMetadataService;
    private final DeviceTypeService deviceTypeService;

    private final Map<String, DeviceSendTrigger> triggers;

    @Scheduled(fixedRate = 3, timeUnit = TimeUnit.MINUTES)
    public void cacheRefresh() {
        cache = new ConcurrentHashMap<>();
        var type = deviceTypeService.find();
        type.forEach(deviceType -> cache.put(deviceType.getType(), deviceMetadataService.findAllByDeviceType(deviceType.getId())));
        log.info("Cache Refresh");
    }

    @Scheduled(fixedRate = 1, timeUnit = TimeUnit.MINUTES)
    public void readStandardTariffHydroponicSettings() {
        log.info("[START] [STANDARD-HYDROPONIC]");

        Optional.ofNullable(cache.get("HYDROPONIC_V1")).ifPresent(list -> list.stream()
                .filter(metadata -> TARIFF_NAME_STANDARD.equals(metadata.getTariff()))
                .forEach(metadata -> triggers.get("TRIGGER_HYDROPONIC_V1").trigger(metadata)));

        log.info("[END] [STANDARD-HYDROPONIC]");
    }

    @Scheduled(fixedRate = 30, timeUnit = TimeUnit.SECONDS)
    public void readPremiumTariffHydroponicSettings() {
        log.info("[START] [PREMIUM-HYDROPONIC]");

        Optional.ofNullable(cache.get("HYDROPONIC_V1")).ifPresent(list -> list.stream()
                .filter(metadata -> TARIFF_NAME_PREMIUM.equals(metadata.getTariff()))
                .forEach(metadata -> triggers.get("TRIGGER_HYDROPONIC_V1").trigger(metadata)));

        log.info("[END] [PREMIUM-HYDROPONIC]");
    }
}

package ua.com.cyberdone.devicemicroservice.schedules;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ua.com.cyberdone.devicemicroservice.model.dto.RegularScheduleDto;
import ua.com.cyberdone.devicemicroservice.persistence.entity.DeviceType;
import ua.com.cyberdone.devicemicroservice.persistence.entity.ValueType;
import ua.com.cyberdone.devicemicroservice.persistence.service.DeviceMetadataService;
import ua.com.cyberdone.devicemicroservice.persistence.service.RegularScheduleService;
import ua.com.cyberdone.devicemicroservice.service.HydroponicOneOperationService;
import ua.com.cyberdone.devicemicroservice.service.WebRelayOperationService;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.THURSDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.DayOfWeek.WEDNESDAY;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScheduledControl {
    private final WebRelayOperationService webRelayOperationService;
    private final HydroponicOneOperationService hydroponicOneOperationService;
    private final DeviceMetadataService deviceMetadataService;
    private final RegularScheduleService regularScheduleService;
    private List<RegularScheduleDto> schedules = new ArrayList<>();

    @Scheduled(fixedRate = 500)
    private void uploadLastDataToProcess() {
        var day = LocalDateTime.now().getDayOfWeek();
        schedules = regularScheduleService.getAllSchedulesWithDays(
                        MONDAY.equals(day), TUESDAY.equals(day), WEDNESDAY.equals(day),
                        THURSDAY.equals(day), FRIDAY.equals(day), SATURDAY.equals(day),
                        SUNDAY.equals(day)).stream()
                .filter(s -> s.getTime().isAfter(LocalTime.now())).collect(Collectors.toList());
    }

    @Scheduled(fixedRate = 1_000)
    public void regularlyControlDevices() {
        schedules.stream().filter(schedule -> {
                    var time = LocalTime.now();
                    var lowRange = schedule.getTime().minusSeconds(1);
                    var highRange = schedule.getTime().plusSeconds(1);
                    return time.isAfter(lowRange) && time.isBefore(highRange);
                })
                .forEach(s -> {
                    var metadata = deviceMetadataService.getMetadataByUuid(s.getUuid());
                    log.info("Sending data='{}' to Device=[name='{}',uuid='{}',type='{}'] topic={} scheduleId={}",
                            s.getValue(), metadata.getName(), s.getUuid(), metadata.getDeviceType(), s.getTopic(), s.getId());
                    sendOperation(metadata.getDeviceType(), s.getUuid(), s.getTopic(), s.getValue(), s.getValueType());
                });
    }

    private void sendOperation(DeviceType type, String uuid, String topic, String data, ValueType valueType) {
        switch (type) {
            case HYDROPONIC_V1 -> hydroponicOneOperationService.sendEncodedData(uuid, topic, data, valueType);
            case RELAY_N4 -> webRelayOperationService.sendEncodedData(uuid, topic, data, valueType);
        }
    }
}

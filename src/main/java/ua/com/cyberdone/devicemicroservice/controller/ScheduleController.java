package ua.com.cyberdone.devicemicroservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.com.cyberdone.devicemicroservice.controller.docs.DeviceSchedulingApi;
import ua.com.cyberdone.devicemicroservice.persistence.model.RegularScheduleDto;
import ua.com.cyberdone.devicemicroservice.persistence.model.RegularScheduleUpdateDto;
import ua.com.cyberdone.devicemicroservice.persistence.service.RegularScheduleService;

import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/device/regular/schedules")
public class ScheduleController implements DeviceSchedulingApi {
    private final RegularScheduleService regularScheduleService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('r_all','r_all_device_regular_schedules')")
    public ResponseEntity<List<RegularScheduleDto>> getSchedulesByKey(@RequestHeader(AUTHORIZATION) String token,
                                                                      @RequestParam String uuid,
                                                                      @RequestParam String key) {
        return ResponseEntity.ok(regularScheduleService.getScheduleByUuidAndMetadata(uuid, key));
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('w_all','w_device_regular_schedule')")
    public ResponseEntity<RegularScheduleDto> createSchedule(@RequestHeader(AUTHORIZATION) String token,
                                                             @RequestBody RegularScheduleDto schedule) {
        log.info("POST {}", schedule);
        return ResponseEntity.ok(regularScheduleService.saveSchedule(schedule));
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('u_all','u_device_regular_schedule')")
    public ResponseEntity<String> updateScheduleMetaInfo(@RequestHeader(AUTHORIZATION) String token,
                                                         @RequestBody RegularScheduleUpdateDto schedule) {
        log.info("PUT {}", schedule);
        regularScheduleService.updateSchedule(schedule);
        return ResponseEntity.ok("OK");
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('d_all','d_device_regular_schedule')")
    public ResponseEntity<String> deleteScheduleById(@RequestHeader(AUTHORIZATION) String token,
                                                     @PathVariable Long id) {
        log.info("DELETE {}", id);
        regularScheduleService.deleteSchedule(id);
        return ResponseEntity.ok("OK");
    }
}

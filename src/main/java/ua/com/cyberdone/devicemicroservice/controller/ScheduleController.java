package ua.com.cyberdone.devicemicroservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.com.cyberdone.devicemicroservice.controller.docs.DeviceSchedulingApi;
import ua.com.cyberdone.devicemicroservice.model.dto.RegularScheduleDto;
import ua.com.cyberdone.devicemicroservice.model.dto.RegularScheduleUpdateDto;
import ua.com.cyberdone.devicemicroservice.persistence.service.RegularScheduleService;

import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static ua.com.cyberdone.devicemicroservice.validation.ValidationConstants.UUID_FAILED_MSG;
import static ua.com.cyberdone.devicemicroservice.validation.ValidationConstants.UUID_PATTERN;
import static ua.com.cyberdone.devicemicroservice.validation.ValidationConstants.VALUE_IS_BLANK_MSG;
import static ua.com.cyberdone.devicemicroservice.validation.ValidationConstants.VALUE_IS_NULL_MSG;
import static ua.com.cyberdone.devicemicroservice.validation.ValidationConstants.VALUE_NOT_NUMBER_MSG;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/device/regular/schedules")
public class ScheduleController implements DeviceSchedulingApi {
    private final RegularScheduleService regularScheduleService;

    @GetMapping
    public ResponseEntity<List<RegularScheduleDto>> getSchedulesByKey(
            @RequestHeader(AUTHORIZATION) String token,
            @NotBlank(message = VALUE_IS_BLANK_MSG) @Pattern(regexp = UUID_PATTERN, message = UUID_FAILED_MSG)
            @RequestParam String uuid,
            @NotBlank(message = VALUE_IS_BLANK_MSG)
            @RequestParam String key) {
        return ResponseEntity.ok(regularScheduleService.getScheduleByUuidAndMetadata(uuid, key));
    }

    @PostMapping
    public ResponseEntity<RegularScheduleDto> createSchedule(@RequestHeader(AUTHORIZATION) String token,
                                                             @Valid @RequestBody RegularScheduleDto schedule) {
        log.info("POST {}", schedule);
        return ResponseEntity.ok(regularScheduleService.saveSchedule(schedule));
    }

    @PutMapping
    public ResponseEntity<String> updateScheduleMetaInfo(@RequestHeader(AUTHORIZATION) String token,
                                                         @Valid @RequestBody RegularScheduleUpdateDto schedule) {
        log.info("PUT {}", schedule);
        regularScheduleService.updateSchedule(schedule);
        return ResponseEntity.ok("OK");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteScheduleById(
            @RequestHeader(AUTHORIZATION) String token,
            @NotNull(message = VALUE_IS_NULL_MSG)
            @Digits(message = VALUE_NOT_NUMBER_MSG, integer = Integer.MAX_VALUE, fraction = 10)
            @PathVariable("id") Long id) {
        log.info("DELETE {}", id);
        regularScheduleService.deleteSchedule(id);
        return ResponseEntity.ok("OK");
    }
}

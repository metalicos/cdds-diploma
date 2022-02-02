package ua.com.cyberdone.devicemicroservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.com.cyberdone.devicemicroservice.model.dto.RegularScheduleDto;
import ua.com.cyberdone.devicemicroservice.model.dto.RegularScheduleUpdateDto;
import ua.com.cyberdone.devicemicroservice.persistence.service.RegularScheduleService;

import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

import static ua.com.cyberdone.devicemicroservice.validation.ValidationConstants.UUID_FAILED_MSG;
import static ua.com.cyberdone.devicemicroservice.validation.ValidationConstants.UUID_PATTERN;
import static ua.com.cyberdone.devicemicroservice.validation.ValidationConstants.VALUE_IS_BLANK_MSG;
import static ua.com.cyberdone.devicemicroservice.validation.ValidationConstants.VALUE_IS_NULL_MSG;
import static ua.com.cyberdone.devicemicroservice.validation.ValidationConstants.VALUE_NOT_NUMBER_MSG;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/schedules")
public class ScheduleController {
    private final RegularScheduleService regularScheduleService;

    @GetMapping
    public ResponseEntity<List<RegularScheduleDto>> getSchedulesByKey(
            @NotBlank(message = VALUE_IS_BLANK_MSG) @Pattern(regexp = UUID_PATTERN, message = UUID_FAILED_MSG)
            @RequestParam String uuid,
            @NotBlank(message = VALUE_IS_BLANK_MSG)
            @RequestParam String key) {
        return ResponseEntity.ok(regularScheduleService.getScheduleByUuidAndMetadata(uuid, key));
    }

    @PostMapping
    public ResponseEntity<String> createSchedule(@Valid @RequestBody RegularScheduleDto schedule) {
        log.info("POST {}", schedule);
        regularScheduleService.saveSchedule(schedule);
        return ResponseEntity.ok("OK");
    }

    @PatchMapping
    public ResponseEntity<String> updateScheduleMetaInfo(@Valid @RequestBody RegularScheduleUpdateDto schedule) {
        log.info("PATCH {}", schedule);
        regularScheduleService.updateSchedule(schedule);
        return ResponseEntity.ok("OK");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteScheduleById(
            @NotNull(message = VALUE_IS_NULL_MSG)
            @Digits(message = VALUE_NOT_NUMBER_MSG, integer = Integer.MAX_VALUE, fraction = 10)
            @RequestParam Long id) {
        log.info("DELETE {}", id);
        regularScheduleService.deleteSchedule(id);
        return ResponseEntity.ok("OK");
    }
}

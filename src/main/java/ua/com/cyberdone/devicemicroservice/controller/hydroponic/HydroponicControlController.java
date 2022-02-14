package ua.com.cyberdone.devicemicroservice.controller.hydroponic;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.com.cyberdone.devicemicroservice.controller.docs.HydroponicControlApi;
import ua.com.cyberdone.devicemicroservice.model.dto.microcontrollers.hydroponic.HydroponicTimeDto;
import ua.com.cyberdone.devicemicroservice.service.HydroponicOneOperationService;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static ua.com.cyberdone.devicemicroservice.persistence.entity.ValueType.DIRECTION;
import static ua.com.cyberdone.devicemicroservice.persistence.entity.ValueType.NUMBER;
import static ua.com.cyberdone.devicemicroservice.persistence.entity.ValueType.SWITCH;
import static ua.com.cyberdone.devicemicroservice.persistence.entity.ValueType.TEXT;
import static ua.com.cyberdone.devicemicroservice.persistence.entity.hydroponic.DirectionEnum.LEFT;
import static ua.com.cyberdone.devicemicroservice.persistence.entity.hydroponic.DirectionEnum.RIGHT;
import static ua.com.cyberdone.devicemicroservice.persistence.entity.hydroponic.DirectionEnum.STOP;
import static ua.com.cyberdone.devicemicroservice.validation.ValidationConstants.DIRECTION_FAILED_MSG;
import static ua.com.cyberdone.devicemicroservice.validation.ValidationConstants.DIRECTION_PATTERN;
import static ua.com.cyberdone.devicemicroservice.validation.ValidationConstants.NUMBER_FAILED_MSG;
import static ua.com.cyberdone.devicemicroservice.validation.ValidationConstants.NUMBER_PATTERN;
import static ua.com.cyberdone.devicemicroservice.validation.ValidationConstants.SWITCH_FAILED_MSG;
import static ua.com.cyberdone.devicemicroservice.validation.ValidationConstants.SWITCH_PATTERN;
import static ua.com.cyberdone.devicemicroservice.validation.ValidationConstants.TEXT_FAILED_MSG;
import static ua.com.cyberdone.devicemicroservice.validation.ValidationConstants.TEXT_PATTERN;
import static ua.com.cyberdone.devicemicroservice.validation.ValidationConstants.UUID_FAILED_MSG;
import static ua.com.cyberdone.devicemicroservice.validation.ValidationConstants.UUID_PATTERN;
import static ua.com.cyberdone.devicemicroservice.validation.ValidationConstants.VALUE_IS_BLANK_MSG;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/hydroponic/control")
public class HydroponicControlController implements HydroponicControlApi {
    private static final String LEFT_DIRECTION = "0";
    private static final String STOP_DIRECTION = "2";
    private static final String RIGHT_DIRECTION = "1";
    private final HydroponicOneOperationService operationService;

    @PutMapping("/update/time")
    @PreAuthorize("hasAnyAuthority('u_all','u_hydroponic_time')")
    public ResponseEntity<String> updateTime(@RequestHeader(AUTHORIZATION) String token,
                                             @Valid @RequestBody HydroponicTimeDto dto) {
        log.info("Updating time to={} in timezone={} for uuid={}", dto.getMicrocontrollerTime(),
                dto.getMicrocontrollerTimeZone(), dto.getUuid());
        operationService.updateTime(dto.getUuid(), dto.getMicrocontrollerTime());
        return ResponseEntity.ok("OK");
    }

    @PutMapping("/update/zone")
    @PreAuthorize("hasAnyAuthority('u_all','u_hydroponic_time_zone')")
    public ResponseEntity<String> updateZone(
            @RequestHeader(AUTHORIZATION) String token,
            @NotBlank(message = VALUE_IS_BLANK_MSG) @Pattern(regexp = UUID_PATTERN, message = UUID_FAILED_MSG)
            @RequestParam String uuid,
            @NotBlank(message = VALUE_IS_BLANK_MSG)
            @RequestParam String value) {
        log.info("Change timezone setting to {} for uuid={}", value, uuid);
        operationService.changeTimeZoneSetting(uuid, value, TEXT);
        return ResponseEntity.ok("OK");
    }

    @PutMapping("/update/autotime")
    @PreAuthorize("hasAnyAuthority('u_all','u_hydroponic_autotime')")
    public ResponseEntity<String> updateAutoTime(
            @RequestHeader(AUTHORIZATION) String token,
            @NotBlank(message = VALUE_IS_BLANK_MSG) @Pattern(regexp = UUID_PATTERN, message = UUID_FAILED_MSG)
            @RequestParam String uuid,
            @NotBlank(message = VALUE_IS_BLANK_MSG) @Pattern(regexp = NUMBER_PATTERN, message = NUMBER_FAILED_MSG)
            @RequestParam String value) {
        log.info("Change autotime setting to {} for uuid={}", value, uuid);
        operationService.changeAutoTimeSetting(uuid, value, NUMBER);
        return ResponseEntity.ok("OK");
    }

    @PutMapping("/update/pumps/phUp")
    @PreAuthorize("hasAnyAuthority('u_all','u_hydroponic_ph_up')")
    public ResponseEntity<String> updatePhUpPumpStatus(
            @RequestHeader(AUTHORIZATION) String token,
            @NotBlank(message = VALUE_IS_BLANK_MSG) @Pattern(regexp = UUID_PATTERN, message = UUID_FAILED_MSG)
            @RequestParam String uuid,
            @NotBlank(message = VALUE_IS_BLANK_MSG) @Pattern(regexp = DIRECTION_PATTERN, message = DIRECTION_FAILED_MSG)
            @RequestParam String value) {
        log.info("Pump Ph Up; direction={} uuid={}", value, uuid);
        switch (value) {
            case LEFT_DIRECTION -> operationService.phUpPump(uuid, LEFT, DIRECTION);
            case STOP_DIRECTION -> operationService.phUpPump(uuid, STOP, DIRECTION);
            case RIGHT_DIRECTION -> operationService.phUpPump(uuid, RIGHT, DIRECTION);
        }
        return ResponseEntity.ok("OK");
    }

    @PutMapping("/update/pumps/phDown")
    @PreAuthorize("hasAnyAuthority('u_all','u_hydroponic_ph_down')")
    public ResponseEntity<String> updatePhDownPumpStatus(
            @RequestHeader(AUTHORIZATION) String token,
            @NotBlank(message = VALUE_IS_BLANK_MSG) @Pattern(regexp = UUID_PATTERN, message = UUID_FAILED_MSG)
            @RequestParam String uuid,
            @NotBlank(message = VALUE_IS_BLANK_MSG) @Pattern(regexp = DIRECTION_PATTERN, message = DIRECTION_FAILED_MSG)
            @RequestParam String value) {
        log.info("Pump Ph Down; direction={} uuid={}", value, uuid);
        switch (value) {
            case LEFT_DIRECTION -> operationService.phDownPump(uuid, LEFT, DIRECTION);
            case STOP_DIRECTION -> operationService.phDownPump(uuid, STOP, DIRECTION);
            case RIGHT_DIRECTION -> operationService.phDownPump(uuid, RIGHT, DIRECTION);
        }
        return ResponseEntity.ok("OK");
    }

    @PutMapping("/update/pumps/tds")
    @PreAuthorize("hasAnyAuthority('u_all','u_hydroponic_tds')")
    public ResponseEntity<String> updateTdsPumpStatus(
            @RequestHeader(AUTHORIZATION) String token,
            @NotBlank(message = VALUE_IS_BLANK_MSG) @Pattern(regexp = UUID_PATTERN, message = UUID_FAILED_MSG)
            @RequestParam String uuid,
            @NotBlank(message = VALUE_IS_BLANK_MSG) @Pattern(regexp = DIRECTION_PATTERN, message = DIRECTION_FAILED_MSG)
            @RequestParam String value) {
        log.info("Pump Tds; direction={} uuid={}", value, uuid);
        switch (value) {
            case LEFT_DIRECTION -> operationService.tdsPump(uuid, LEFT, DIRECTION);
            case STOP_DIRECTION -> operationService.tdsPump(uuid, STOP, DIRECTION);
            case RIGHT_DIRECTION -> operationService.tdsPump(uuid, RIGHT, DIRECTION);
        }
        return ResponseEntity.ok("OK");
    }

    @PutMapping("/update/restart")
    @PreAuthorize("hasAnyAuthority('u_all','u_hydroponic_restart')")
    public ResponseEntity<String> restart(
            @RequestHeader(AUTHORIZATION) String token,
            @NotBlank(message = VALUE_IS_BLANK_MSG) @Pattern(regexp = UUID_PATTERN, message = UUID_FAILED_MSG)
            @RequestParam String uuid) {
        log.info("Restart uuid={}", uuid);
        operationService.restart(uuid);
        return ResponseEntity.ok("OK");
    }

    @PutMapping("/save/settings")
    @PreAuthorize("hasAnyAuthority('u_all','u_hydroponic_save_settings')")
    public ResponseEntity<String> saveAllSettings(
            @RequestHeader(AUTHORIZATION) String token,
            @NotBlank(message = VALUE_IS_BLANK_MSG) @Pattern(regexp = UUID_PATTERN, message = UUID_FAILED_MSG)
            @RequestParam String uuid) {
        log.info("Save Settings uuid={}", uuid);
        operationService.saveAllSettings(uuid);
        return ResponseEntity.ok("OK");
    }

    @PutMapping("/read/settings")
    @PreAuthorize("hasAnyAuthority('u_all','u_hydroponic_read_settings')")
    public ResponseEntity<String> readAllSettings(
            @RequestHeader(AUTHORIZATION) String token,
            @NotBlank(message = VALUE_IS_BLANK_MSG) @Pattern(regexp = UUID_PATTERN, message = UUID_FAILED_MSG)
            @RequestParam String uuid) {
        log.info("Read Settings uuid={}", uuid);
        operationService.readAllSettings(uuid);
        return ResponseEntity.ok("OK");
    }

    @PutMapping("/calibrate/tds")
    @PreAuthorize("hasAnyAuthority('u_all','u_hydroponic_calibrate_tds')")
    public ResponseEntity<String> calibrateTdsSensor(
            @RequestHeader(AUTHORIZATION) String token,
            @NotBlank(message = VALUE_IS_BLANK_MSG) @Pattern(regexp = UUID_PATTERN, message = UUID_FAILED_MSG)
            @RequestParam String uuid,
            @NotBlank(message = VALUE_IS_BLANK_MSG) @Pattern(regexp = NUMBER_PATTERN, message = NUMBER_FAILED_MSG)
            @RequestParam String value) {
        log.info("Calibrate TDS; val={} uuid={}", value, uuid);
        operationService.calibrateTdsSensor(uuid, value, NUMBER);
        return ResponseEntity.ok("OK");
    }

    @PutMapping("/calibrate/tds/clear")
    @PreAuthorize("hasAnyAuthority('u_all','u_hydroponic_calibrate_tds_clear')")
    public ResponseEntity<String> clrCalibrationTdsSensor(
            @RequestHeader(AUTHORIZATION) String token,
            @NotBlank(message = VALUE_IS_BLANK_MSG) @Pattern(regexp = UUID_PATTERN, message = UUID_FAILED_MSG)
            @RequestParam String uuid) {
        log.info("Calibrate TDS Clear uuid={}", uuid);
        operationService.clearCalibrationOfTdsSensor(uuid);
        return ResponseEntity.ok("OK");
    }

    @PutMapping("/calibrate/ph/low")
    @PreAuthorize("hasAnyAuthority('u_all','u_hydroponic_calibrate_ph_low')")
    public ResponseEntity<String> calibratePhLow(
            @RequestHeader(AUTHORIZATION) String token,
            @NotBlank(message = VALUE_IS_BLANK_MSG) @Pattern(regexp = UUID_PATTERN, message = UUID_FAILED_MSG)
            @RequestParam String uuid,
            @NotBlank(message = VALUE_IS_BLANK_MSG) @Pattern(regexp = NUMBER_PATTERN, message = NUMBER_FAILED_MSG)
            @RequestParam String value) {
        log.info("Calibrate Ph LOW; val={} uuid={}", value, uuid);
        operationService.calibratePhSensorLowPoint(uuid, value, NUMBER);
        return ResponseEntity.ok("OK");
    }

    @PutMapping("/calibrate/ph/high")
    @PreAuthorize("hasAnyAuthority('u_all','u_hydroponic_calibrate_ph_high')")
    public ResponseEntity<String> calibratePhHigh(
            @RequestHeader(AUTHORIZATION) String token,
            @NotBlank(message = VALUE_IS_BLANK_MSG) @Pattern(regexp = UUID_PATTERN, message = UUID_FAILED_MSG)
            @RequestParam String uuid,
            @NotBlank(message = VALUE_IS_BLANK_MSG) @Pattern(regexp = NUMBER_PATTERN, message = NUMBER_FAILED_MSG)
            @RequestParam String value) {
        log.info("Calibrate Ph HIGH; val={} uuid={}", value, uuid);
        operationService.calibratePhSensorHighPoint(uuid, value, NUMBER);
        return ResponseEntity.ok("OK");
    }

    @PutMapping("/calibrate/ph/clear")
    @PreAuthorize("hasAnyAuthority('u_all','u_hydroponic_calibrate_ph_clear')")
    public ResponseEntity<String> clrCalibrationPhSensor(
            @RequestHeader(AUTHORIZATION) String token,
            @NotBlank(message = VALUE_IS_BLANK_MSG) @Pattern(regexp = UUID_PATTERN, message = UUID_FAILED_MSG)
            @RequestParam String uuid) {
        log.info("Calibrate Ph Clear uuid={}", uuid);
        operationService.clearCalibrationOfPhSensor(uuid);
        return ResponseEntity.ok("OK");
    }

    @PutMapping("/setup/ph")
    @PreAuthorize("hasAnyAuthority('u_all','u_hydroponic_setup_ph')")
    public ResponseEntity<String> updateSetupPhValue(
            @RequestHeader(AUTHORIZATION) String token,
            @NotBlank(message = VALUE_IS_BLANK_MSG) @Pattern(regexp = UUID_PATTERN, message = UUID_FAILED_MSG)
            @RequestParam String uuid,
            @NotBlank(message = VALUE_IS_BLANK_MSG) @Pattern(regexp = NUMBER_PATTERN, message = NUMBER_FAILED_MSG)
            @RequestParam String value) {
        operationService.updateSetupPhValue(uuid, value, NUMBER);
        log.info("Setup Ph; val={} uuid={}", value, uuid);
        return ResponseEntity.ok("OK");
    }

    @PutMapping("/setup/tds")
    @PreAuthorize("hasAnyAuthority('u_all','u_hydroponic_setup_tds')")
    public ResponseEntity<String> updateSetupTdsValue(
            @RequestHeader(AUTHORIZATION) String token,
            @NotBlank(message = VALUE_IS_BLANK_MSG) @Pattern(regexp = UUID_PATTERN, message = UUID_FAILED_MSG)
            @RequestParam String uuid,
            @NotBlank(message = VALUE_IS_BLANK_MSG) @Pattern(regexp = NUMBER_PATTERN, message = NUMBER_FAILED_MSG)
            @RequestParam String value) {
        log.info("Setup TDS; val={} uuid={}", value, uuid);
        operationService.updateSetupTdsValue(uuid, value, NUMBER);
        return ResponseEntity.ok("OK");
    }

    @PutMapping("/update/dispensers/recheck-time")
    @PreAuthorize("hasAnyAuthority('u_all','u_hydroponic_dispensers_recheck_time')")
    public ResponseEntity<String> updateRecheckDispensersAfterTime(
            @RequestHeader(AUTHORIZATION) String token,
            @NotBlank(message = VALUE_IS_BLANK_MSG) @Pattern(regexp = UUID_PATTERN, message = UUID_FAILED_MSG)
            @RequestParam String uuid,
            @NotBlank(message = VALUE_IS_BLANK_MSG) @Pattern(regexp = NUMBER_PATTERN, message = NUMBER_FAILED_MSG)
            @RequestParam String value) {
        log.info("Recheck Time; val={} uuid={}", value, uuid);
        operationService.updateRecheckDispensersAfterTime(uuid, value, NUMBER);
        return ResponseEntity.ok("OK");
    }

    @PutMapping("/update/dose/ph/up")
    @PreAuthorize("hasAnyAuthority('u_all','u_hydroponic_dose_ph_up')")
    public ResponseEntity<String> updatePhUpDose(
            @RequestHeader(AUTHORIZATION) String token,
            @NotBlank(message = VALUE_IS_BLANK_MSG) @Pattern(regexp = UUID_PATTERN, message = UUID_FAILED_MSG)
            @RequestParam String uuid,
            @NotBlank(message = VALUE_IS_BLANK_MSG) @Pattern(regexp = NUMBER_PATTERN, message = NUMBER_FAILED_MSG)
            @RequestParam String value) {
        log.info("Dose Ph UP; val={} uuid={}", value, uuid);
        operationService.updatePhUpDose(uuid, value, NUMBER);
        return ResponseEntity.ok("OK");
    }

    @PutMapping("/update/dose/ph/down")
    @PreAuthorize("hasAnyAuthority('u_all','u_hydroponic_dose_ph_down')")
    public ResponseEntity<String> updatePhDownDose(
            @RequestHeader(AUTHORIZATION) String token,
            @NotBlank(message = VALUE_IS_BLANK_MSG) @Pattern(regexp = UUID_PATTERN, message = UUID_FAILED_MSG)
            @RequestParam String uuid,
            @NotBlank(message = VALUE_IS_BLANK_MSG) @Pattern(regexp = NUMBER_PATTERN, message = NUMBER_FAILED_MSG)
            @RequestParam String value) {
        log.info("Dose Ph DOWN; val={} uuid={}", value, uuid);
        operationService.updatePhDownDose(uuid, value, NUMBER);
        return ResponseEntity.ok("OK");
    }

    @PutMapping("/update/dose/tds")
    @PreAuthorize("hasAnyAuthority('u_all','u_hydroponic_dose_tds')")
    public ResponseEntity<String> updateFertilizerDose(
            @RequestHeader(AUTHORIZATION) String token,
            @NotBlank(message = VALUE_IS_BLANK_MSG) @Pattern(regexp = UUID_PATTERN, message = UUID_FAILED_MSG)
            @RequestParam String uuid,
            @NotBlank(message = VALUE_IS_BLANK_MSG) @Pattern(regexp = NUMBER_PATTERN, message = NUMBER_FAILED_MSG)
            @RequestParam String value) {
        log.info("Dose TDS; val={} uuid={}", value, uuid);
        operationService.updateTdsDose(uuid, value, NUMBER);
        return ResponseEntity.ok("OK");
    }

    @PutMapping("/update/regulator/error/ph")
    @PreAuthorize("hasAnyAuthority('u_all','u_hydroponic_regulator_error_ph')")
    public ResponseEntity<String> updateRegulatePhError(
            @RequestHeader(AUTHORIZATION) String token,
            @NotBlank(message = VALUE_IS_BLANK_MSG) @Pattern(regexp = UUID_PATTERN, message = UUID_FAILED_MSG)
            @RequestParam String uuid,
            @NotBlank(message = VALUE_IS_BLANK_MSG) @Pattern(regexp = NUMBER_PATTERN, message = NUMBER_FAILED_MSG)
            @RequestParam String value) {
        log.info("Reg Error Ph; val={} uuid={}", value, uuid);
        operationService.updateRegulatePhError(uuid, value, NUMBER);
        return ResponseEntity.ok("OK");
    }

    @PutMapping("/update/regulator/error/tds")
    @PreAuthorize("hasAnyAuthority('u_all','u_hydroponic_regulator_error_tds')")
    public ResponseEntity<String> updateRegulateTdsError(
            @RequestHeader(AUTHORIZATION) String token,
            @NotBlank(message = VALUE_IS_BLANK_MSG) @Pattern(regexp = UUID_PATTERN, message = UUID_FAILED_MSG)
            @RequestParam String uuid,
            @NotBlank(message = VALUE_IS_BLANK_MSG) @Pattern(regexp = NUMBER_PATTERN, message = NUMBER_FAILED_MSG)
            @RequestParam String value) {
        log.info("Reg Error TDS; val={} uuid={}", value, uuid);
        operationService.updateRegulateTdsError(uuid, value, NUMBER);
        return ResponseEntity.ok("OK");
    }

    @PutMapping("/update/pump/speed")
    @PreAuthorize("hasAnyAuthority('u_all','u_hydroponic_pump_speed')")
    public ResponseEntity<String> updatePumpSpeed(
            @RequestHeader(AUTHORIZATION) String token,
            @NotBlank(message = VALUE_IS_BLANK_MSG) @Pattern(regexp = UUID_PATTERN, message = UUID_FAILED_MSG)
            @RequestParam String uuid,
            @NotBlank(message = VALUE_IS_BLANK_MSG) @Pattern(regexp = NUMBER_PATTERN, message = NUMBER_FAILED_MSG)
            @RequestParam String value) {
        log.info("Pump speed ml/ms; val={} uuid={}", value, uuid);
        operationService.updatePumpSpeedMlPerMilliseconds(uuid, value, NUMBER);
        return ResponseEntity.ok("OK");
    }

    @PutMapping("/update/wifi/ssid")
    @PreAuthorize("hasAnyAuthority('u_all','u_hydroponic_wifi_ssid')")
    public ResponseEntity<String> updateWifiSsid(
            @RequestHeader(AUTHORIZATION) String token,
            @NotBlank(message = VALUE_IS_BLANK_MSG) @Pattern(regexp = UUID_PATTERN, message = UUID_FAILED_MSG)
            @RequestParam String uuid,
            @NotBlank(message = VALUE_IS_BLANK_MSG) @Pattern(regexp = TEXT_PATTERN, message = TEXT_FAILED_MSG)
            @RequestParam String value) {
        log.info("Wifi SSID; val={} uuid={}", value, uuid);
        operationService.updateWifiSsid(uuid, value, TEXT);
        return ResponseEntity.ok("OK");
    }

    @PutMapping("/update/wifi/pass")
    @PreAuthorize("hasAnyAuthority('u_all','u_hydroponic_wifi_pass')")
    public ResponseEntity<String> updateWifiPassword(
            @RequestHeader(AUTHORIZATION) String token,
            @NotBlank(message = VALUE_IS_BLANK_MSG) @Pattern(regexp = UUID_PATTERN, message = UUID_FAILED_MSG)
            @RequestParam String uuid,
            @NotBlank(message = VALUE_IS_BLANK_MSG) @Pattern(regexp = TEXT_PATTERN, message = TEXT_FAILED_MSG)
            @RequestParam String value) {
        log.info("Wifi PASS; val={} uuid={}", value, uuid);
        operationService.updateWifiPassword(uuid, value, TEXT);
        return ResponseEntity.ok("OK");
    }

    @PutMapping("/update/enable/sensors")
    @PreAuthorize("hasAnyAuthority('u_all','u_hydroponic_enable_sensors')")
    public ResponseEntity<String> updateSensorsEnable(
            @RequestHeader(AUTHORIZATION) String token,
            @NotBlank(message = VALUE_IS_BLANK_MSG) @Pattern(regexp = UUID_PATTERN, message = UUID_FAILED_MSG)
            @RequestParam String uuid,
            @NotBlank(message = VALUE_IS_BLANK_MSG) @Pattern(regexp = SWITCH_PATTERN, message = SWITCH_FAILED_MSG)
            @RequestParam String value) {
        log.info("Enable sensors; val={} uuid={}", value, uuid);
        operationService.updateSensorsEnable(uuid, value, SWITCH);
        return ResponseEntity.ok("OK");
    }

    @PutMapping("/update/enable/dispensers")
    @PreAuthorize("hasAnyAuthority('u_all','u_hydroponic_enable_dispensers')")
    public ResponseEntity<String> updateDispensersEnable(
            @RequestHeader(AUTHORIZATION) String token,
            @NotBlank(message = VALUE_IS_BLANK_MSG) @Pattern(regexp = UUID_PATTERN, message = UUID_FAILED_MSG)
            @RequestParam String uuid,
            @NotBlank(message = VALUE_IS_BLANK_MSG) @Pattern(regexp = SWITCH_PATTERN, message = SWITCH_FAILED_MSG)
            @RequestParam String value) {
        log.info("Enable Dispensers; val={} uuid={}", value, uuid);
        operationService.updateDispensersEnable(uuid, value, SWITCH);
        return ResponseEntity.ok("OK");
    }
}

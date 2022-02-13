package ua.com.cyberdone.devicemicroservice.controller.hydroponic;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> updateTime(@RequestHeader(AUTHORIZATION) String token,
                                             @Valid @RequestBody HydroponicTimeDto dto) {
        log.info("Updating time to={} in timezone={} for uuid={}", dto.getMicrocontrollerTime(),
                dto.getMicrocontrollerTimeZone(), dto.getUuid());
        operationService.updateTime(dto.getUuid(), dto.getMicrocontrollerTime());
        return ResponseEntity.ok("OK");
    }

    @PutMapping("/update/zone")
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
    public ResponseEntity<String> restart(
            @RequestHeader(AUTHORIZATION) String token,
            @NotBlank(message = VALUE_IS_BLANK_MSG) @Pattern(regexp = UUID_PATTERN, message = UUID_FAILED_MSG)
            @RequestParam String uuid) {
        log.info("Restart uuid={}", uuid);
        operationService.restart(uuid);
        return ResponseEntity.ok("OK");
    }

    @PutMapping("/save/settings")
    public ResponseEntity<String> saveAllSettings(
            @RequestHeader(AUTHORIZATION) String token,
            @NotBlank(message = VALUE_IS_BLANK_MSG) @Pattern(regexp = UUID_PATTERN, message = UUID_FAILED_MSG)
            @RequestParam String uuid) {
        log.info("Save Settings uuid={}", uuid);
        operationService.saveAllSettings(uuid);
        return ResponseEntity.ok("OK");
    }

    @PutMapping("/read/settings")
    public ResponseEntity<String> readAllSettings(
            @RequestHeader(AUTHORIZATION) String token,
            @NotBlank(message = VALUE_IS_BLANK_MSG) @Pattern(regexp = UUID_PATTERN, message = UUID_FAILED_MSG)
            @RequestParam String uuid) {
        log.info("Read Settings uuid={}", uuid);
        operationService.readAllSettings(uuid);
        return ResponseEntity.ok("OK");
    }

    @PutMapping("/calibrate/tds")
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
    public ResponseEntity<String> clrCalibrationTdsSensor(
            @RequestHeader(AUTHORIZATION) String token,
            @NotBlank(message = VALUE_IS_BLANK_MSG) @Pattern(regexp = UUID_PATTERN, message = UUID_FAILED_MSG)
            @RequestParam String uuid) {
        log.info("Calibrate TDS Clear uuid={}", uuid);
        operationService.clearCalibrationOfTdsSensor(uuid);
        return ResponseEntity.ok("OK");
    }

    @PutMapping("/calibrate/ph/low")
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
    public ResponseEntity<String> clrCalibrationPhSensor(
            @RequestHeader(AUTHORIZATION) String token,
            @NotBlank(message = VALUE_IS_BLANK_MSG) @Pattern(regexp = UUID_PATTERN, message = UUID_FAILED_MSG)
            @RequestParam String uuid) {
        log.info("Calibrate Ph Clear uuid={}", uuid);
        operationService.clearCalibrationOfPhSensor(uuid);
        return ResponseEntity.ok("OK");
    }

    @PutMapping("/setup/ph")
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

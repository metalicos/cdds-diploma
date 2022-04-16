package ua.com.cyberdone.devicemicroservice.controller.hydroponic;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.com.cyberdone.devicemicroservice.controller.BaseControlController;
import ua.com.cyberdone.devicemicroservice.controller.docs.HydroponicControlApi;
import ua.com.cyberdone.devicemicroservice.persistence.model.microcontrollers.hydroponic.DatabasePhCalibrationDto;
import ua.com.cyberdone.devicemicroservice.persistence.model.microcontrollers.hydroponic.DatabaseTdsCalibrationDto;
import ua.com.cyberdone.devicemicroservice.service.control.hydroponic.HydroponicOneOperationService;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static ua.com.cyberdone.devicemicroservice.persistence.entity.ValueType.DIRECTION;
import static ua.com.cyberdone.devicemicroservice.persistence.entity.ValueType.NUMBER;
import static ua.com.cyberdone.devicemicroservice.persistence.entity.ValueType.SWITCH;
import static ua.com.cyberdone.devicemicroservice.persistence.entity.hydroponic.DirectionEnum.LEFT;
import static ua.com.cyberdone.devicemicroservice.persistence.entity.hydroponic.DirectionEnum.RIGHT;
import static ua.com.cyberdone.devicemicroservice.persistence.entity.hydroponic.DirectionEnum.STOP;

@RestController
@RequestMapping("/hydroponic/control")
public class HydroponicControlController extends BaseControlController implements HydroponicControlApi {
    private static final String LEFT_DIRECTION = "0";
    private static final String STOP_DIRECTION = "2";
    private static final String RIGHT_DIRECTION = "1";
    private final HydroponicOneOperationService operationService;

    public HydroponicControlController(HydroponicOneOperationService operationService) {
        super(operationService);
        this.operationService = operationService;
    }

    @PutMapping("/update/pumps/phUp")
    @PreAuthorize("hasAnyAuthority('u_all','u_hydroponic_ph_up')")
    public ResponseEntity<String> updatePhUpPumpStatus(@RequestHeader(AUTHORIZATION) String token,
                                                       @RequestParam String uuid,
                                                       @RequestParam String value) {
        switch (value) {
            case LEFT_DIRECTION -> operationService.phUpPump(uuid, LEFT, DIRECTION);
            case STOP_DIRECTION -> operationService.phUpPump(uuid, STOP, DIRECTION);
            case RIGHT_DIRECTION -> operationService.phUpPump(uuid, RIGHT, DIRECTION);
        }
        return ResponseEntity.ok("OK");
    }

    @PutMapping("/update/pumps/phDown")
    @PreAuthorize("hasAnyAuthority('u_all','u_hydroponic_ph_down')")
    public ResponseEntity<String> updatePhDownPumpStatus(@RequestHeader(AUTHORIZATION) String token,
                                                         @RequestParam String uuid,
                                                         @RequestParam String value) {
        switch (value) {
            case LEFT_DIRECTION -> operationService.phDownPump(uuid, LEFT, DIRECTION);
            case STOP_DIRECTION -> operationService.phDownPump(uuid, STOP, DIRECTION);
            case RIGHT_DIRECTION -> operationService.phDownPump(uuid, RIGHT, DIRECTION);
        }
        return ResponseEntity.ok("OK");
    }

    @PutMapping("/update/pumps/tds")
    @PreAuthorize("hasAnyAuthority('u_all','u_hydroponic_tds')")
    public ResponseEntity<String> updateTdsPumpStatus(@RequestHeader(AUTHORIZATION) String token,
                                                      @RequestParam String uuid,
                                                      @RequestParam String value) {
        switch (value) {
            case LEFT_DIRECTION -> operationService.tdsPump(uuid, LEFT, DIRECTION);
            case STOP_DIRECTION -> operationService.tdsPump(uuid, STOP, DIRECTION);
            case RIGHT_DIRECTION -> operationService.tdsPump(uuid, RIGHT, DIRECTION);
        }
        return ResponseEntity.ok("OK");
    }

    @PutMapping("/calibrate/tds")
    @PreAuthorize("hasAnyAuthority('u_all','u_hydroponic_calibrate_tds')")
    public ResponseEntity<String> calibrateTdsSensor(@RequestHeader(AUTHORIZATION) String token,
                                                     @RequestParam String uuid,
                                                     @RequestParam String value) {
        operationService.calibrateTdsSensor(uuid, value, NUMBER);
        return ResponseEntity.ok("OK");
    }

    @PutMapping("/calibrate/tds/clear")
    @PreAuthorize("hasAnyAuthority('u_all','u_hydroponic_calibrate_tds_clear')")
    public ResponseEntity<String> clrCalibrationTdsSensor(@RequestHeader(AUTHORIZATION) String token,
                                                          @RequestParam String uuid) {
        operationService.clearCalibrationOfTdsSensor(uuid);
        return ResponseEntity.ok("OK");
    }

    @PutMapping("/calibrate/ph/low")
    @PreAuthorize("hasAnyAuthority('u_all','u_hydroponic_calibrate_ph_low')")
    public ResponseEntity<String> calibratePhLow(@RequestHeader(AUTHORIZATION) String token,
                                                 @RequestParam String uuid,
                                                 @RequestParam String value) {
        operationService.calibratePhSensorLowPoint(uuid, value, NUMBER);
        return ResponseEntity.ok("OK");
    }

    @PutMapping("/calibrate/ph/high")
    @PreAuthorize("hasAnyAuthority('u_all','u_hydroponic_calibrate_ph_high')")
    public ResponseEntity<String> calibratePhHigh(@RequestHeader(AUTHORIZATION) String token,
                                                  @RequestParam String uuid,
                                                  @RequestParam String value) {
        operationService.calibratePhSensorHighPoint(uuid, value, NUMBER);
        return ResponseEntity.ok("OK");
    }

    @PutMapping("/calibrate/ph/clear")
    @PreAuthorize("hasAnyAuthority('u_all','u_hydroponic_calibrate_ph_clear')")
    public ResponseEntity<String> clrCalibrationPhSensor(@RequestHeader(AUTHORIZATION) String token,
                                                         @RequestParam String uuid) {
        operationService.clearCalibrationOfPhSensor(uuid);
        return ResponseEntity.ok("OK");
    }

    @PutMapping("/setup/ph")
    @PreAuthorize("hasAnyAuthority('u_all','u_hydroponic_setup_ph')")
    public ResponseEntity<String> updateSetupPhValue(@RequestHeader(AUTHORIZATION) String token,
                                                     @RequestParam String uuid,
                                                     @RequestParam String value) {
        operationService.updateSetupPhValue(uuid, value, NUMBER);
        return ResponseEntity.ok("OK");
    }

    @PutMapping("/setup/tds")
    @PreAuthorize("hasAnyAuthority('u_all','u_hydroponic_setup_tds')")
    public ResponseEntity<String> updateSetupTdsValue(@RequestHeader(AUTHORIZATION) String token,
                                                      @RequestParam String uuid,
                                                      @RequestParam String value) {
        operationService.updateSetupTdsValue(uuid, value, NUMBER);
        return ResponseEntity.ok("OK");
    }

    @PutMapping("/update/dispensers/recheck-time")
    @PreAuthorize("hasAnyAuthority('u_all','u_hydroponic_dispensers_recheck_time')")
    public ResponseEntity<String> updateRecheckDispensersAfterTime(@RequestHeader(AUTHORIZATION) String token,
                                                                   @RequestParam String uuid,
                                                                   @RequestParam String value) {
        operationService.updateRecheckDispensersAfterTime(uuid, value, NUMBER);
        return ResponseEntity.ok("OK");
    }

    @PutMapping("/update/dose/ph/up")
    @PreAuthorize("hasAnyAuthority('u_all','u_hydroponic_dose_ph_up')")
    public ResponseEntity<String> updatePhUpDose(@RequestHeader(AUTHORIZATION) String token,
                                                 @RequestParam String uuid,
                                                 @RequestParam String value) {
        operationService.updatePhUpDose(uuid, value, NUMBER);
        return ResponseEntity.ok("OK");
    }

    @PutMapping("/update/dose/ph/down")
    @PreAuthorize("hasAnyAuthority('u_all','u_hydroponic_dose_ph_down')")
    public ResponseEntity<String> updatePhDownDose(@RequestHeader(AUTHORIZATION) String token,
                                                   @RequestParam String uuid,
                                                   @RequestParam String value) {
        operationService.updatePhDownDose(uuid, value, NUMBER);
        return ResponseEntity.ok("OK");
    }

    @PutMapping("/update/dose/tds")
    @PreAuthorize("hasAnyAuthority('u_all','u_hydroponic_dose_tds')")
    public ResponseEntity<String> updateFertilizerDose(@RequestHeader(AUTHORIZATION) String token,
                                                       @RequestParam String uuid,
                                                       @RequestParam String value) {
        operationService.updateTdsDose(uuid, value, NUMBER);
        return ResponseEntity.ok("OK");
    }

    @PutMapping("/update/regulator/error/ph")
    @PreAuthorize("hasAnyAuthority('u_all','u_hydroponic_regulator_error_ph')")
    public ResponseEntity<String> updateRegulatePhError(@RequestHeader(AUTHORIZATION) String token,
                                                        @RequestParam String uuid,
                                                        @RequestParam String value) {
        operationService.updateRegulatePhError(uuid, value, NUMBER);
        return ResponseEntity.ok("OK");
    }

    @PutMapping("/update/regulator/error/tds")
    @PreAuthorize("hasAnyAuthority('u_all','u_hydroponic_regulator_error_tds')")
    public ResponseEntity<String> updateRegulateTdsError(@RequestHeader(AUTHORIZATION) String token,
                                                         @RequestParam String uuid,
                                                         @RequestParam String value) {
        operationService.updateRegulateTdsError(uuid, value, NUMBER);
        return ResponseEntity.ok("OK");
    }

    @PutMapping("/update/pump/speed")
    @PreAuthorize("hasAnyAuthority('u_all','u_hydroponic_pump_speed')")
    public ResponseEntity<String> updatePumpSpeed(@RequestHeader(AUTHORIZATION) String token,
                                                  @RequestParam String uuid,
                                                  @RequestParam String value) {
        operationService.updatePumpSpeedMlPerMilliseconds(uuid, value, NUMBER);
        return ResponseEntity.ok("OK");
    }

    @PutMapping("/update/enable/sensors")
    @PreAuthorize("hasAnyAuthority('u_all','u_hydroponic_enable_sensors')")
    public ResponseEntity<String> updateSensorsEnable(@RequestHeader(AUTHORIZATION) String token,
                                                      @RequestParam String uuid,
                                                      @RequestParam String value) {
        operationService.updateSensorsEnable(uuid, value, SWITCH);
        return ResponseEntity.ok("OK");
    }

    @PutMapping("/update/enable/dispensers")
    @PreAuthorize("hasAnyAuthority('u_all','u_hydroponic_enable_dispensers')")
    public ResponseEntity<String> updateDispensersEnable(@RequestHeader(AUTHORIZATION) String token,
                                                         @RequestParam String uuid,
                                                         @RequestParam String value) {
        operationService.updateDispensersEnable(uuid, value, SWITCH);
        return ResponseEntity.ok("OK");
    }

    @PutMapping("/calibrate/ph/database")
    @PreAuthorize("hasAnyAuthority('u_all','u_hydroponic_ph_calibration_from_database')")
    public ResponseEntity<String> updatePhFromDatabaseData(@RequestHeader(AUTHORIZATION) String token,
                                                           @RequestBody DatabasePhCalibrationDto dto) {
        operationService.phCalibrationPoint(dto.getUuid(), dto.getCalibrationValuePoint().toString(), NUMBER);
        operationService.phCalibrationValue1(dto.getUuid(), dto.getCalibrationReferenceValue1().toString(), NUMBER);
        operationService.phCalibrationValue1(dto.getUuid(), dto.getCalibrationReferenceValue2().toString(), NUMBER);
        operationService.phCalibrationAdc1(dto.getUuid(), dto.getCalibrationAdcValue1().toString(), NUMBER);
        operationService.phCalibrationAdc2(dto.getUuid(), dto.getCalibrationAdcValue2().toString(), NUMBER);
        operationService.phCalibrationSlope(dto.getUuid(), dto.getCalibrationSlope().toString(), NUMBER);
        operationService.phCalibrationAdcOffset(dto.getUuid(), dto.getCalibrationValueOffset().toString(), NUMBER);
        operationService.phOversampling(dto.getUuid(), dto.getOversampling().toString(), NUMBER);
        return ResponseEntity.ok("OK");
    }

    @PutMapping("/calibrate/tds/database")
    @PreAuthorize("hasAnyAuthority('u_all','u_hydroponic_tds_calibration_from_database')")
    public ResponseEntity<String> updateTdsFromDatabaseData(@RequestHeader(AUTHORIZATION) String token,
                                                            @RequestBody DatabaseTdsCalibrationDto dto) {
        operationService.tdsOversampling(dto.getUuid(), dto.getOversampling().toString(), NUMBER);
        operationService.tdsKValue(dto.getUuid(), dto.getCalibrationCoefficientValue().toString(), NUMBER);
        return ResponseEntity.ok("OK");
    }
}

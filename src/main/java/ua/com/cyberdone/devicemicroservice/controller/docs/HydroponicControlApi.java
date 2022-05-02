package ua.com.cyberdone.devicemicroservice.controller.docs;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import ua.com.cyberdone.devicemicroservice.persistence.model.microcontrollers.hydroponic.DatabasePhCalibrationDto;
import ua.com.cyberdone.devicemicroservice.persistence.model.microcontrollers.hydroponic.DatabaseTdsCalibrationDto;
import ua.com.cyberdone.devicemicroservice.util.ControllerConstantUtils;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static ua.com.cyberdone.devicemicroservice.util.ValidationConstants.DIRECTION_FAILED_MSG;
import static ua.com.cyberdone.devicemicroservice.util.ValidationConstants.DIRECTION_PATTERN;
import static ua.com.cyberdone.devicemicroservice.util.ValidationConstants.SWITCH_FAILED_MSG;
import static ua.com.cyberdone.devicemicroservice.util.ValidationConstants.SWITCH_PATTERN;
import static ua.com.cyberdone.devicemicroservice.util.ValidationConstants.UUID_FAILED_MSG;
import static ua.com.cyberdone.devicemicroservice.util.ValidationConstants.UUID_PATTERN;
import static ua.com.cyberdone.devicemicroservice.util.ValidationConstants.VALUE_IS_BLANK_MSG;

@Validated
@Tag(name = "Hydroponic Control", description = "Endpoints for control hydroponic microcontrollers")
public interface HydroponicControlApi {

    @Operation(summary = "Update hydroponic ph-up pump mode", description = "Send update ph-up pump mode (RUN_LEFT = 0 | STOP = 2 | RUN_RIGHT = 1) command to hydroponic")
    @ApiResponse(responseCode = "200", description = "Send update ph-up pump mode (RUN_LEFT = 0 | STOP = 2 | RUN_RIGHT = 1) command to hydroponic",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                    schema = @Schema(implementation = String.class, example = ControllerConstantUtils.OK)))
    ResponseEntity<String> updatePhUpPumpStatus(String token,
                                                @Valid
                                                @NotBlank(message = VALUE_IS_BLANK_MSG)
                                                @Pattern(regexp = UUID_PATTERN, message = UUID_FAILED_MSG) String uuid,
                                                @Valid
                                                @NotBlank(message = VALUE_IS_BLANK_MSG)
                                                @Pattern(regexp = DIRECTION_PATTERN, message = DIRECTION_FAILED_MSG)
                                                        String value);

    @Operation(summary = "Update hydroponic ph-down pump mode", description = "Send update ph-down pump mode (RUN_LEFT = 0 | STOP = 2 | RUN_RIGHT = 1) command to hydroponic")
    @ApiResponse(responseCode = "200", description = "Send update ph-down pump mode (RUN_LEFT = 0 | STOP = 2 | RUN_RIGHT = 1) command to hydroponic",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                    schema = @Schema(implementation = String.class, example = ControllerConstantUtils.OK)))
    ResponseEntity<String> updatePhDownPumpStatus(String token,
                                                  @Valid
                                                  @NotBlank(message = VALUE_IS_BLANK_MSG)
                                                  @Pattern(regexp = UUID_PATTERN, message = UUID_FAILED_MSG)
                                                          String uuid,
                                                  @Valid
                                                  @NotBlank(message = VALUE_IS_BLANK_MSG)
                                                  @Pattern(regexp = DIRECTION_PATTERN, message = DIRECTION_FAILED_MSG)
                                                          String value);

    @Operation(summary = "Update hydroponic tds pump mode", description = "Send update tds pump mode (RUN_LEFT = 0 | STOP = 2 | RUN_RIGHT = 1) command to hydroponic")
    @ApiResponse(responseCode = "200", description = "Send update tds pump mode (RUN_LEFT = 0 | STOP = 2 | RUN_RIGHT = 1) command to hydroponic",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                    schema = @Schema(implementation = String.class, example = ControllerConstantUtils.OK)))
    ResponseEntity<String> updateTdsPumpStatus(String token,
                                               @Valid
                                               @NotBlank(message = VALUE_IS_BLANK_MSG)
                                               @Pattern(regexp = UUID_PATTERN, message = UUID_FAILED_MSG)
                                                       String uuid,
                                               @Valid
                                               @NotBlank(message = VALUE_IS_BLANK_MSG)
                                               @Pattern(regexp = DIRECTION_PATTERN, message = DIRECTION_FAILED_MSG)
                                                       String value);

    @Operation(summary = "Update polarity of pump", description = "Update pump`s polarity. For example if pump is rotating left, then after the change it will be rotating in right direction.")
    @ApiResponse(responseCode = "200", description = "Update pump`s polarity. For example if pump is rotating left, then after the change it will be rotating in right direction.",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                    schema = @Schema(implementation = String.class, example = ControllerConstantUtils.OK)))
    ResponseEntity<String> updatePumpPolarity(String token,
                                              @Valid
                                              @NotBlank(message = VALUE_IS_BLANK_MSG)
                                              @Pattern(regexp = UUID_PATTERN, message = UUID_FAILED_MSG)
                                                      String uuid,
                                              String pumpNumber);

    @Operation(summary = "Calibrate tds sensor", description = "Calibrate tds sensor of hydroponic microcontroller")
    @ApiResponse(responseCode = "200", description = "Calibrate tds sensor of hydroponic microcontroller",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                    schema = @Schema(implementation = String.class, example = ControllerConstantUtils.OK)))
    ResponseEntity<String> calibrateTdsSensor(String token,
                                              @Valid
                                              @NotBlank(message = VALUE_IS_BLANK_MSG)
                                              @Pattern(regexp = UUID_PATTERN, message = UUID_FAILED_MSG)
                                                      String uuid,
                                              @Valid
                                              @NotBlank(message = VALUE_IS_BLANK_MSG)
                                                      String value);

    @Operation(summary = "Clear calibration of tds sensor", description = "Clear calibration of tds sensor of hydroponic microcontroller")
    @ApiResponse(responseCode = "200", description = "Clear calibration of tds sensor of hydroponic microcontroller",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                    schema = @Schema(implementation = String.class, example = ControllerConstantUtils.OK)))
    ResponseEntity<String> clrCalibrationTdsSensor(String token,
                                                   @Valid
                                                   @NotBlank(message = VALUE_IS_BLANK_MSG)
                                                   @Pattern(regexp = UUID_PATTERN, message = UUID_FAILED_MSG)
                                                           String uuid);

    @Operation(summary = "Calibration of low point of ph sensor", description = "Calibration of low point of ph sensor of hydroponic microcontroller")
    @ApiResponse(responseCode = "200", description = "Calibration of low point of ph sensor of hydroponic microcontroller",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                    schema = @Schema(implementation = String.class, example = ControllerConstantUtils.OK)))
    ResponseEntity<String> calibratePhLow(String token,
                                          @Valid
                                          @NotBlank(message = VALUE_IS_BLANK_MSG)
                                          @Pattern(regexp = UUID_PATTERN, message = UUID_FAILED_MSG)
                                                  String uuid,
                                          @Valid
                                          @NotBlank(message = VALUE_IS_BLANK_MSG)
                                                  String value);

    @Operation(summary = "Calibration of high point of ph sensor", description = "Calibration of high point of ph sensor of hydroponic microcontroller")
    @ApiResponse(responseCode = "200", description = "Calibration of high point of ph sensor of hydroponic microcontroller",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                    schema = @Schema(implementation = String.class, example = ControllerConstantUtils.OK)))
    ResponseEntity<String> calibratePhHigh(String token,
                                           @Valid
                                           @NotBlank(message = VALUE_IS_BLANK_MSG)
                                           @Pattern(regexp = UUID_PATTERN, message = UUID_FAILED_MSG)
                                                   String uuid,
                                           @Valid
                                           @NotBlank(message = VALUE_IS_BLANK_MSG)
                                                   String value);

    @Operation(summary = "Clear calibration of ph sensor", description = "Clear calibration of ph sensor of hydroponic microcontroller")
    @ApiResponse(responseCode = "200", description = "Clear calibration of ph sensor of hydroponic microcontroller",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                    schema = @Schema(implementation = String.class, example = ControllerConstantUtils.OK)))
    ResponseEntity<String> clrCalibrationPhSensor(String token,
                                                  @Valid
                                                  @NotBlank(message = VALUE_IS_BLANK_MSG)
                                                  @Pattern(regexp = UUID_PATTERN, message = UUID_FAILED_MSG)
                                                          String uuid);

    @Operation(summary = "Change ph value to maintain by controller", description = "Change ph value to maintain by controller")
    @ApiResponse(responseCode = "200", description = "Change ph value to maintain by controller",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                    schema = @Schema(implementation = String.class, example = ControllerConstantUtils.OK)))
    ResponseEntity<String> updateSetupPhValue(String token,
                                              @Valid
                                              @NotBlank(message = VALUE_IS_BLANK_MSG)
                                              @Pattern(regexp = UUID_PATTERN, message = UUID_FAILED_MSG)
                                                      String uuid,
                                              @Valid
                                              @NotBlank(message = VALUE_IS_BLANK_MSG)
                                                      String value);

    @Operation(summary = "Change tds value to maintain by controller", description = "Change tds value to maintain by controller")
    @ApiResponse(responseCode = "200", description = "Change tds value to maintain by controller",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                    schema = @Schema(implementation = String.class, example = ControllerConstantUtils.OK)))
    ResponseEntity<String> updateSetupTdsValue(String token,
                                               @Valid
                                               @NotBlank(message = VALUE_IS_BLANK_MSG)
                                               @Pattern(regexp = UUID_PATTERN, message = UUID_FAILED_MSG)
                                                       String uuid,
                                               @Valid
                                               @NotBlank(message = VALUE_IS_BLANK_MSG)
                                                       String value);

    @Operation(summary = "Change recheck dispensers time", description = "Change time to recheck dispensers after")
    @ApiResponse(responseCode = "200", description = "Change time to recheck dispensers after",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                    schema = @Schema(implementation = String.class, example = ControllerConstantUtils.OK)))
    ResponseEntity<String> updateRecheckDispensersAfterTime(String token,
                                                            @Valid
                                                            @NotBlank(message = VALUE_IS_BLANK_MSG)
                                                            @Pattern(regexp = UUID_PATTERN, message = UUID_FAILED_MSG)
                                                                    String uuid,
                                                            @Valid
                                                            @NotBlank(message = VALUE_IS_BLANK_MSG)
                                                                    String value);

    @Operation(summary = "Change dose for ph up pump one time injection", description = "Change dose for ph up pump one time injection")
    @ApiResponse(responseCode = "200", description = "Change dose for ph up pump one time injection",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                    schema = @Schema(implementation = String.class, example = ControllerConstantUtils.OK)))
    ResponseEntity<String> updatePhUpDose(String token,
                                          @Valid
                                          @NotBlank(message = VALUE_IS_BLANK_MSG)
                                          @Pattern(regexp = UUID_PATTERN, message = UUID_FAILED_MSG)
                                                  String uuid,
                                          @Valid
                                          @NotBlank(message = VALUE_IS_BLANK_MSG)
                                                  String value);

    @Operation(summary = "Change dose for ph down pump one time injection", description = "Change dose for ph down pump one time injection")
    @ApiResponse(responseCode = "200", description = "Change dose for ph down pump one time injection",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                    schema = @Schema(implementation = String.class, example = ControllerConstantUtils.OK)))
    ResponseEntity<String> updatePhDownDose(String token,
                                            @Valid
                                            @NotBlank(message = VALUE_IS_BLANK_MSG)
                                            @Pattern(regexp = UUID_PATTERN, message = UUID_FAILED_MSG)
                                                    String uuid,
                                            @Valid
                                            @NotBlank(message = VALUE_IS_BLANK_MSG)
                                                    String value);

    @Operation(summary = "Change dose for tds pump one time injection", description = "Change dose for tds pump one time injection")
    @ApiResponse(responseCode = "200", description = "Change dose for tds pump one time injection",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                    schema = @Schema(implementation = String.class, example = ControllerConstantUtils.OK)))
    ResponseEntity<String> updateFertilizerDose(String token,
                                                @Valid
                                                @NotBlank(message = VALUE_IS_BLANK_MSG)
                                                @Pattern(regexp = UUID_PATTERN, message = UUID_FAILED_MSG)
                                                        String uuid,
                                                @Valid
                                                @NotBlank(message = VALUE_IS_BLANK_MSG)
                                                        String value);

    @Operation(summary = "Change error for ph pump", description = "Change error for ph pump")
    @ApiResponse(responseCode = "200", description = "Change error for ph pump",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                    schema = @Schema(implementation = String.class, example = ControllerConstantUtils.OK)))
    ResponseEntity<String> updateRegulatePhError(String token,
                                                 @Valid
                                                 @NotBlank(message = VALUE_IS_BLANK_MSG)
                                                 @Pattern(regexp = UUID_PATTERN, message = UUID_FAILED_MSG)
                                                         String uuid,
                                                 @Valid
                                                 @NotBlank(message = VALUE_IS_BLANK_MSG)
                                                         String value);

    @Operation(summary = "Change error for tds pump", description = "Change error for tds pump")
    @ApiResponse(responseCode = "200", description = "Change error for tds pump",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                    schema = @Schema(implementation = String.class, example = ControllerConstantUtils.OK)))
    ResponseEntity<String> updateRegulateTdsError(String token,
                                                  @Valid
                                                  @NotBlank(message = VALUE_IS_BLANK_MSG)
                                                  @Pattern(regexp = UUID_PATTERN, message = UUID_FAILED_MSG)
                                                          String uuid,
                                                  @Valid
                                                  @NotBlank(message = VALUE_IS_BLANK_MSG)
                                                          String value);

    @Operation(summary = "Change pumping speed", description = "Change pumping speed")
    @ApiResponse(responseCode = "200", description = "Change pumping speed",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                    schema = @Schema(implementation = String.class, example = ControllerConstantUtils.OK)))
    ResponseEntity<String> updatePumpSpeed(String token,
                                           @Valid
                                           @NotBlank(message = VALUE_IS_BLANK_MSG)
                                           @Pattern(regexp = UUID_PATTERN, message = UUID_FAILED_MSG)
                                                   String uuid,
                                           @Valid
                                           @NotBlank(message = VALUE_IS_BLANK_MSG)
                                                   String value);

    @Operation(summary = "Update hydroponic sensors availability", description = "Update hydroponic sensors availability (ENABLED=1 | DISABLED=0)")
    @ApiResponse(responseCode = "200", description = "Update hydroponic sensors availability (ENABLED=1 | DISABLED=0)",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                    schema = @Schema(implementation = String.class, example = ControllerConstantUtils.OK)))
    ResponseEntity<String> updateSensorsEnable(String token,
                                               @Valid
                                               @NotBlank(message = VALUE_IS_BLANK_MSG)
                                               @Pattern(regexp = UUID_PATTERN, message = UUID_FAILED_MSG)
                                                       String uuid,
                                               @Valid
                                               @NotBlank(message = VALUE_IS_BLANK_MSG)
                                               @Pattern(regexp = SWITCH_PATTERN, message = SWITCH_FAILED_MSG)
                                                       String value);

    @Operation(summary = "Update hydroponic dispensers availability", description = "Update hydroponic dispensers availability (ENABLED=1 | DISABLED=0)")
    @ApiResponse(responseCode = "200", description = "Update hydroponic dispensers availability (ENABLED=1 | DISABLED=0)",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                    schema = @Schema(implementation = String.class, example = ControllerConstantUtils.OK)))
    ResponseEntity<String> updateDispensersEnable(String token,
                                                  @Valid
                                                  @NotBlank(message = VALUE_IS_BLANK_MSG)
                                                  @Pattern(regexp = UUID_PATTERN, message = UUID_FAILED_MSG)
                                                          String uuid,
                                                  @Valid
                                                  @NotBlank(message = VALUE_IS_BLANK_MSG)
                                                  @Pattern(regexp = SWITCH_PATTERN, message = SWITCH_FAILED_MSG)
                                                          String value);

    @Operation(summary = "Update PH calibration using data earlier saved in the DB", description = "Update PH calibration using data earlier saved in the DB")
    @ApiResponse(responseCode = "200", description = "Update PH calibration using data earlier saved in the DB",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                    schema = @Schema(implementation = String.class, example = ControllerConstantUtils.OK)))
    ResponseEntity<String> updatePhFromDatabaseData(@RequestHeader(AUTHORIZATION) String token,
                                                    @RequestBody @Valid DatabasePhCalibrationDto dto);

    @Operation(summary = "Update TDS calibration using data earlier saved in the DB", description = "Update TDS calibration using data earlier saved in the DB")
    @ApiResponse(responseCode = "200", description = "Update TDS calibration using data earlier saved in the DB",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                    schema = @Schema(implementation = String.class, example = ControllerConstantUtils.OK)))
    ResponseEntity<String> updateTdsFromDatabaseData(@RequestHeader(AUTHORIZATION) String token,
                                                     @RequestBody @Valid DatabaseTdsCalibrationDto dto);
}

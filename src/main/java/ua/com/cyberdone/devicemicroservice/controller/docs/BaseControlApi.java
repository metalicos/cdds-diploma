package ua.com.cyberdone.devicemicroservice.controller.docs;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import ua.com.cyberdone.devicemicroservice.constant.ControllerConstantUtils;
import ua.com.cyberdone.devicemicroservice.model.microcontrollers.hydroponic.HydroponicTimeDto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import static ua.com.cyberdone.devicemicroservice.constant.ValidationConstants.TEXT_FAILED_MSG;
import static ua.com.cyberdone.devicemicroservice.constant.ValidationConstants.TEXT_PATTERN;
import static ua.com.cyberdone.devicemicroservice.constant.ValidationConstants.UUID_FAILED_MSG;
import static ua.com.cyberdone.devicemicroservice.constant.ValidationConstants.UUID_PATTERN;
import static ua.com.cyberdone.devicemicroservice.constant.ValidationConstants.VALUE_IS_BLANK_MSG;

@Validated
@Tag(name = "Base Control", description = "Endpoints base for device control")
public interface BaseControlApi {

    @Operation(summary = "Update time", description = "Send update time command")
    @ApiResponse(responseCode = "200", description = "Send update time command",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                    schema = @Schema(implementation = String.class, example = ControllerConstantUtils.OK)))
    ResponseEntity<String> updateTime(String token, @Valid HydroponicTimeDto dto);

    @Operation(summary = "Update time zone", description = "Send update time zone command")
    @ApiResponse(responseCode = "200", description = "Send update time zone command",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                    schema = @Schema(implementation = String.class, example = ControllerConstantUtils.OK)))
    ResponseEntity<String> updateZone(String token,
                                      @Valid
                                      @NotBlank(message = VALUE_IS_BLANK_MSG)
                                      @Pattern(regexp = UUID_PATTERN, message = UUID_FAILED_MSG)
                                              String uuid,
                                      @Valid
                                      @NotBlank(message = VALUE_IS_BLANK_MSG)
                                              String value);

    @Operation(summary = "Update auto time mode", description = "Send update auto time mode command")
    @ApiResponse(responseCode = "200", description = "Send update auto time mode command",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                    schema = @Schema(implementation = String.class, example = ControllerConstantUtils.OK)))
    ResponseEntity<String> updateAutoTime(String token,
                                          @Valid
                                          @NotBlank(message = VALUE_IS_BLANK_MSG)
                                          @Pattern(regexp = UUID_PATTERN, message = UUID_FAILED_MSG)
                                                  String uuid,
                                          @Valid
                                          @NotBlank(message = VALUE_IS_BLANK_MSG)
                                                  String value);


    @Operation(summary = "Restart microcontroller", description = "Restart microcontroller")
    @ApiResponse(responseCode = "200", description = "Restart microcontroller",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                    schema = @Schema(implementation = String.class, example = ControllerConstantUtils.OK)))
    ResponseEntity<String> restart(String token,
                                   @Valid
                                   @NotBlank(message = VALUE_IS_BLANK_MSG)
                                   @Pattern(regexp = UUID_PATTERN, message = UUID_FAILED_MSG)
                                           String uuid);

    @Operation(summary = "Update restart counter value", description = "Update restart counter")
    @ApiResponse(responseCode = "200", description = "Update restart counter",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                    schema = @Schema(implementation = String.class, example = ControllerConstantUtils.OK)))
    ResponseEntity<String> restartCounter(String token,
                                          @Valid
                                          @NotBlank(message = VALUE_IS_BLANK_MSG)
                                          @Pattern(regexp = UUID_PATTERN, message = UUID_FAILED_MSG)
                                                  String uuid,
                                          @Valid
                                          @NotBlank(message = VALUE_IS_BLANK_MSG)
                                                  String value);

    @Operation(summary = "Save all settings to device memory", description = "Save all settings to device memory")
    @ApiResponse(responseCode = "200", description = "Save all settings to device memory",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                    schema = @Schema(implementation = String.class, example = ControllerConstantUtils.OK)))
    ResponseEntity<String> saveAllSettings(String token,
                                           @Valid
                                           @NotBlank(message = VALUE_IS_BLANK_MSG)
                                           @Pattern(regexp = UUID_PATTERN, message = UUID_FAILED_MSG)
                                                   String uuid);

    @Operation(summary = "Read all settings from device memory", description = "Read all settings from device memory")
    @ApiResponse(responseCode = "200", description = "Read all settings from device memory",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                    schema = @Schema(implementation = String.class, example = ControllerConstantUtils.OK)))
    ResponseEntity<String> readAllSettings(String token,
                                           @Valid
                                           @NotBlank(message = VALUE_IS_BLANK_MSG)
                                           @Pattern(regexp = UUID_PATTERN, message = UUID_FAILED_MSG)
                                                   String uuid);

    @Operation(summary = "Update ssid in device for further Internet connections", description = "Update ssid in device for further Internet connections")
    @ApiResponse(responseCode = "200", description = "Update ssid in device for further Internet connections",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                    schema = @Schema(implementation = String.class, example = ControllerConstantUtils.OK)))
    ResponseEntity<String> updateWifiSsid(String token,
                                          @Valid
                                          @NotBlank(message = VALUE_IS_BLANK_MSG)
                                          @Pattern(regexp = UUID_PATTERN, message = UUID_FAILED_MSG)
                                                  String uuid,
                                          @Valid
                                          @NotBlank(message = VALUE_IS_BLANK_MSG)
                                          @Pattern(regexp = TEXT_PATTERN, message = TEXT_FAILED_MSG)
                                                  String value);

    @Operation(summary = "Update password in device for further Internet connections", description = "Update password in device for further Internet connections")
    @ApiResponse(responseCode = "200", description = "Update password in device for further Internet connections",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                    schema = @Schema(implementation = String.class, example = ControllerConstantUtils.OK)))
    ResponseEntity<String> updateWifiPassword(String token,
                                              @Valid
                                              @NotBlank(message = VALUE_IS_BLANK_MSG)
                                              @Pattern(regexp = UUID_PATTERN, message = UUID_FAILED_MSG)
                                                      String uuid,
                                              @Valid
                                              @NotBlank(message = VALUE_IS_BLANK_MSG)
                                              @Pattern(regexp = TEXT_PATTERN, message = TEXT_FAILED_MSG)
                                                      String value);
}

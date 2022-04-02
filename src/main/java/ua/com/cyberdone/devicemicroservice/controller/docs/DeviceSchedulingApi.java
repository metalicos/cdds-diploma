package ua.com.cyberdone.devicemicroservice.controller.docs;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import ua.com.cyberdone.devicemicroservice.constant.ControllerConstantUtils;
import ua.com.cyberdone.devicemicroservice.model.RegularScheduleDto;
import ua.com.cyberdone.devicemicroservice.model.RegularScheduleUpdateDto;

import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

import static ua.com.cyberdone.devicemicroservice.constant.ValidationConstants.UUID_FAILED_MSG;
import static ua.com.cyberdone.devicemicroservice.constant.ValidationConstants.UUID_PATTERN;
import static ua.com.cyberdone.devicemicroservice.constant.ValidationConstants.VALUE_IS_BLANK_MSG;
import static ua.com.cyberdone.devicemicroservice.constant.ValidationConstants.VALUE_IS_NULL_MSG;
import static ua.com.cyberdone.devicemicroservice.constant.ValidationConstants.VALUE_NOT_NUMBER_MSG;

@Validated
@Tag(name = "Device Scheduling", description = "Endpoints for managing process of scheduling device operations")
public interface DeviceSchedulingApi {

    @Operation(summary = "Read device schedules by device UUID", description = "Return device schedules. Find schedules by device unique identifier UUID.")
    @ApiResponse(responseCode = "200", description = "Return device schedules. Find schedules by device unique identifier UUID.",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    array = @ArraySchema(schema = @Schema(implementation = RegularScheduleDto.class))))
    ResponseEntity<List<RegularScheduleDto>> getSchedulesByKey(
            String token,
            @Valid @NotBlank(message = VALUE_IS_BLANK_MSG) @Pattern(regexp = UUID_PATTERN, message = UUID_FAILED_MSG)
                    String uuid,
            @Valid @NotBlank(message = VALUE_IS_BLANK_MSG)
                    String key);

    @Operation(summary = "Create schedule for device", description = "Create schedule for device.")
    @ApiResponse(responseCode = "200", description = "Create schedule for device",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = RegularScheduleDto.class)))
    ResponseEntity<RegularScheduleDto> createSchedule(String token, @Valid RegularScheduleDto schedule);

    @Operation(summary = "Update schedule metadata (name && description)", description = "Update description and name for device schedule.")
    @ApiResponse(responseCode = "200", description = "Update description and name for device schedule.",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = String.class, example = ControllerConstantUtils.OK)))
    ResponseEntity<String> updateScheduleMetaInfo(String token, @Valid RegularScheduleUpdateDto schedule);

    @Operation(summary = "Delete schedule", description = "Delete schedule by id")
    @ApiResponse(responseCode = "200", description = "Delete schedule by id",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = String.class, example = ControllerConstantUtils.OK)))
    ResponseEntity<String> deleteScheduleById(
            String token,
            @Valid @NotNull(message = VALUE_IS_NULL_MSG)
            @Digits(message = VALUE_NOT_NUMBER_MSG, integer = Integer.MAX_VALUE, fraction = 10)
                    Long id);
}

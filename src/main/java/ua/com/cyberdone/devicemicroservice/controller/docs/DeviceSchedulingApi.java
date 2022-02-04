package ua.com.cyberdone.devicemicroservice.controller.docs;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import ua.com.cyberdone.devicemicroservice.constant.ControllerConstantUtils;
import ua.com.cyberdone.devicemicroservice.model.dto.RegularScheduleDto;
import ua.com.cyberdone.devicemicroservice.model.dto.RegularScheduleUpdateDto;

import java.util.List;

@Tag(name = "Device Scheduling", description = "Endpoints for managing process of scheduling device operations")
public interface DeviceSchedulingApi {

    @Operation(summary = "Read device schedules by device UUID", description = "Return device schedules. Find schedules by device unique identifier UUID.")
    @ApiResponse(responseCode = "200", description = "Return device schedules. Find schedules by device unique identifier UUID.",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    array = @ArraySchema(schema = @Schema(implementation = RegularScheduleDto.class))))
    ResponseEntity<List<RegularScheduleDto>> getSchedulesByKey(String token, String uuid, String key);

    @Operation(summary = "Create schedule for device", description = "Create schedule for device.")
    @ApiResponse(responseCode = "200", description = "Create schedule for device",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = RegularScheduleDto.class)))
    ResponseEntity<RegularScheduleDto> createSchedule(String token, RegularScheduleDto schedule);

    @Operation(summary = "Update schedule metadata (name && description)", description = "Update description and name for device schedule.")
    @ApiResponse(responseCode = "200", description = "Update description and name for device schedule.",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = String.class, example = ControllerConstantUtils.OK)))
    ResponseEntity<String> updateScheduleMetaInfo(String token, RegularScheduleUpdateDto schedule);

    @Operation(summary = "Delete schedule", description = "Delete schedule by id")
    @ApiResponse(responseCode = "200", description = "Delete schedule by id",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = String.class, example = ControllerConstantUtils.OK)))
    ResponseEntity<String> deleteScheduleById(String token, Long id);
}

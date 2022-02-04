package ua.com.cyberdone.devicemicroservice.controller.docs;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import ua.com.cyberdone.devicemicroservice.model.dto.microcontrollers.hydroponic.HydroponicDataDto;

import java.util.List;

@Tag(name = "Hydroponic Data", description = "Endpoints for managing hydroponic device data")
public interface HydroponicDataApi {

    @Operation(summary = "Read last hydroponic data", description = "Return last {amount} of hydroponic data.")
    @ApiResponse(responseCode = "200", description = "Return last {amount} of hydroponic data.",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    array = @ArraySchema(schema = @Schema(implementation = HydroponicDataDto.class))))
    ResponseEntity<List<HydroponicDataDto>> getLastDataInDeviceWithUuid(String token, String uuid, Integer page, Integer limit);

    @Operation(summary = "Delete all hydroponic data for device", description = "Delete all hydroponic data for device with UUID")
    @ApiResponse(responseCode = "200", description = "Delete all hydroponic data for device with UUID")
    ResponseEntity<Void> deleteAllDataInDeviceWithUuid(String token, String uuid);
}

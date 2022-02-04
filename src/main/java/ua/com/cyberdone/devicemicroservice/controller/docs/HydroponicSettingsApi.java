package ua.com.cyberdone.devicemicroservice.controller.docs;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import ua.com.cyberdone.devicemicroservice.model.dto.microcontrollers.hydroponic.HydroponicSettingsDto;

import java.util.List;

@Tag(name = "Hydroponic Settings", description = "Endpoints for managing hydroponic device settings")
public interface HydroponicSettingsApi {

    @Operation(summary = "Read last hydroponic settings", description = "Return last {amount} of hydroponic settings.")
    @ApiResponse(responseCode = "200", description = "Return last {amount} of hydroponic settings.",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    array = @ArraySchema(schema = @Schema(implementation = HydroponicSettingsDto.class))))
    ResponseEntity<List<HydroponicSettingsDto>> getLastSettingsInDeviceWithUuid(String token, String uuid, Integer page, Integer limit);
}

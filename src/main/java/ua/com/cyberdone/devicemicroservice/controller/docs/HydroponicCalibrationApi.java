package ua.com.cyberdone.devicemicroservice.controller.docs;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import ua.com.cyberdone.devicemicroservice.model.dto.microcontrollers.hydroponic.HydroponicCalibrationDataDto;

import java.util.List;

@Tag(name = "Hydroponic Calibration", description = "Endpoints for hydroponic calibration purpose")
public interface HydroponicCalibrationApi {

    @Operation(summary = "Read last hydroponic calibration", description = "Return last {amount} of hydroponic calibration.")
    @ApiResponse(responseCode = "200", description = "Return last {amount} of hydroponic calibration.",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    array = @ArraySchema(schema = @Schema(implementation = HydroponicCalibrationDataDto.class))))
    ResponseEntity<List<HydroponicCalibrationDataDto>> getLastCalibrationDataInDeviceWithUuid(String token, String uuid,
                                                                                              Integer page, Integer limit);

    @Operation(summary = "Delete hydroponic calibration by id", description = "Delete hydroponic calibration by id")
    @ApiResponse(responseCode = "200", description = "Delete hydroponic calibration by id")
    ResponseEntity<Void> deleteCalibrationDataByUuid(String token, String uuid);
}

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
import ua.com.cyberdone.devicemicroservice.persistence.model.microcontrollers.hydroponic.HydroponicSettingsDto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

import static ua.com.cyberdone.devicemicroservice.util.ValidationConstants.NOT_POSITIVE_MSG;
import static ua.com.cyberdone.devicemicroservice.util.ValidationConstants.NOT_POSITIVE_OR_ZERO_MSG;
import static ua.com.cyberdone.devicemicroservice.util.ValidationConstants.UUID_FAILED_MSG;
import static ua.com.cyberdone.devicemicroservice.util.ValidationConstants.UUID_PATTERN;
import static ua.com.cyberdone.devicemicroservice.util.ValidationConstants.VALUE_IS_BLANK_MSG;
import static ua.com.cyberdone.devicemicroservice.util.ValidationConstants.VALUE_IS_NULL_MSG;

@Validated
@Tag(name = "Hydroponic Settings", description = "Endpoints for managing hydroponic device settings")
public interface HydroponicSettingsApi {

    @Operation(summary = "Read last hydroponic settings", description = "Return last {amount} of hydroponic settings.")
    @ApiResponse(responseCode = "200", description = "Return last {amount} of hydroponic settings.",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    array = @ArraySchema(schema = @Schema(implementation = HydroponicSettingsDto.class))))
    ResponseEntity<List<HydroponicSettingsDto>> getLastSettingsInDeviceWithUuid(
            String token,
            @Valid
            @NotBlank(message = VALUE_IS_BLANK_MSG)
            @Pattern(regexp = UUID_PATTERN, message = UUID_FAILED_MSG)
                    String uuid,
            @Valid
            @NotNull(message = VALUE_IS_NULL_MSG)
            @PositiveOrZero(message = NOT_POSITIVE_OR_ZERO_MSG)
                    Integer page,
            @Valid
            @NotNull(message = VALUE_IS_NULL_MSG)
            @Positive(message = NOT_POSITIVE_MSG)
                    Integer limit);
}

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
import ua.com.cyberdone.devicemicroservice.persistence.model.microcontrollers.hydroponic.HydroponicSettingTemplateDto;
import ua.com.cyberdone.devicemicroservice.persistence.model.microcontrollers.hydroponic.HydroponicSettingsDto;
import ua.com.cyberdone.devicemicroservice.util.ControllerConstantUtils;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

import static ua.com.cyberdone.devicemicroservice.util.ValidationConstants.NOT_POSITIVE_MSG;
import static ua.com.cyberdone.devicemicroservice.util.ValidationConstants.NOT_POSITIVE_OR_ZERO_MSG;
import static ua.com.cyberdone.devicemicroservice.util.ValidationConstants.VALUE_IS_NULL_MSG;

@Validated
@Tag(name = "Hydroponic Setting Template", description = "Endpoints for CRUD operations with setting templates for user")
public interface HydroponicSettingTemplateApi {

    @Operation(summary = "Read hydroponic setting templates", description = "Return setting templates of concrete user.")
    @ApiResponse(responseCode = "200", description = "Return setting templates with pagination for user by id. UserId is processed from JWT token.",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    array = @ArraySchema(schema = @Schema(implementation = HydroponicSettingsDto.class))))
    ResponseEntity<List<HydroponicSettingsDto>> getLastSettingTemplate(
            String token,
            @Valid
            @NotNull(message = VALUE_IS_NULL_MSG)
            @PositiveOrZero(message = NOT_POSITIVE_OR_ZERO_MSG)
                    Integer page,
            @Valid
            @NotNull(message = VALUE_IS_NULL_MSG)
            @Positive(message = NOT_POSITIVE_MSG)
                    Integer limit);

    @Operation(summary = "Create hydroponic setting template", description = "Create hydroponic setting template for user.")
    @ApiResponse(responseCode = "200", description = "Create hydroponic setting template for user.",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                    schema = @Schema(implementation = HydroponicSettingTemplateDto.class)))
    ResponseEntity<HydroponicSettingTemplateDto> createHydroponicSettingTemplate(String token, @Valid HydroponicSettingTemplateDto dto);

    @Operation(summary = "Update hydroponic setting template", description = "Update hydroponic setting template for user.")
    @ApiResponse(responseCode = "200", description = "Update hydroponic setting template for user.",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                    schema = @Schema(implementation = String.class, example = ControllerConstantUtils.OK)))
    ResponseEntity<String> updateHydroponicSettingTemplate(String token, @Valid HydroponicSettingTemplateDto dto);

    @Operation(summary = "Delete hydroponic setting template", description = "Delete hydroponic setting template by its id")
    @ApiResponse(responseCode = "200", description = "Delete hydroponic setting template by its id",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                    schema = @Schema(implementation = String.class, example = ControllerConstantUtils.OK)))
    ResponseEntity<String> deleteHydroponicSettingTemplate(
            String token,
            @Valid
            @NotNull(message = VALUE_IS_NULL_MSG)
            @Positive(message = NOT_POSITIVE_MSG)
                    Long templateId);
}

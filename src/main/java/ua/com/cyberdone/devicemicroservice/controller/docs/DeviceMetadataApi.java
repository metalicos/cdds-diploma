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
import org.springframework.web.multipart.MultipartFile;
import ua.com.cyberdone.devicemicroservice.persistence.entity.DeviceType;
import ua.com.cyberdone.devicemicroservice.persistence.model.DeviceMetadataDto;
import ua.com.cyberdone.devicemicroservice.persistence.model.SaveDeviceMetadataDto;
import ua.com.cyberdone.devicemicroservice.util.ControllerConstantUtils;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.io.IOException;
import java.util.List;

import static ua.com.cyberdone.devicemicroservice.util.ValidationConstants.NOT_POSITIVE_MSG;
import static ua.com.cyberdone.devicemicroservice.util.ValidationConstants.UUID_FAILED_MSG;
import static ua.com.cyberdone.devicemicroservice.util.ValidationConstants.UUID_PATTERN;
import static ua.com.cyberdone.devicemicroservice.util.ValidationConstants.VALUE_IS_BLANK_MSG;
import static ua.com.cyberdone.devicemicroservice.util.ValidationConstants.VALUE_IS_NULL_MSG;

@Validated
@Tag(name = "Device Metadata", description = "Endpoints for managing device metadata")
public interface DeviceMetadataApi {

    @Operation(summary = "Read device metadata by device UUID", description = "Return device metadata. Find metadata by device unique identifier UUID.")
    @ApiResponse(responseCode = "200", description = "Return device metadata. Find metadata by device unique identifier UUID.",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = DeviceMetadataDto.class)))
    ResponseEntity<DeviceMetadataDto> getMetadataByUuid(
            String token,
            @Valid @NotBlank(message = VALUE_IS_BLANK_MSG)
            @Pattern(regexp = UUID_PATTERN, message = UUID_FAILED_MSG)
                    String uuid);

    @Operation(summary = "Read list of device metadata by owner user", description = "Return device metadata list. Find metadata by device owner.")
    @ApiResponse(responseCode = "200", description = "Return device metadata list. Find metadata by device owner.",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    array = @ArraySchema(schema = @Schema(implementation = DeviceMetadataDto.class))))
    ResponseEntity<List<DeviceMetadataDto>> getMetadataByUser(
            String token,
            @Valid @NotNull(message = VALUE_IS_NULL_MSG)
            @Positive(message = NOT_POSITIVE_MSG)
                    Long userId);

    @Operation(summary = "Update device metadata (name && description)", description = "Update description and name of the device. User customization purpose.")
    @ApiResponse(responseCode = "200", description = "Update description and name of the device. User customization purpose.",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                    schema = @Schema(implementation = String.class, example = ControllerConstantUtils.OK)))
    ResponseEntity<String> updateMetadata(
            String token,
            @Valid @NotBlank(message = VALUE_IS_BLANK_MSG)
            @Pattern(regexp = UUID_PATTERN, message = UUID_FAILED_MSG)
                    String uuid,
            @Valid @NotBlank(message = VALUE_IS_BLANK_MSG)
                    String name,
            @Valid @NotBlank(message = VALUE_IS_BLANK_MSG)
                    String description,
            @Valid @NotNull(message = VALUE_IS_NULL_MSG)
                    MultipartFile deviceImage)
            throws IOException;

    @Operation(summary = "Create device metadata", description = "Creates metadata for device.")
    @ApiResponse(responseCode = "200", description = "Create device metadata, also creates a device, because metadata is the main concept.",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = DeviceMetadataDto.class)))
    ResponseEntity<DeviceMetadataDto> createMetadata(
            String token,
            @Valid SaveDeviceMetadataDto metadataDto);

    @Operation(summary = "Delete device metadata", description = "Permanent delete device metadata.")
    @ApiResponse(responseCode = "200", description = "Permanent delete device metadata.",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                    schema = @Schema(implementation = String.class, example = ControllerConstantUtils.OK)))
    ResponseEntity<String> deleteMetadata(
            String token,
            @Valid @NotBlank(message = VALUE_IS_BLANK_MSG)
            @Pattern(regexp = UUID_PATTERN, message = UUID_FAILED_MSG)
                    String uuid);

    @Operation(summary = "Read device types", description = "Get all available device types.")
    @ApiResponse(responseCode = "200", description = "Get all available device types.",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                    schema = @Schema(implementation = String.class)))
    ResponseEntity<DeviceType[]> getDeviceTypesList(String token);

    @Operation(summary = "Unlink device from user", description = "Unlink device with UUID from user with ID")
    @ApiResponse(responseCode = "200", description = "Unlink device with UUID from user with ID",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                    schema = @Schema(implementation = String.class)))
    ResponseEntity<String> unlinkMetadataFromUser(
            String token,
            @Valid @NotBlank(message = VALUE_IS_BLANK_MSG)
            @Pattern(regexp = UUID_PATTERN, message = UUID_FAILED_MSG)
                    String uuid);

    @Operation(summary = "Link device to user", description = "Link device with UUID to user with ID")
    @ApiResponse(responseCode = "200", description = "Link device with UUID to user with ID",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                    schema = @Schema(implementation = String.class, example = ControllerConstantUtils.OK)))
    ResponseEntity<String> linkMetadataToUser(
            String token,
            @Valid @NotBlank(message = VALUE_IS_BLANK_MSG)
            @Pattern(regexp = UUID_PATTERN, message = UUID_FAILED_MSG)
                    String uuid,
            @Valid @NotNull(message = VALUE_IS_NULL_MSG)
            @Positive(message = NOT_POSITIVE_MSG)
                    Long userId);
}

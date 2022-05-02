package ua.com.cyberdone.devicemicroservice.controller.docs;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import ua.com.cyberdone.devicemicroservice.exception.AlreadyExistException;
import ua.com.cyberdone.devicemicroservice.exception.IllegalOperationException;
import ua.com.cyberdone.devicemicroservice.exception.NotFoundException;
import ua.com.cyberdone.devicemicroservice.persistence.entity.DelegationStatus;
import ua.com.cyberdone.devicemicroservice.persistence.model.delegation.DelegatedDeviceControlDto;
import ua.com.cyberdone.devicemicroservice.persistence.model.delegation.PageableDelegatedDeviceControlDto;
import ua.com.cyberdone.devicemicroservice.util.ControllerConstantUtils;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import static ua.com.cyberdone.devicemicroservice.util.ValidationConstants.EMAIL_FAIL_MESSAGE;
import static ua.com.cyberdone.devicemicroservice.util.ValidationConstants.EMAIL_RGX;
import static ua.com.cyberdone.devicemicroservice.util.ValidationConstants.UUID_FAILED_MSG;
import static ua.com.cyberdone.devicemicroservice.util.ValidationConstants.UUID_PATTERN;
import static ua.com.cyberdone.devicemicroservice.util.ValidationConstants.VALUE_IS_BLANK_MSG;

@Validated
@Tag(name = "Delegated Device Control", description = "Endpoints base for delegation device control between users")
public interface DelegatedDeviceControlApi {

    @Operation(summary = "Get Delegated Device Control Devices (in any status) For User By His Token",
            description = "Endpoint extracts username from JWT token, then search all delegated control in DB for that user. In response will be present data in all possible statuses.")
    @ApiResponse(responseCode = "200",
            description = "Endpoint extracts username from JWT token, then search all delegated control in DB for that user. In response will be present data in all possible statuses.",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = PageableDelegatedDeviceControlDto.class)))
    ResponseEntity<PageableDelegatedDeviceControlDto> getDelegatedDeviceControlForUserByToken(
            String token,
            Integer page,
            Integer size,
            String direction,
            String sortBy);

    @Operation(summary = "Get Delegated Device Control For Owner By Device UUID and concrete request status",
            description = "Endpoint extracts user-id from JWT token and if user is owner of this particular device, returns delegated control list of particular delegation status.")
    @ApiResponse(responseCode = "200", description = "Endpoint extracts user-id from JWT token and if user is owner of this particular device, returns delegated control list of particular delegation status.",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = PageableDelegatedDeviceControlDto.class)))
    ResponseEntity<PageableDelegatedDeviceControlDto> getAllDelegatedDeviceControlByDeviceUuidAndOwnerToken(
            String token,
            @Valid
            @NotBlank(message = VALUE_IS_BLANK_MSG)
            @Pattern(regexp = UUID_PATTERN, message = UUID_FAILED_MSG)
                    String deviceUuid,
            DelegationStatus delegationStatus,
            Integer page,
            Integer size,
            String direction,
            String sortBy);

    @Operation(summary = "Get Delegated Device Control For User By Device UUID", description = "Get Delegated Device Control For User By Device UUID")
    @ApiResponse(responseCode = "200", description = "Get Delegated Device Control For User By Device UUID",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = DelegatedDeviceControlDto.class)))
    ResponseEntity<DelegatedDeviceControlDto> getDelegatedDeviceControlForUser(
            String token,
            @Valid
            @NotBlank(message = VALUE_IS_BLANK_MSG)
            @Pattern(regexp = EMAIL_RGX, message = EMAIL_FAIL_MESSAGE)
                    String username,
            @Valid
            @NotBlank(message = VALUE_IS_BLANK_MSG)
            @Pattern(regexp = UUID_PATTERN, message = UUID_FAILED_MSG)
                    String deviceUuid) throws NotFoundException;

    @Operation(summary = "Update Delegated Device Control For Owner",
            description = "Update Delegated Device Control For Owner By Device UUID And Delegated User Username")
    @ApiResponse(responseCode = "200", description = "Update Delegated Device Control For Owner By Device UUID And Delegated User Username",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = String.class, example = ControllerConstantUtils.OK)))
    ResponseEntity<String> updateDelegationStatus(
            String token,
            @Valid
            @NotBlank(message = VALUE_IS_BLANK_MSG)
            @Pattern(regexp = UUID_PATTERN, message = UUID_FAILED_MSG)
                    String deviceUuid,
            @Valid
            @NotBlank(message = VALUE_IS_BLANK_MSG)
            @Pattern(regexp = EMAIL_RGX, message = EMAIL_FAIL_MESSAGE)
                    String username,
            DelegationStatus delegationStatus);

    @Operation(summary = "Create Delegated Device Control Request",
            description = "Create Delegated Device Control Request")
    @ApiResponse(responseCode = "200", description = "Create Delegated Device Control Request",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = String.class, example = ControllerConstantUtils.OK)))
    ResponseEntity<DelegatedDeviceControlDto> createDelegatedDeviceControl(
            String token,
            String comment,
            @Valid
            @NotBlank(message = VALUE_IS_BLANK_MSG)
            @Pattern(regexp = UUID_PATTERN, message = UUID_FAILED_MSG)
                    String deviceUuid) throws IllegalOperationException, AlreadyExistException, NotFoundException;
}

package ua.com.cyberdone.devicemicroservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.com.cyberdone.devicemicroservice.model.dto.DeviceMetadataDto;
import ua.com.cyberdone.devicemicroservice.persistence.entity.DeviceType;
import ua.com.cyberdone.devicemicroservice.persistence.service.DeviceMetadataService;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.util.List;

import static ua.com.cyberdone.devicemicroservice.validation.ValidationConstants.NOT_POSITIVE_MSG;
import static ua.com.cyberdone.devicemicroservice.validation.ValidationConstants.UUID_FAILED_MSG;
import static ua.com.cyberdone.devicemicroservice.validation.ValidationConstants.UUID_PATTERN;
import static ua.com.cyberdone.devicemicroservice.validation.ValidationConstants.VALUE_IS_BLANK_MSG;
import static ua.com.cyberdone.devicemicroservice.validation.ValidationConstants.VALUE_IS_NULL_MSG;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/metadata")
public class MetadataController {
    private final DeviceMetadataService metadataService;

    @GetMapping
    public ResponseEntity<DeviceMetadataDto> getMetadataByUuid(
            @NotBlank(message = VALUE_IS_BLANK_MSG) @Pattern(regexp = UUID_PATTERN, message = UUID_FAILED_MSG)
            @RequestParam String uuid) {
        return ResponseEntity.ok(metadataService.getMetadataByUuid(uuid));
    }

    @GetMapping("/list")
    public ResponseEntity<List<DeviceMetadataDto>> getMetadataByUser(
            @NotNull(message = VALUE_IS_NULL_MSG) @Positive(message = NOT_POSITIVE_MSG)
            @RequestParam Long userId) {
        return ResponseEntity.ok(metadataService.getMetadataByUser(userId));
    }

    @PatchMapping
    public ResponseEntity<String> updateMetadata(
            @NotBlank(message = VALUE_IS_BLANK_MSG) @Pattern(regexp = UUID_PATTERN, message = UUID_FAILED_MSG)
            @RequestParam String uuid,
            @NotBlank(message = VALUE_IS_BLANK_MSG)
            @RequestParam String name,
            @NotBlank(message = VALUE_IS_BLANK_MSG)
            @RequestParam String description) {
        log.info("{} {} {}", uuid, name, description);
        metadataService.updateMetadata(uuid, name, description);
        return ResponseEntity.ok("OK");
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('w_all','w_device_metadata')")
    public ResponseEntity<String> createMetadata(@RequestBody @Valid DeviceMetadataDto metadataDto) {
        log.info("Creating Device with Metadata: {}", metadataDto);
        metadataService.saveMetadata(metadataDto);
        return ResponseEntity.ok("OK");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteMetadata(
            @NotBlank(message = VALUE_IS_BLANK_MSG) @Pattern(regexp = UUID_PATTERN, message = UUID_FAILED_MSG)
            @RequestParam String uuid) {
        metadataService.deleteMetadata(uuid);
        return ResponseEntity.ok("OK");
    }

    @GetMapping("/device-types")
    public ResponseEntity<DeviceType[]> getDeviceTypesList() {
        return ResponseEntity.ok(DeviceType.values());
    }

    @PutMapping("/unlink")
    public ResponseEntity<String> getDeviceTypesList(
            @NotBlank(message = VALUE_IS_BLANK_MSG) @Pattern(regexp = UUID_PATTERN, message = UUID_FAILED_MSG)
            @RequestParam String uuid) {
        metadataService.unlinkMetadataFromUser(uuid);
        return ResponseEntity.ok("OK");
    }

    @PutMapping("/link")
    public ResponseEntity<String> getDeviceTypesList(
            @NotBlank(message = VALUE_IS_BLANK_MSG) @Pattern(regexp = UUID_PATTERN, message = UUID_FAILED_MSG)
            @RequestParam String uuid,
            @NotNull(message = VALUE_IS_NULL_MSG) @Positive(message = NOT_POSITIVE_MSG)
            @RequestParam Long userId) {
        metadataService.linkMetadataToUser(uuid, userId);
        return ResponseEntity.ok("OK");
    }
}

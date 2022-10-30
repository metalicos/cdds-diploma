package ua.com.cyberdone.devicemicroservice.controller.device;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.com.cyberdone.devicemicroservice.device.common.entity.DeviceMetadata;
import ua.com.cyberdone.devicemicroservice.device.common.entity.DeviceType;
import ua.com.cyberdone.devicemicroservice.device.common.exception.AlreadyExistException;
import ua.com.cyberdone.devicemicroservice.device.common.exception.NotFoundException;
import ua.com.cyberdone.devicemicroservice.device.common.model.UiDeviceMetadata;
import ua.com.cyberdone.devicemicroservice.device.common.service.DeviceMetadataService;
import ua.com.cyberdone.devicemicroservice.device.common.service.DeviceTypeService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequiredArgsConstructor
@RequestMapping("/device/metadata")
public class DeviceMetadataController {
    private final DeviceMetadataService deviceMetadataService;
    private final DeviceTypeService deviceTypeService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('r_all','r_device_metadata')")
    public ResponseEntity<UiDeviceMetadata> getMetadataByUuid(@RequestHeader(AUTHORIZATION) String token,
                                                              @RequestParam String uuid) throws NotFoundException {
        return ResponseEntity.ok(deviceMetadataService.findMetadataByUuid(uuid));
    }

    @GetMapping("/list")
    @PreAuthorize("hasAnyAuthority('r_all','r_all_user_device_metadata')")
    public ResponseEntity<List<UiDeviceMetadata>> getMetadataByUser(@RequestHeader(AUTHORIZATION) String token,
                                                                    @RequestParam Long userId) {
        return ResponseEntity.ok(deviceMetadataService.findAll(userId));
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('u_all','u_device_metadata')")
    public ResponseEntity<DeviceMetadata> updateMetadata(@RequestHeader(AUTHORIZATION) String token,
                                                         @RequestParam String uuid,
                                                         @RequestParam String name,
                                                         @RequestParam String description) {
        return ResponseEntity.ok(deviceMetadataService.update(DeviceMetadata.builder()
                .uuid(uuid).name(name).description(description).build()));
    }

    @PutMapping("/{uuid}/image")
    @PreAuthorize("hasAnyAuthority('u_all','u_device_metadata')")
    public ResponseEntity<DeviceMetadata> updateDeviceImage(@RequestHeader(AUTHORIZATION) String token,
                                                            @PathVariable String uuid,
                                                            @RequestPart("file") MultipartFile deviceImage) throws IOException {
        return ResponseEntity.ok(deviceMetadataService.update(DeviceMetadata.builder()
                .uuid(uuid).logo(deviceImage.getBytes()).build()));
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('w_all','w_device_metadata')")
    public ResponseEntity<DeviceMetadata> createMetadata(@RequestHeader(AUTHORIZATION) String token,
                                                         @RequestBody DeviceMetadata metadata) throws AlreadyExistException {
        metadata.setCreatedTimestamp(LocalDateTime.now());
        return ResponseEntity.ok(deviceMetadataService.save(metadata));
    }

    @DeleteMapping
    @PreAuthorize("hasAnyAuthority('d_all','d_device_metadata')")
    public ResponseEntity<String> deleteMetadata(@RequestHeader(AUTHORIZATION) String token,
                                                 @RequestParam String uuid) {
        deviceMetadataService.deleteByUuid(uuid);
        return ResponseEntity.ok("OK");
    }

    @GetMapping("/device-types")
    @PreAuthorize("hasAnyAuthority('r_all','r_device_types')")
    public ResponseEntity<List<String>> getDeviceTypesList(@RequestHeader(AUTHORIZATION) String token) {
        return ResponseEntity.ok(deviceTypeService.find().stream().map(DeviceType::getType).collect(Collectors.toList()));
    }

    @PutMapping("/unlink")
    @PreAuthorize("hasAnyAuthority('u_all','u_device_metadata_unlink')")
    public ResponseEntity<String> unlinkMetadataFromUser(@RequestHeader(AUTHORIZATION) String token,
                                                         @RequestParam String uuid) {
        deviceMetadataService.unlinkWithOwnerDeviceMetadata(uuid);
        return ResponseEntity.ok("OK");
    }

    @PutMapping("/link")
    @PreAuthorize("hasAnyAuthority('u_all','u_device_metadata_link')")
    public ResponseEntity<String> linkMetadataToUser(@RequestHeader(AUTHORIZATION) String token,
                                                     @RequestParam String uuid,
                                                     @RequestParam Long userId) {
        deviceMetadataService.linkWithOwnerDeviceMetadata(uuid, userId);
        return ResponseEntity.ok("OK");
    }
}

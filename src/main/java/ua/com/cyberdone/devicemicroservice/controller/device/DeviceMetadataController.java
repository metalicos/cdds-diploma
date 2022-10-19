package ua.com.cyberdone.devicemicroservice.controller.device;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.com.cyberdone.devicemicroservice.device.common.entity.DeviceMetadata;
import ua.com.cyberdone.devicemicroservice.device.common.exception.AlreadyExistException;
import ua.com.cyberdone.devicemicroservice.device.common.exception.NotFoundException;
import ua.com.cyberdone.devicemicroservice.device.common.service.DeviceMetadataService;
import ua.com.cyberdone.devicemicroservice.device.common.service.DeviceTypeService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequiredArgsConstructor
@RequestMapping("/device/metadata")
public class DeviceMetadataController {
    private final DeviceMetadataService deviceMetadataService;
    private final DeviceTypeService deviceTypeService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('r_all','r_device_metadata')")
    public ResponseEntity<DeviceMetadata> getMetadataByUuid(@RequestHeader(AUTHORIZATION) String token,
                                                            @RequestParam String uuid) throws NotFoundException {
        return ResponseEntity.ok(deviceMetadataService.find(uuid));
    }

    @GetMapping("/list")
    @PreAuthorize("hasAnyAuthority('r_all','r_all_user_device_metadata')")
    public ResponseEntity<List<DeviceMetadata>> getMetadataByUser(@RequestHeader(AUTHORIZATION) String token,
                                                                  @RequestParam Long userId) {
        return ResponseEntity.ok(deviceMetadataService.findAll(userId));
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('u_all','u_device_metadata')")
    public ResponseEntity<DeviceMetadata> updateMetadata(@RequestHeader(AUTHORIZATION) String token,
                                                         @RequestParam String uuid,
                                                         @RequestParam String name,
                                                         @RequestParam String description)
            throws IOException, NotFoundException {
        return ResponseEntity.ok(deviceMetadataService.update(uuid, name, description));
    }

    @PutMapping("/{uuid}/image")
    @PreAuthorize("hasAnyAuthority('u_all','u_device_metadata')")
    public ResponseEntity<DeviceMetadata> updateDeviceImage(@RequestHeader(AUTHORIZATION) String token,
                                                            @PathVariable String uuid,
                                                            @RequestPart("file") MultipartFile deviceImage)
            throws IOException, NotFoundException, SQLException {
        return ResponseEntity.ok(deviceMetadataService.update(uuid, deviceImage));
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('w_all','w_device_metadata')")
    public ResponseEntity<DeviceMetadata> createMetadata(@RequestHeader(AUTHORIZATION) String token,
                                                         @RequestBody DeviceMetadata metadataDto) throws AlreadyExistException {
        return ResponseEntity.ok(deviceMetadataService.saveMetadata(metadataDto));
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
    public ResponseEntity<List<String>> getDeviceTypesList(@RequestHeader(AUTHORIZATION) String token) throws NotFoundException {
        return ResponseEntity.ok(deviceTypeService.findAll());
    }

    @PutMapping("/unlink")
    @PreAuthorize("hasAnyAuthority('u_all','u_device_metadata_unlink')")
    public ResponseEntity<String> unlinkMetadataFromUser(@RequestHeader(AUTHORIZATION) String token,
                                                         @RequestParam String uuid) throws AlreadyExistException, NotFoundException {
        deviceMetadataService.unlinkWithOwnerDeviceMetadata(uuid);
        return ResponseEntity.ok("OK");
    }

    @PutMapping("/link")
    @PreAuthorize("hasAnyAuthority('u_all','u_device_metadata_link')")
    public ResponseEntity<String> linkMetadataToUser(@RequestHeader(AUTHORIZATION) String token,
                                                     @RequestParam String uuid,
                                                     @RequestParam Long userId) throws AlreadyExistException, NotFoundException {
        deviceMetadataService.linkWithOwnerDeviceMetadata(uuid, userId);
        return ResponseEntity.ok("OK");
    }
}

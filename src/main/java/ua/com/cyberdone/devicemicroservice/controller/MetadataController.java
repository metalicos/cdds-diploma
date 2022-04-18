package ua.com.cyberdone.devicemicroservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ua.com.cyberdone.devicemicroservice.controller.docs.DeviceMetadataApi;
import ua.com.cyberdone.devicemicroservice.exception.AlreadyExistException;
import ua.com.cyberdone.devicemicroservice.exception.NotFoundException;
import ua.com.cyberdone.devicemicroservice.persistence.entity.DeviceType;
import ua.com.cyberdone.devicemicroservice.persistence.model.DeviceMetadataDto;
import ua.com.cyberdone.devicemicroservice.persistence.model.SaveDeviceMetadataDto;
import ua.com.cyberdone.devicemicroservice.persistence.service.DeviceMetadataService;

import java.io.IOException;
import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequiredArgsConstructor
@RequestMapping("/device/metadata")
public class MetadataController implements DeviceMetadataApi {
    private final DeviceMetadataService metadataService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('r_all','r_device_metadata')")
    public ResponseEntity<DeviceMetadataDto> getMetadataByUuid(@RequestHeader(AUTHORIZATION) String token,
                                                               @RequestParam String uuid) throws NotFoundException {
        return ResponseEntity.ok(metadataService.getMetadataByUuid(uuid));
    }

    @GetMapping("/list")
    @PreAuthorize("hasAnyAuthority('r_all','r_all_user_device_metadata')")
    public ResponseEntity<List<DeviceMetadataDto>> getMetadataByUser(@RequestHeader(AUTHORIZATION) String token,
                                                                     @RequestParam Long userId) {
        return ResponseEntity.ok(metadataService.getMetadataByUser(userId));
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('u_all','u_device_metadata')")
    public ResponseEntity<DeviceMetadataDto> updateMetadata(@RequestHeader(AUTHORIZATION) String token,
                                                 @RequestParam String uuid,
                                                 @RequestParam String name,
                                                 @RequestParam String description)
            throws IOException, NotFoundException {
        return ResponseEntity.ok(metadataService.updateMetadata(uuid, name, description));
    }

    @PutMapping("/{uuid}/image")
    @PreAuthorize("hasAnyAuthority('u_all','u_device_metadata')")
    public ResponseEntity<DeviceMetadataDto> updateDeviceImage(@RequestHeader(AUTHORIZATION) String token,
                                                               @PathVariable String uuid,
                                                               @RequestPart("file") MultipartFile deviceImage)
            throws IOException, NotFoundException {
        return ResponseEntity.ok(metadataService.updateDeviceImage(uuid, deviceImage));
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('w_all','w_device_metadata')")
    public ResponseEntity<DeviceMetadataDto> createMetadata(@RequestHeader(AUTHORIZATION) String token,
                                                            @RequestBody SaveDeviceMetadataDto metadataDto) throws AlreadyExistException {
        return ResponseEntity.ok(metadataService.saveMetadata(metadataDto));
    }

    @DeleteMapping
    @PreAuthorize("hasAnyAuthority('d_all','d_device_metadata')")
    public ResponseEntity<String> deleteMetadata(@RequestHeader(AUTHORIZATION) String token,
                                                 @RequestParam String uuid) {
        metadataService.deleteMetadata(uuid);
        return ResponseEntity.ok("OK");
    }

    @GetMapping("/device-types")
    @PreAuthorize("hasAnyAuthority('r_all','r_device_types')")
    public ResponseEntity<DeviceType[]> getDeviceTypesList(@RequestHeader(AUTHORIZATION) String token) {
        return ResponseEntity.ok(DeviceType.values());
    }

    @PutMapping("/unlink")
    @PreAuthorize("hasAnyAuthority('u_all','u_device_metadata_unlink')")
    public ResponseEntity<String> unlinkMetadataFromUser(@RequestHeader(AUTHORIZATION) String token,
                                                         @RequestParam String uuid) throws AlreadyExistException, NotFoundException {
        metadataService.unlinkMetadataFromUser(uuid);
        return ResponseEntity.ok("OK");
    }

    @PutMapping("/link")
    @PreAuthorize("hasAnyAuthority('u_all','u_device_metadata_link')")
    public ResponseEntity<String> linkMetadataToUser(@RequestHeader(AUTHORIZATION) String token,
                                                     @RequestParam String uuid,
                                                     @RequestParam Long userId) throws AlreadyExistException, NotFoundException {
        metadataService.linkMetadataToUser(uuid, userId);
        return ResponseEntity.ok("OK");
    }
}

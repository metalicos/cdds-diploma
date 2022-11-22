package ua.com.cyberdone.devicemicroservice.device.rest.device;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.cyberdone.devicemicroservice.device.model.DeviceDTO;
import ua.com.cyberdone.devicemicroservice.device.model.DeviceType;
import ua.com.cyberdone.devicemicroservice.device.service.DeviceService;
import ua.com.cyberdone.devicemicroservice.security.JwtService;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;


@RestController
@RequiredArgsConstructor
@RequestMapping(name = "/devices", produces = MediaType.APPLICATION_JSON_VALUE)
public class DeviceController {
    private final DeviceService deviceService;
    private final JwtService jwtService;

    @GetMapping("/{uuid}")
    public ResponseEntity<DeviceDTO> getMetadataByUuid(@RequestHeader(AUTHORIZATION) String token,
                                                       @PathVariable String uuid) {
        return ResponseEntity.ok(deviceService.findByUuid(uuid));
    }

    @GetMapping("/my")
    public ResponseEntity<Page<DeviceDTO>> getMyDevices(@RequestHeader(AUTHORIZATION) String token) {
        return jwtService.getUserId(token)
                .map(userId -> ResponseEntity.ok(deviceService.findByOwnerId(userId)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<DeviceDTO> updateMetadata(@RequestHeader(AUTHORIZATION) String token,
                                                    @PathVariable String uuid,
                                                    @RequestBody @Valid DeviceDTO deviceDTO) {
        return ResponseEntity.ok(deviceService.update(uuid, deviceDTO));
    }

    @PostMapping
    public ResponseEntity<DeviceDTO> createDevice(@RequestHeader(AUTHORIZATION) String token,
                                                  @RequestBody @Valid DeviceDTO deviceDTO) {
        return ResponseEntity.ok(deviceService.create(deviceDTO));
    }

    @GetMapping("/types")
    public ResponseEntity<List<String>> getDeviceTypesList(@RequestHeader(AUTHORIZATION) String token) {
        return ResponseEntity.ok(Arrays.stream(DeviceType.values()).map(DeviceType::name).collect(Collectors.toList()));
    }

    @PutMapping("/{uuid}/unlink")
    public ResponseEntity<String> unlinkMetadataFromUser(@RequestHeader(AUTHORIZATION) String token,
                                                         @PathVariable String uuid) {
        jwtService.getUserId(token)
                .ifPresent(userId -> deviceService.unlinkOwner(uuid, userId));
        return ResponseEntity.ok("OK");
    }

    @PutMapping("/{uuid}/link")
    public ResponseEntity<String> linkMetadataToUser(@RequestHeader(AUTHORIZATION) String token,
                                                     @PathVariable String uuid) {
        jwtService.getUserId(token)
                .ifPresent(userId -> deviceService.linkOwner(uuid, userId));
        return ResponseEntity.ok("OK");
    }

    @GetMapping("/page/{page}/size/{size}")
    public ResponseEntity<Page<DeviceDTO>> getAllDevices(@RequestHeader(AUTHORIZATION) String token,
                                                         @PathVariable Integer page,
                                                         @PathVariable Integer size) {
        return ResponseEntity.ok(deviceService.findAll(PageRequest.of(page, size)));
    }
}

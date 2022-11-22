package ua.com.cyberdone.devicemicroservice.device.rest.device;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.cyberdone.devicemicroservice.device.model.DeviceDetailsDTO;
import ua.com.cyberdone.devicemicroservice.device.service.DeviceDetailsService;

import javax.validation.Valid;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/devices/details", produces = MediaType.APPLICATION_JSON_VALUE)
public class DeviceDetailsController {
    private final DeviceDetailsService deviceDetailsService;

    @GetMapping("/byDevice/{uuid}")
    public ResponseEntity<DeviceDetailsDTO> getDelegateSecretsByDevice(
            @RequestHeader(AUTHORIZATION) String token,
            @PathVariable String uuid) {
        return ResponseEntity.ok(deviceDetailsService.findByDeviceUuid(uuid));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeviceDetailsDTO> getDeviceDetails(@PathVariable final Long id) {
        return ResponseEntity.ok(deviceDetailsService.get(id));
    }

    @PostMapping
    public ResponseEntity<DeviceDetailsDTO> createDeviceDetails(
            @RequestBody @Valid final DeviceDetailsDTO deviceDetailsDTO) {
        return new ResponseEntity<>(deviceDetailsService.create(deviceDetailsDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DeviceDetailsDTO> updateDeviceDetails(@PathVariable final Long id,
                                                                @RequestBody @Valid final DeviceDetailsDTO deviceDetailsDTO) {
        return ResponseEntity.ok(deviceDetailsService.update(id, deviceDetailsDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDeviceDetails(@PathVariable final Long id) {
        deviceDetailsService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

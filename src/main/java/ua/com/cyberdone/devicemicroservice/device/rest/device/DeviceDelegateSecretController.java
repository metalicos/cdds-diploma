package ua.com.cyberdone.devicemicroservice.device.rest.device;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.cyberdone.devicemicroservice.device.model.DeviceDelegateSecretDTO;
import ua.com.cyberdone.devicemicroservice.device.service.DeviceDelegateSecretService;

import javax.validation.Valid;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/devices/delegateSecrets", produces = MediaType.APPLICATION_JSON_VALUE)
public class DeviceDelegateSecretController {
    private final DeviceDelegateSecretService deviceDelegateSecretService;

    @GetMapping("/byDevice/{uuid}")
    public ResponseEntity<Page<DeviceDelegateSecretDTO>> getDelegateSecretsByDevice(
            @RequestHeader(AUTHORIZATION) String token,
            @PathVariable String uuid) {
        return ResponseEntity.ok(deviceDelegateSecretService.findAllByDeviceUuid(uuid));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeviceDelegateSecretDTO> getDeviceDelegateSecret(
            @RequestHeader(AUTHORIZATION) String token,
            @PathVariable final Long id) {
        return ResponseEntity.ok(deviceDelegateSecretService.get(id));
    }

    @PostMapping
    public ResponseEntity<DeviceDelegateSecretDTO> createDeviceDelegateSecret(
            @RequestHeader(AUTHORIZATION) String token,
            @RequestBody @Valid final DeviceDelegateSecretDTO deviceDelegateSecretDTO) {
        return new ResponseEntity<>(deviceDelegateSecretService.create(deviceDelegateSecretDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DeviceDelegateSecretDTO> updateDeviceDelegateSecret(
            @RequestHeader(AUTHORIZATION) String token,
            @PathVariable final Long id,
            @RequestBody @Valid final DeviceDelegateSecretDTO deviceDelegateSecretDTO) {
        return ResponseEntity.ok(deviceDelegateSecretService.update(id, deviceDelegateSecretDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDeviceDelegateSecret(
            @RequestHeader(AUTHORIZATION) String token,
            @PathVariable final Long id) {
        deviceDelegateSecretService.delete(id);
        return ResponseEntity.noContent().build();
    }

}

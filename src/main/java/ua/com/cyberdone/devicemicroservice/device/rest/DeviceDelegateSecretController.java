package ua.com.cyberdone.devicemicroservice.device.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.cyberdone.devicemicroservice.device.model.DeviceDelegateSecretDTO;
import ua.com.cyberdone.devicemicroservice.device.service.DeviceDelegateSecretService;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/devices/delegateSecrets", produces = MediaType.APPLICATION_JSON_VALUE)
public class DeviceDelegateSecretController {
    private final DeviceDelegateSecretService deviceDelegateSecretService;


    @GetMapping
    public ResponseEntity<List<DeviceDelegateSecretDTO>> getAllDeviceDelegateSecrets() {
        return ResponseEntity.ok(deviceDelegateSecretService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeviceDelegateSecretDTO> getDeviceDelegateSecret(
            @PathVariable final Long id) {
        return ResponseEntity.ok(deviceDelegateSecretService.get(id));
    }

    @PostMapping
    public ResponseEntity<Long> createDeviceDelegateSecret(
            @RequestBody @Valid final DeviceDelegateSecretDTO deviceDelegateSecretDTO) {
        return new ResponseEntity<>(deviceDelegateSecretService.create(deviceDelegateSecretDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateDeviceDelegateSecret(@PathVariable final Long id,
                                                           @RequestBody @Valid final DeviceDelegateSecretDTO deviceDelegateSecretDTO) {
        deviceDelegateSecretService.update(id, deviceDelegateSecretDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDeviceDelegateSecret(@PathVariable final Long id) {
        deviceDelegateSecretService.delete(id);
        return ResponseEntity.noContent().build();
    }

}

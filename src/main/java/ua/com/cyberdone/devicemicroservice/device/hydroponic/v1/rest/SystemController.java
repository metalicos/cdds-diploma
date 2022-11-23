package ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.model.SystemDTO;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.service.SystemService;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/device/hydroponic/v1/systems", produces = MediaType.APPLICATION_JSON_VALUE)
public class SystemController {
    private final SystemService systemService;

    @GetMapping("/byDevice/{uuid}")
    public ResponseEntity<SystemDTO> getSystemServiceByDeviceUuid(@PathVariable String uuid) {
        return ResponseEntity.ok(systemService.findByDeviceUuid(uuid));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SystemDTO> getSystemService(@PathVariable final Long id) {
        return ResponseEntity.ok(systemService.get(id));
    }

    @PostMapping
    public ResponseEntity<SystemDTO> createSystemService(
            @RequestBody @Valid final SystemDTO systemDTO) {
        return new ResponseEntity<>(systemService.create(systemDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SystemDTO> updateSystemService(@PathVariable final Long id,
                                                         @RequestBody @Valid final SystemDTO systemDTO) {
        return ResponseEntity.ok(systemService.update(id, systemDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSystemService(@PathVariable final Long id) {
        systemService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

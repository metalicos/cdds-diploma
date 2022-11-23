package ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.model.PhSensorDTO;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.service.PhSensorService;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/device/hydroponic/v1/phSensors", produces = MediaType.APPLICATION_JSON_VALUE)
public class PhSensorController {
    private final PhSensorService phSensorService;

    @GetMapping("/byDevice/{uuid}")
    public ResponseEntity<PhSensorDTO> getPhSensorByDeviceUuid(@PathVariable String uuid) {
        return ResponseEntity.ok(phSensorService.findByDeviceUuid(uuid));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PhSensorDTO> getPhSensor(@PathVariable final Long id) {
        return ResponseEntity.ok(phSensorService.get(id));
    }

    @PostMapping
    public ResponseEntity<PhSensorDTO> createPhSensor(
            @RequestBody @Valid final PhSensorDTO PhSensorDTO) {
        return new ResponseEntity<>(phSensorService.create(PhSensorDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PhSensorDTO> updatePhSensor(@PathVariable final Long id,
                                                      @RequestBody @Valid final PhSensorDTO PhSensorDTO) {
        return ResponseEntity.ok(phSensorService.update(id, PhSensorDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePhSensor(@PathVariable final Long id) {
        phSensorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

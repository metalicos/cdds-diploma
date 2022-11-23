package ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.rest;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.model.EcSensorDTO;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.service.EcSensorService;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/device/hydroponic/v1/ecSensors", produces = MediaType.APPLICATION_JSON_VALUE)
public class EcSensorController {
    private final EcSensorService ecSensorService;


    @GetMapping("/byDevice/{uuid}")
    public ResponseEntity<EcSensorDTO> getEcSensorByDeviceUuid(@PathVariable String uuid) {
        return ResponseEntity.ok(ecSensorService.findByDeviceUuid(uuid));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EcSensorDTO> getEcSensor(@PathVariable final Long id) {
        return ResponseEntity.ok(ecSensorService.get(id));
    }

    @PostMapping
    public ResponseEntity<EcSensorDTO> createEcSensor(
            @RequestBody @Valid final EcSensorDTO ecSensorDTO) {
        return new ResponseEntity<>(ecSensorService.create(ecSensorDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EcSensorDTO> updateEcSensor(@PathVariable final Long id,
                                                      @RequestBody @Valid final EcSensorDTO EcSensorDTO) {
        return ResponseEntity.ok(ecSensorService.update(id, EcSensorDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEcSensor(@PathVariable final Long id) {
        ecSensorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

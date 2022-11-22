package ua.com.cyberdone.devicemicroservice.device.rest;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/ecSensors", produces = MediaType.APPLICATION_JSON_VALUE)
public class EcSensorResource {

    private final EcSensorService ecSensorService;

    public EcSensorResource(final EcSensorService ecSensorService) {
        this.ecSensorService = ecSensorService;
    }

    @GetMapping
    public ResponseEntity<List<EcSensorDTO>> getAllEcSensors() {
        return ResponseEntity.ok(ecSensorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EcSensorDTO> getEcSensor(@PathVariable final Long id) {
        return ResponseEntity.ok(ecSensorService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createEcSensor(@RequestBody @Valid final EcSensorDTO ecSensorDTO) {
        return new ResponseEntity<>(ecSensorService.create(ecSensorDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateEcSensor(@PathVariable final Long id,
                                               @RequestBody @Valid final EcSensorDTO ecSensorDTO) {
        ecSensorService.update(id, ecSensorDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteEcSensor(@PathVariable final Long id) {
        ecSensorService.delete(id);
        return ResponseEntity.noContent().build();
    }

}

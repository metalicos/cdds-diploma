package ua.com.cyberdone.devicemicroservice.device.rest;

import io.bootify.my_app.model.PhSensorDTO;
import io.bootify.my_app.service.PhSensorService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(value = "/api/phSensors", produces = MediaType.APPLICATION_JSON_VALUE)
public class PhSensorResource {

    private final PhSensorService phSensorService;

    public PhSensorResource(final PhSensorService phSensorService) {
        this.phSensorService = phSensorService;
    }

    @GetMapping
    public ResponseEntity<List<PhSensorDTO>> getAllPhSensors() {
        return ResponseEntity.ok(phSensorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PhSensorDTO> getPhSensor(@PathVariable final Long id) {
        return ResponseEntity.ok(phSensorService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createPhSensor(@RequestBody @Valid final PhSensorDTO phSensorDTO) {
        return new ResponseEntity<>(phSensorService.create(phSensorDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePhSensor(@PathVariable final Long id,
                                               @RequestBody @Valid final PhSensorDTO phSensorDTO) {
        phSensorService.update(id, phSensorDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deletePhSensor(@PathVariable final Long id) {
        phSensorService.delete(id);
        return ResponseEntity.noContent().build();
    }

}

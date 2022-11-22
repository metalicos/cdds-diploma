package ua.com.cyberdone.devicemicroservice.device.rest;

import io.bootify.my_app.model.DeviceDetailsDTO;
import io.bootify.my_app.service.DeviceDetailsService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(value = "/api/deviceDetailss", produces = MediaType.APPLICATION_JSON_VALUE)
public class DeviceDetailsResource {

    private final DeviceDetailsService deviceDetailsService;

    public DeviceDetailsResource(final DeviceDetailsService deviceDetailsService) {
        this.deviceDetailsService = deviceDetailsService;
    }

    @GetMapping
    public ResponseEntity<List<DeviceDetailsDTO>> getAllDeviceDetailss() {
        return ResponseEntity.ok(deviceDetailsService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeviceDetailsDTO> getDeviceDetails(@PathVariable final Long id) {
        return ResponseEntity.ok(deviceDetailsService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createDeviceDetails(
            @RequestBody @Valid final DeviceDetailsDTO deviceDetailsDTO) {
        return new ResponseEntity<>(deviceDetailsService.create(deviceDetailsDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateDeviceDetails(@PathVariable final Long id,
                                                    @RequestBody @Valid final DeviceDetailsDTO deviceDetailsDTO) {
        deviceDetailsService.update(id, deviceDetailsDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteDeviceDetails(@PathVariable final Long id) {
        deviceDetailsService.delete(id);
        return ResponseEntity.noContent().build();
    }

}

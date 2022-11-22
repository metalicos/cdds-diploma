package ua.com.cyberdone.devicemicroservice.device.rest;

import io.bootify.my_app.model.DispenserSchedulingDTO;
import io.bootify.my_app.service.DispenserSchedulingService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(value = "/api/dispenserSchedulings", produces = MediaType.APPLICATION_JSON_VALUE)
public class DispenserSchedulingResource {

    private final DispenserSchedulingService dispenserSchedulingService;

    public DispenserSchedulingResource(
            final DispenserSchedulingService dispenserSchedulingService) {
        this.dispenserSchedulingService = dispenserSchedulingService;
    }

    @GetMapping
    public ResponseEntity<List<DispenserSchedulingDTO>> getAllDispenserSchedulings() {
        return ResponseEntity.ok(dispenserSchedulingService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DispenserSchedulingDTO> getDispenserScheduling(
            @PathVariable final Long id) {
        return ResponseEntity.ok(dispenserSchedulingService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createDispenserScheduling(
            @RequestBody @Valid final DispenserSchedulingDTO dispenserSchedulingDTO) {
        return new ResponseEntity<>(dispenserSchedulingService.create(dispenserSchedulingDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateDispenserScheduling(@PathVariable final Long id,
                                                          @RequestBody @Valid final DispenserSchedulingDTO dispenserSchedulingDTO) {
        dispenserSchedulingService.update(id, dispenserSchedulingDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteDispenserScheduling(@PathVariable final Long id) {
        dispenserSchedulingService.delete(id);
        return ResponseEntity.noContent().build();
    }

}

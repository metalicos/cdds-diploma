package ua.com.cyberdone.devicemicroservice.device.rest;

import io.bootify.my_app.model.DispenserDTO;
import io.bootify.my_app.service.DispenserService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(value = "/api/dispensers", produces = MediaType.APPLICATION_JSON_VALUE)
public class DispenserResource {

    private final DispenserService dispenserService;

    public DispenserResource(final DispenserService dispenserService) {
        this.dispenserService = dispenserService;
    }

    @GetMapping
    public ResponseEntity<List<DispenserDTO>> getAllDispensers() {
        return ResponseEntity.ok(dispenserService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DispenserDTO> getDispenser(@PathVariable final Long id) {
        return ResponseEntity.ok(dispenserService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createDispenser(
            @RequestBody @Valid final DispenserDTO dispenserDTO) {
        return new ResponseEntity<>(dispenserService.create(dispenserDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateDispenser(@PathVariable final Long id,
                                                @RequestBody @Valid final DispenserDTO dispenserDTO) {
        dispenserService.update(id, dispenserDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteDispenser(@PathVariable final Long id) {
        dispenserService.delete(id);
        return ResponseEntity.noContent().build();
    }

}

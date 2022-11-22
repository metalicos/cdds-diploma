package ua.com.cyberdone.devicemicroservice.device.rest;

import io.bootify.my_app.model.SystemDTO;
import io.bootify.my_app.service.SystemService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(value = "/api/systems", produces = MediaType.APPLICATION_JSON_VALUE)
public class SystemResource {

    private final SystemService systemService;

    public SystemResource(final SystemService systemService) {
        this.systemService = systemService;
    }

    @GetMapping
    public ResponseEntity<List<SystemDTO>> getAllSystems() {
        return ResponseEntity.ok(systemService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SystemDTO> getSystem(@PathVariable final Long id) {
        return ResponseEntity.ok(systemService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createSystem(@RequestBody @Valid final SystemDTO systemDTO) {
        return new ResponseEntity<>(systemService.create(systemDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateSystem(@PathVariable final Long id,
                                             @RequestBody @Valid final SystemDTO systemDTO) {
        systemService.update(id, systemDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteSystem(@PathVariable final Long id) {
        systemService.delete(id);
        return ResponseEntity.noContent().build();
    }

}

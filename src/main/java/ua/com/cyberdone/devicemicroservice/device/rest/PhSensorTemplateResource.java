package ua.com.cyberdone.devicemicroservice.device.rest;

import io.bootify.my_app.model.PhSensorTemplateDTO;
import io.bootify.my_app.service.PhSensorTemplateService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(value = "/api/phSensorTemplates", produces = MediaType.APPLICATION_JSON_VALUE)
public class PhSensorTemplateResource {

    private final PhSensorTemplateService phSensorTemplateService;

    public PhSensorTemplateResource(final PhSensorTemplateService phSensorTemplateService) {
        this.phSensorTemplateService = phSensorTemplateService;
    }

    @GetMapping
    public ResponseEntity<List<PhSensorTemplateDTO>> getAllPhSensorTemplates() {
        return ResponseEntity.ok(phSensorTemplateService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PhSensorTemplateDTO> getPhSensorTemplate(@PathVariable final Long id) {
        return ResponseEntity.ok(phSensorTemplateService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createPhSensorTemplate(
            @RequestBody @Valid final PhSensorTemplateDTO phSensorTemplateDTO) {
        return new ResponseEntity<>(phSensorTemplateService.create(phSensorTemplateDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePhSensorTemplate(@PathVariable final Long id,
                                                       @RequestBody @Valid final PhSensorTemplateDTO phSensorTemplateDTO) {
        phSensorTemplateService.update(id, phSensorTemplateDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deletePhSensorTemplate(@PathVariable final Long id) {
        phSensorTemplateService.delete(id);
        return ResponseEntity.noContent().build();
    }

}

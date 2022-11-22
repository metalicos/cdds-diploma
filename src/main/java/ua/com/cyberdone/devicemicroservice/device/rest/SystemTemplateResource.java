package ua.com.cyberdone.devicemicroservice.device.rest;

import io.bootify.my_app.model.SystemTemplateDTO;
import io.bootify.my_app.service.SystemTemplateService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(value = "/api/systemTemplates", produces = MediaType.APPLICATION_JSON_VALUE)
public class SystemTemplateResource {

    private final SystemTemplateService systemTemplateService;

    public SystemTemplateResource(final SystemTemplateService systemTemplateService) {
        this.systemTemplateService = systemTemplateService;
    }

    @GetMapping
    public ResponseEntity<List<SystemTemplateDTO>> getAllSystemTemplates() {
        return ResponseEntity.ok(systemTemplateService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SystemTemplateDTO> getSystemTemplate(@PathVariable final Long id) {
        return ResponseEntity.ok(systemTemplateService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createSystemTemplate(
            @RequestBody @Valid final SystemTemplateDTO systemTemplateDTO) {
        return new ResponseEntity<>(systemTemplateService.create(systemTemplateDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateSystemTemplate(@PathVariable final Long id,
                                                     @RequestBody @Valid final SystemTemplateDTO systemTemplateDTO) {
        systemTemplateService.update(id, systemTemplateDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteSystemTemplate(@PathVariable final Long id) {
        systemTemplateService.delete(id);
        return ResponseEntity.noContent().build();
    }

}

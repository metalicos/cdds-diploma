package ua.com.cyberdone.devicemicroservice.device.rest;

import io.bootify.my_app.model.EcSensorTemplateDTO;
import io.bootify.my_app.service.EcSensorTemplateService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(value = "/api/ecSensorTemplates", produces = MediaType.APPLICATION_JSON_VALUE)
public class EcSensorTemplateResource {

    private final EcSensorTemplateService ecSensorTemplateService;

    public EcSensorTemplateResource(final EcSensorTemplateService ecSensorTemplateService) {
        this.ecSensorTemplateService = ecSensorTemplateService;
    }

    @GetMapping
    public ResponseEntity<List<EcSensorTemplateDTO>> getAllEcSensorTemplates() {
        return ResponseEntity.ok(ecSensorTemplateService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EcSensorTemplateDTO> getEcSensorTemplate(@PathVariable final Long id) {
        return ResponseEntity.ok(ecSensorTemplateService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createEcSensorTemplate(
            @RequestBody @Valid final EcSensorTemplateDTO ecSensorTemplateDTO) {
        return new ResponseEntity<>(ecSensorTemplateService.create(ecSensorTemplateDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateEcSensorTemplate(@PathVariable final Long id,
                                                       @RequestBody @Valid final EcSensorTemplateDTO ecSensorTemplateDTO) {
        ecSensorTemplateService.update(id, ecSensorTemplateDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteEcSensorTemplate(@PathVariable final Long id) {
        ecSensorTemplateService.delete(id);
        return ResponseEntity.noContent().build();
    }

}

package ua.com.cyberdone.devicemicroservice.device.rest;

import io.bootify.my_app.model.DispenseScheduleTemplateDTO;
import io.bootify.my_app.service.DispenseScheduleTemplateService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(value = "/api/dispenseScheduleTemplates", produces = MediaType.APPLICATION_JSON_VALUE)
public class DispenseScheduleTemplateResource {

    private final DispenseScheduleTemplateService dispenseScheduleTemplateService;

    public DispenseScheduleTemplateResource(
            final DispenseScheduleTemplateService dispenseScheduleTemplateService) {
        this.dispenseScheduleTemplateService = dispenseScheduleTemplateService;
    }

    @GetMapping
    public ResponseEntity<List<DispenseScheduleTemplateDTO>> getAllDispenseScheduleTemplates() {
        return ResponseEntity.ok(dispenseScheduleTemplateService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DispenseScheduleTemplateDTO> getDispenseScheduleTemplate(
            @PathVariable final Long id) {
        return ResponseEntity.ok(dispenseScheduleTemplateService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createDispenseScheduleTemplate(
            @RequestBody @Valid final DispenseScheduleTemplateDTO dispenseScheduleTemplateDTO) {
        return new ResponseEntity<>(dispenseScheduleTemplateService.create(dispenseScheduleTemplateDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateDispenseScheduleTemplate(@PathVariable final Long id,
                                                               @RequestBody @Valid final DispenseScheduleTemplateDTO dispenseScheduleTemplateDTO) {
        dispenseScheduleTemplateService.update(id, dispenseScheduleTemplateDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteDispenseScheduleTemplate(@PathVariable final Long id) {
        dispenseScheduleTemplateService.delete(id);
        return ResponseEntity.noContent().build();
    }

}

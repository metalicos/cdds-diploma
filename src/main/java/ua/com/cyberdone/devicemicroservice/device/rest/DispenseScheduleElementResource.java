package ua.com.cyberdone.devicemicroservice.device.rest;

import io.bootify.my_app.model.DispenseScheduleElementDTO;
import io.bootify.my_app.service.DispenseScheduleElementService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(value = "/api/dispenseScheduleElements", produces = MediaType.APPLICATION_JSON_VALUE)
public class DispenseScheduleElementResource {

    private final DispenseScheduleElementService dispenseScheduleElementService;

    public DispenseScheduleElementResource(
            final DispenseScheduleElementService dispenseScheduleElementService) {
        this.dispenseScheduleElementService = dispenseScheduleElementService;
    }

    @GetMapping
    public ResponseEntity<List<DispenseScheduleElementDTO>> getAllDispenseScheduleElements() {
        return ResponseEntity.ok(dispenseScheduleElementService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DispenseScheduleElementDTO> getDispenseScheduleElement(
            @PathVariable final Long id) {
        return ResponseEntity.ok(dispenseScheduleElementService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createDispenseScheduleElement(
            @RequestBody @Valid final DispenseScheduleElementDTO dispenseScheduleElementDTO) {
        return new ResponseEntity<>(dispenseScheduleElementService.create(dispenseScheduleElementDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateDispenseScheduleElement(@PathVariable final Long id,
                                                              @RequestBody @Valid final DispenseScheduleElementDTO dispenseScheduleElementDTO) {
        dispenseScheduleElementService.update(id, dispenseScheduleElementDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteDispenseScheduleElement(@PathVariable final Long id) {
        dispenseScheduleElementService.delete(id);
        return ResponseEntity.noContent().build();
    }

}

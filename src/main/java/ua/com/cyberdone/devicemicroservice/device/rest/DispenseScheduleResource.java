package ua.com.cyberdone.devicemicroservice.device.rest;

import io.bootify.my_app.model.DispenseScheduleDTO;
import io.bootify.my_app.service.DispenseScheduleService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(value = "/api/dispenseSchedules", produces = MediaType.APPLICATION_JSON_VALUE)
public class DispenseScheduleResource {

    private final DispenseScheduleService dispenseScheduleService;

    public DispenseScheduleResource(final DispenseScheduleService dispenseScheduleService) {
        this.dispenseScheduleService = dispenseScheduleService;
    }

    @GetMapping
    public ResponseEntity<List<DispenseScheduleDTO>> getAllDispenseSchedules() {
        return ResponseEntity.ok(dispenseScheduleService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DispenseScheduleDTO> getDispenseSchedule(@PathVariable final Long id) {
        return ResponseEntity.ok(dispenseScheduleService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createDispenseSchedule(
            @RequestBody @Valid final DispenseScheduleDTO dispenseScheduleDTO) {
        return new ResponseEntity<>(dispenseScheduleService.create(dispenseScheduleDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateDispenseSchedule(@PathVariable final Long id,
                                                       @RequestBody @Valid final DispenseScheduleDTO dispenseScheduleDTO) {
        dispenseScheduleService.update(id, dispenseScheduleDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteDispenseSchedule(@PathVariable final Long id) {
        dispenseScheduleService.delete(id);
        return ResponseEntity.noContent().build();
    }

}

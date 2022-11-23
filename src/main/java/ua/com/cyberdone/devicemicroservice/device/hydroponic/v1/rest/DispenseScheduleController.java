package ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.model.DispenseScheduleDTO;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.service.DispenseScheduleService;

import javax.validation.Valid;

/**
 * API налаштувань розкладу дозування
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/device/hydroponic/v1/dispenseSchedules", produces = MediaType.APPLICATION_JSON_VALUE)
public class DispenseScheduleController {
    private final DispenseScheduleService dispenseScheduleService;


    @GetMapping("/byDevice/{uuid}")
    public ResponseEntity<DispenseScheduleDTO> getDispenseScheduleByDeviceUuid(@PathVariable String uuid) {
        return ResponseEntity.ok(dispenseScheduleService.findByDeviceUuid(uuid));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DispenseScheduleDTO> getDispenseSchedule(@PathVariable final Long id) {
        return ResponseEntity.ok(dispenseScheduleService.get(id));
    }

    @PostMapping
    public ResponseEntity<DispenseScheduleDTO> createDispenseSchedule(
            @RequestBody @Valid final DispenseScheduleDTO dispenseScheduleDTO) {
        return new ResponseEntity<>(dispenseScheduleService.create(dispenseScheduleDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DispenseScheduleDTO> updateDispenseSchedule(@PathVariable final Long id,
                                                                      @RequestBody @Valid final DispenseScheduleDTO dispenseScheduleDTO) {
        return ResponseEntity.ok(dispenseScheduleService.update(id, dispenseScheduleDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDispenseSchedule(@PathVariable final Long id) {
        dispenseScheduleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

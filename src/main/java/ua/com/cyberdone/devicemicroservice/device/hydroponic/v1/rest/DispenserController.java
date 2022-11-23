package ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.model.DispenserDTO;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.service.DispenserService;

import javax.validation.Valid;

/**
 * API налаштувань дозаторів
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/device/hydroponic/v1/dispensers", produces = MediaType.APPLICATION_JSON_VALUE)
public class DispenserController {
    private final DispenserService dispenserService;

    @GetMapping("/byDevice/{uuid}")
    public ResponseEntity<Page<DispenserDTO>> getAllDispensers(@PathVariable String uuid,
                                                               @RequestParam(defaultValue = "0", required = false) Integer page,
                                                               @RequestParam(defaultValue = "20", required = false) Integer size) {
        return ResponseEntity.ok(dispenserService.findAllByDeviceUuid(uuid, PageRequest.of(page, size)));
    }

//    @PostMapping
//    public ResponseEntity<DispenserDTO> createDispenser(
//            @RequestBody @Valid final DispenserDTO dispenserDTO) {
//        return new ResponseEntity<>(dispenserService.create(dispenserDTO), HttpStatus.CREATED);
//    }

    @PutMapping("/{id}")
    public ResponseEntity<DispenserDTO> updateDispenser(@PathVariable final Long id,
                                                        @RequestBody @Valid final DispenserDTO dispenserDTO) {
        return ResponseEntity.ok(dispenserService.update(id, dispenserDTO));
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteDispenser(@PathVariable final Long id) {
//        dispenserService.delete(id);
//        return ResponseEntity.noContent().build();
//    }
}

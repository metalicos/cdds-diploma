package ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.model.SpecialSystemDTO;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.service.SpecialSystemService;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/device/hydroponic/v1/specialSystems", produces = MediaType.APPLICATION_JSON_VALUE)
public class SpecialSystemController {

    private final SpecialSystemService specialSystemService;


    @GetMapping("/byDevice/{uuid}")
    public ResponseEntity<SpecialSystemDTO> getSpecialSystemByDeviceUuid(@PathVariable String uuid) {
        return ResponseEntity.ok(specialSystemService.findByDeviceUuid(uuid));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpecialSystemDTO> getSpecialSystem(@PathVariable final Long id) {
        return ResponseEntity.ok(specialSystemService.get(id));
    }

    @PostMapping
    public ResponseEntity<SpecialSystemDTO> createSpecialSystem(
            @RequestBody @Valid final SpecialSystemDTO SpecialSystemDTO) {
        return new ResponseEntity<>(specialSystemService.create(SpecialSystemDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SpecialSystemDTO> updateSpecialSystem(@PathVariable final Long id,
                                                                @RequestBody @Valid final SpecialSystemDTO SpecialSystemDTO) {
        return ResponseEntity.ok(specialSystemService.update(id, SpecialSystemDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpecialSystem(@PathVariable final Long id) {
        specialSystemService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

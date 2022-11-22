package ua.com.cyberdone.devicemicroservice.device.rest;

import io.bootify.my_app.model.SpecialSystemDTO;
import io.bootify.my_app.service.SpecialSystemService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(value = "/api/specialSystems", produces = MediaType.APPLICATION_JSON_VALUE)
public class SpecialSystemResource {

    private final SpecialSystemService specialSystemService;

    public SpecialSystemResource(final SpecialSystemService specialSystemService) {
        this.specialSystemService = specialSystemService;
    }

    @GetMapping
    public ResponseEntity<List<SpecialSystemDTO>> getAllSpecialSystems() {
        return ResponseEntity.ok(specialSystemService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpecialSystemDTO> getSpecialSystem(@PathVariable final Long id) {
        return ResponseEntity.ok(specialSystemService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createSpecialSystem(
            @RequestBody @Valid final SpecialSystemDTO specialSystemDTO) {
        return new ResponseEntity<>(specialSystemService.create(specialSystemDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateSpecialSystem(@PathVariable final Long id,
                                                    @RequestBody @Valid final SpecialSystemDTO specialSystemDTO) {
        specialSystemService.update(id, specialSystemDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteSpecialSystem(@PathVariable final Long id) {
        specialSystemService.delete(id);
        return ResponseEntity.noContent().build();
    }

}

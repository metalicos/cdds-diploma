package ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.model.PhSensorTemplateDTO;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.service.PhSensorTemplateService;
import ua.com.cyberdone.devicemicroservice.security.JwtService;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/device/hydroponic/v1/phSensorTemplates", produces = MediaType.APPLICATION_JSON_VALUE)
public class PhSensorTemplateController {
    private final PhSensorTemplateService phSensorTemplateService;
    private final JwtService jwtService;

    @GetMapping("/{id}")
    public ResponseEntity<PhSensorTemplateDTO> getEcSensorTemplate(@PathVariable Long id) {
        return ResponseEntity.ok(phSensorTemplateService.get(id));
    }

    @GetMapping("/byOwner/{id}")
    public ResponseEntity<Page<PhSensorTemplateDTO>> getEcSensorTemplateByOwnerId(
            @PathVariable Long id,
            @RequestParam(defaultValue = "0", required = false) Integer page,
            @RequestParam(defaultValue = "20", required = false) Integer size) {
        return ResponseEntity.ok(phSensorTemplateService.getByOwnerId(id, page, size));
    }

    @GetMapping("/lib/cyberdone")
    public ResponseEntity<Page<PhSensorTemplateDTO>> getCyberDoneTemplateLibEcSensorTemplate(
            @RequestParam(defaultValue = "0", required = false) Integer page,
            @RequestParam(defaultValue = "20", required = false) Integer size) {
        return ResponseEntity.ok(phSensorTemplateService.getByOwnerId(1L, page, size));
    }

    @GetMapping("/lib/my")
    public ResponseEntity<Page<PhSensorTemplateDTO>> getMyLibTemplatesEcSensorTemplate(
            @RequestHeader(AUTHORIZATION) String token,
            @RequestParam(defaultValue = "0", required = false) Integer page,
            @RequestParam(defaultValue = "20", required = false) Integer size) {
        return jwtService.getUserId(token)
                .map(userId -> ResponseEntity.ok(phSensorTemplateService.getByOwnerId(userId, page, size)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PhSensorTemplateDTO> createEcSensorTemplate(
            @RequestBody PhSensorTemplateDTO dispenserTemplateDTO) {
        return new ResponseEntity<>(phSensorTemplateService.create(dispenserTemplateDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PhSensorTemplateDTO> updateEcSensorTemplate(@PathVariable Long id,
                                                                      @RequestBody PhSensorTemplateDTO dispenserTemplateDTO) {
        return ResponseEntity.ok(phSensorTemplateService.update(id, dispenserTemplateDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEcSensorTemplate(@PathVariable Long id) {
        phSensorTemplateService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

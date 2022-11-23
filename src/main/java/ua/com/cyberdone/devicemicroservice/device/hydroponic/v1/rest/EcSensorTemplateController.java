package ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.model.EcSensorTemplateDTO;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.service.EcSensorTemplateService;
import ua.com.cyberdone.devicemicroservice.security.JwtService;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/device/hydroponic/v1/ecSensorTemplates", produces = MediaType.APPLICATION_JSON_VALUE)
public class EcSensorTemplateController {
    private final EcSensorTemplateService ecSensorTemplateService;
    private final JwtService jwtService;

    @GetMapping("/{id}")
    public ResponseEntity<EcSensorTemplateDTO> getEcSensorTemplate(@PathVariable Long id) {
        return ResponseEntity.ok(ecSensorTemplateService.get(id));
    }

    @GetMapping("/byOwner/{id}")
    public ResponseEntity<Page<EcSensorTemplateDTO>> getEcSensorTemplateByOwnerId(
            @PathVariable Long id,
            @RequestParam(defaultValue = "0", required = false) Integer page,
            @RequestParam(defaultValue = "20", required = false) Integer size) {
        return ResponseEntity.ok(ecSensorTemplateService.getByOwnerId(id, page, size));
    }

    @GetMapping("/lib/cyberdone")
    public ResponseEntity<Page<EcSensorTemplateDTO>> getCyberDoneTemplateLibEcSensorTemplate(
            @RequestParam(defaultValue = "0", required = false) Integer page,
            @RequestParam(defaultValue = "20", required = false) Integer size) {
        return ResponseEntity.ok(ecSensorTemplateService.getByOwnerId(1L, page, size));
    }

    @GetMapping("/lib/my")
    public ResponseEntity<Page<EcSensorTemplateDTO>> getMyLibTemplatesEcSensorTemplate(
            @RequestHeader(AUTHORIZATION) String token,
            @RequestParam(defaultValue = "0", required = false) Integer page,
            @RequestParam(defaultValue = "20", required = false) Integer size) {
        return jwtService.getUserId(token)
                .map(userId -> ResponseEntity.ok(ecSensorTemplateService.getByOwnerId(userId, page, size)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EcSensorTemplateDTO> createEcSensorTemplate(
            @RequestBody EcSensorTemplateDTO dispenserTemplateDTO) {
        return new ResponseEntity<>(ecSensorTemplateService.create(dispenserTemplateDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EcSensorTemplateDTO> updateEcSensorTemplate(@PathVariable Long id,
                                                                      @RequestBody EcSensorTemplateDTO dispenserTemplateDTO) {
        return ResponseEntity.ok(ecSensorTemplateService.update(id, dispenserTemplateDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEcSensorTemplate(@PathVariable Long id) {
        ecSensorTemplateService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

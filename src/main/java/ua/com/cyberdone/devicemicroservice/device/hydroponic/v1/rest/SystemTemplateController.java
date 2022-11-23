package ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.model.SystemTemplateDTO;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.service.SystemTemplateService;
import ua.com.cyberdone.devicemicroservice.security.JwtService;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/device/hydroponic/v1/systemTemplates", produces = MediaType.APPLICATION_JSON_VALUE)
public class SystemTemplateController {
    private final SystemTemplateService systemTemplateService;
    private final JwtService jwtService;

    @GetMapping("/{id}")
    public ResponseEntity<SystemTemplateDTO> getSystemTemplate(@PathVariable Long id) {
        return ResponseEntity.ok(systemTemplateService.get(id));
    }

    @GetMapping("/byOwner/{id}")
    public ResponseEntity<Page<SystemTemplateDTO>> getSystemTemplateByOwnerId(
            @PathVariable Long id,
            @RequestParam(defaultValue = "0", required = false) Integer page,
            @RequestParam(defaultValue = "20", required = false) Integer size) {
        return ResponseEntity.ok(systemTemplateService.getByOwnerId(id, page, size));
    }

    @GetMapping("/lib/cyberdone")
    public ResponseEntity<Page<SystemTemplateDTO>> getCyberDoneTemplateSystemTemplate(
            @RequestParam(defaultValue = "0", required = false) Integer page,
            @RequestParam(defaultValue = "20", required = false) Integer size) {
        return ResponseEntity.ok(systemTemplateService.getByOwnerId(1L, page, size));
    }

    @GetMapping("/lib/my")
    public ResponseEntity<Page<SystemTemplateDTO>> getMyLibTemplatesSystemTemplate(
            @RequestHeader(AUTHORIZATION) String token,
            @RequestParam(defaultValue = "0", required = false) Integer page,
            @RequestParam(defaultValue = "20", required = false) Integer size) {
        return jwtService.getUserId(token)
                .map(userId -> ResponseEntity.ok(systemTemplateService.getByOwnerId(userId, page, size)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<SystemTemplateDTO> createSystemTemplate(
            @RequestBody SystemTemplateDTO systemTemplateDTO) {
        return new ResponseEntity<>(systemTemplateService.create(systemTemplateDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SystemTemplateDTO> updateSystemTemplate(@PathVariable Long id,
                                                                  @RequestBody SystemTemplateDTO systemTemplateDTO) {
        return ResponseEntity.ok(systemTemplateService.update(id, systemTemplateDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSystemTemplate(@PathVariable Long id) {
        systemTemplateService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

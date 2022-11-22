package ua.com.cyberdone.devicemicroservice.device.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.cyberdone.devicemicroservice.device.model.DispenserTemplateDTO;
import ua.com.cyberdone.devicemicroservice.device.service.DispenserTemplateService;
import ua.com.cyberdone.devicemicroservice.security.JwtService;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/1.0/dispenserTemplates", produces = MediaType.APPLICATION_JSON_VALUE)
public class DispenserTemplateController {
    private final DispenserTemplateService dispenserTemplateService;
    private final JwtService jwtService;


    @GetMapping("/{id}")
    public ResponseEntity<DispenserTemplateDTO> getDispenserTemplate(@PathVariable Long id) {
        return ResponseEntity.ok(dispenserTemplateService.get(id));
    }

    @GetMapping("/byOwner/{id}")
    public ResponseEntity<Page<DispenserTemplateDTO>> getDispenserTemplatesByOwnerId(
            @PathVariable Long id,
            @RequestParam(defaultValue = "0", required = false) Integer page,
            @RequestParam(defaultValue = "20", required = false) Integer size) {
        return ResponseEntity.ok(dispenserTemplateService.getByOwnerId(id, page, size));
    }

    @GetMapping("/lib/cyberdone")
    public ResponseEntity<Page<DispenserTemplateDTO>> getCyberDoneTemplateLibDispenserTemplates(
            @RequestParam(defaultValue = "0", required = false) Integer page,
            @RequestParam(defaultValue = "20", required = false) Integer size) {
        return ResponseEntity.ok(dispenserTemplateService.getByOwnerId(1L, page, size));
    }

    @GetMapping("/lib/my")
    public ResponseEntity<Page<DispenserTemplateDTO>> getCyberDoneTemplateLibDispenserTemplates(
            @RequestHeader(AUTHORIZATION) String token,
            @RequestParam(defaultValue = "0", required = false) Integer page,
            @RequestParam(defaultValue = "20", required = false) Integer size) {
        return jwtService.getUserId(token)
                .map(userId -> ResponseEntity.ok(dispenserTemplateService.getByOwnerId(userId, page, size)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Long> createDispenserTemplate(
            @RequestBody DispenserTemplateDTO dispenserTemplateDTO) {
        return new ResponseEntity<>(dispenserTemplateService.create(dispenserTemplateDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateDispenserTemplate(@PathVariable Long id,
                                                        @RequestBody DispenserTemplateDTO dispenserTemplateDTO) {
        dispenserTemplateService.update(id, dispenserTemplateDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDispenserTemplate(@PathVariable Long id) {
        dispenserTemplateService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
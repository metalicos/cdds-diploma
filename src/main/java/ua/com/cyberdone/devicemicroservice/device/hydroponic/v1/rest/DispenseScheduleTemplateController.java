package ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.model.DispenseScheduleTemplateDTO;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.service.DispenseScheduleTemplateService;
import ua.com.cyberdone.devicemicroservice.security.JwtService;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

/**
 * API налаштувань шаблонів розкладу дозування
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/device/hydroponic/v1/dispenseScheduleTemplates", produces = MediaType.APPLICATION_JSON_VALUE)
public class DispenseScheduleTemplateController {
    private final DispenseScheduleTemplateService dispenseScheduleTemplateService;
    private final JwtService jwtService;

    @GetMapping("/{id}")
    public ResponseEntity<DispenseScheduleTemplateDTO> getDispenseScheduleTemplate(@PathVariable Long id) {
        return ResponseEntity.ok(dispenseScheduleTemplateService.get(id));
    }

    @GetMapping("/byOwner/{id}")
    public ResponseEntity<Page<DispenseScheduleTemplateDTO>> getDispenseScheduleTemplateByOwnerId(
            @PathVariable Long id,
            @RequestParam(defaultValue = "0", required = false) Integer page,
            @RequestParam(defaultValue = "20", required = false) Integer size) {
        return ResponseEntity.ok(dispenseScheduleTemplateService.getByOwnerId(id, page, size));
    }

    @GetMapping("/lib/cyberdone")
    public ResponseEntity<Page<DispenseScheduleTemplateDTO>> getCyberDoneTemplateLibDispenseScheduleTemplates(
            @RequestParam(defaultValue = "0", required = false) Integer page,
            @RequestParam(defaultValue = "20", required = false) Integer size) {
        return ResponseEntity.ok(dispenseScheduleTemplateService.getByOwnerId(1L, page, size));
    }

    @GetMapping("/lib/my")
    public ResponseEntity<Page<DispenseScheduleTemplateDTO>> getCyberDoneTemplateLibDispenseScheduleTemplates(
            @RequestHeader(AUTHORIZATION) String token,
            @RequestParam(defaultValue = "0", required = false) Integer page,
            @RequestParam(defaultValue = "20", required = false) Integer size) {
        return jwtService.getUserId(token)
                .map(userId -> ResponseEntity.ok(dispenseScheduleTemplateService.getByOwnerId(userId, page, size)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<DispenseScheduleTemplateDTO> createDispenseScheduleTemplate(
            @RequestBody DispenseScheduleTemplateDTO dispenserTemplateDTO) {
        return new ResponseEntity<>(dispenseScheduleTemplateService.create(dispenserTemplateDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DispenseScheduleTemplateDTO> updateDispenseScheduleTemplate(@PathVariable Long id,
                                                                                      @RequestBody DispenseScheduleTemplateDTO dispenserTemplateDTO) {
        return ResponseEntity.ok(dispenseScheduleTemplateService.update(id, dispenserTemplateDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDispenseScheduleTemplate(@PathVariable Long id) {
        dispenseScheduleTemplateService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

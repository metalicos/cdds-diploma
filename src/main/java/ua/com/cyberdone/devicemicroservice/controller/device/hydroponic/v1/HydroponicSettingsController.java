package ua.com.cyberdone.devicemicroservice.controller.device.hydroponic.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.com.cyberdone.devicemicroservice.data.entity.hydroponic.v1.HydroponicSettings;
import ua.com.cyberdone.devicemicroservice.exception.NotFoundException;
import ua.com.cyberdone.devicemicroservice.service.hydroponic.v1.HydroponicSettingsService;

import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequiredArgsConstructor
@RequestMapping("/device/hydroponic/v1/settings")
public class HydroponicSettingsController {
    private final HydroponicSettingsService hydroponicSettingsService;

    @GetMapping("/{type}")
    @PreAuthorize("hasAnyAuthority('r_all','r_all_hydroponic_setting')")
    public ResponseEntity<List<HydroponicSettings>> getSettings(@RequestHeader(AUTHORIZATION) String token,
                                                                @RequestParam String uuid,
                                                                @RequestParam Long page,
                                                                @RequestParam Long limit,
                                                                @PathVariable String type) throws NotFoundException {
        return ResponseEntity.ok(hydroponicSettingsService.findAll(uuid, page, limit, type));
    }

    @PatchMapping("/{type}")
    @PreAuthorize("hasAnyAuthority('r_all','r_all_hydroponic_setting')")
    public ResponseEntity<String> updateTemplate(@RequestHeader(AUTHORIZATION) String token,
                                                 @RequestParam String uuid,
                                                 @RequestParam(required = false) String name,
                                                 @RequestParam(required = false) String description,
                                                 @RequestBody Object settings,
                                                 @PathVariable String type) {
        hydroponicSettingsService.update(uuid, type, settings, name, description);
        return ResponseEntity.ok("OK");
    }

    @PostMapping("/{type}")
    @PreAuthorize("hasAnyAuthority('r_all','r_all_hydroponic_setting')")
    public ResponseEntity<String> createTemplate(@RequestHeader(AUTHORIZATION) String token,
                                                 @RequestParam String uuid,
                                                 @RequestParam String name,
                                                 @RequestParam String description,
                                                 @RequestBody Object settings,
                                                 @PathVariable String type) {
        hydroponicSettingsService.save(uuid, type, settings, name, description);
        return ResponseEntity.ok("OK");
    }
}

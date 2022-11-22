package ua.com.cyberdone.devicemicroservice.controller.device.hydroponic.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/device/hydroponic/v1/settings")
public class HydroponicSettingsController {
//    private final HydroponicSettingsService hydroponicSettingsService;
//
//    @GetMapping("/{type}")
//    @PreAuthorize("hasAnyAuthority('r_all','r_all_hydroponic_setting')")
//    public ResponseEntity<List<HydroponicSettings>> getSettings(@RequestHeader(AUTHORIZATION) String token,
//                                                                @RequestParam String uuid,
//                                                                @RequestParam Long page,
//                                                                @RequestParam Long limit,
//                                                                @PathVariable String type) throws NotFoundException {
//        return ResponseEntity.ok(hydroponicSettingsService.findAll(uuid, page, limit, type));
//    }
//
//    @PatchMapping("/{type}")
//    @PreAuthorize("hasAnyAuthority('r_all','r_all_hydroponic_setting')")
//    public ResponseEntity<String> updateTemplate(@RequestHeader(AUTHORIZATION) String token,
//                                                 @RequestBody HydroponicSettings settings) {
//        hydroponicSettingsService.update(settings);
//        return ResponseEntity.ok("OK");
//    }
//
//    @PostMapping("/{type}")
//    @PreAuthorize("hasAnyAuthority('r_all','r_all_hydroponic_setting')")
//    public ResponseEntity<String> createTemplate(@RequestHeader(AUTHORIZATION) String token,
//                                                 @RequestBody HydroponicSettings settings) {
//        hydroponicSettingsService.save(settings);
//        return ResponseEntity.ok("OK");
//    }
}

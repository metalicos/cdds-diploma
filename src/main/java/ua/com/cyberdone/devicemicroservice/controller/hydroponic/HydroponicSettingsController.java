package ua.com.cyberdone.devicemicroservice.controller.hydroponic;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.com.cyberdone.devicemicroservice.controller.docs.HydroponicSettingsApi;
import ua.com.cyberdone.devicemicroservice.model.microcontrollers.hydroponic.HydroponicSettingsDto;
import ua.com.cyberdone.devicemicroservice.persistence.service.HydroponicSettingsService;

import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/hydroponic/settings")
public class HydroponicSettingsController implements HydroponicSettingsApi {
    private final HydroponicSettingsService hydroponicSettingsService;

    @GetMapping("/last")
    @PreAuthorize("hasAnyAuthority('r_all','r_all_hydroponic_setting')")
    public ResponseEntity<List<HydroponicSettingsDto>> getLastSettingsInDeviceWithUuid(@RequestHeader(AUTHORIZATION) String token,
                                                                                       @RequestParam String uuid,
                                                                                       @RequestParam(defaultValue = "0") Integer page,
                                                                                       @RequestParam(defaultValue = "15") Integer limit) {
        return ResponseEntity.ok(hydroponicSettingsService.getLastSettingsByUuid(uuid, page, limit));
    }
}

package ua.com.cyberdone.devicemicroservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.cyberdone.devicemicroservice.controller.docs.BaseControlApi;
import ua.com.cyberdone.devicemicroservice.model.microcontrollers.hydroponic.HydroponicTimeDto;
import ua.com.cyberdone.devicemicroservice.service.control.hydroponic.HydroponicOneOperationService;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static ua.com.cyberdone.devicemicroservice.persistence.entity.ValueType.NUMBER;
import static ua.com.cyberdone.devicemicroservice.persistence.entity.ValueType.TEXT;

@RequiredArgsConstructor
public class BaseControlController implements BaseControlApi {
    private final HydroponicOneOperationService operationService;

    @PutMapping("/update/time")
    @PreAuthorize("hasAnyAuthority('u_all','u_hydroponic_time')")
    public ResponseEntity<String> updateTime(@RequestHeader(AUTHORIZATION) String token,
                                             @RequestBody HydroponicTimeDto dto) {
        operationService.updateTime(dto.getUuid(), dto.getMicrocontrollerTime());
        return ResponseEntity.ok("OK");
    }

    @PutMapping("/update/zone")
    @PreAuthorize("hasAnyAuthority('u_all','u_hydroponic_time_zone')")
    public ResponseEntity<String> updateZone(@RequestHeader(AUTHORIZATION) String token,
                                             @RequestParam String uuid,
                                             @RequestParam String value) {
        operationService.changeTimeZoneSetting(uuid, value, TEXT);
        return ResponseEntity.ok("OK");
    }

    @PutMapping("/update/autotime")
    @PreAuthorize("hasAnyAuthority('u_all','u_hydroponic_autotime')")
    public ResponseEntity<String> updateAutoTime(@RequestHeader(AUTHORIZATION) String token,
                                                 @RequestParam String uuid,
                                                 @RequestParam String value) {
        operationService.changeAutoTimeSetting(uuid, value, NUMBER);
        return ResponseEntity.ok("OK");
    }

    @PutMapping("/update/restart")
    @PreAuthorize("hasAnyAuthority('u_all','u_hydroponic_restart')")
    public ResponseEntity<String> restart(@RequestHeader(AUTHORIZATION) String token,
                                          @RequestParam String uuid) {
        operationService.restart(uuid);
        return ResponseEntity.ok("OK");
    }

    @PutMapping("/update/restart-counter")
    @PreAuthorize("hasAnyAuthority('u_all','u_hydroponic_restart')")
    public ResponseEntity<String> restartCounter(@RequestHeader(AUTHORIZATION) String token,
                                                 @RequestParam String uuid,
                                                 @RequestParam String value) {
        operationService.restartCounter(uuid, value, NUMBER);
        return ResponseEntity.ok("OK");
    }

    @PutMapping("/save/settings")
    @PreAuthorize("hasAnyAuthority('u_all','u_hydroponic_save_settings')")
    public ResponseEntity<String> saveAllSettings(@RequestHeader(AUTHORIZATION) String token,
                                                  @RequestParam String uuid) {
        operationService.saveAllSettings(uuid);
        return ResponseEntity.ok("OK");
    }

    @PutMapping("/read/settings")
    @PreAuthorize("hasAnyAuthority('u_all','u_hydroponic_read_settings')")
    public ResponseEntity<String> readAllSettings(@RequestHeader(AUTHORIZATION) String token,
                                                  @RequestParam String uuid) {
        operationService.readAllSettings(uuid);
        return ResponseEntity.ok("OK");
    }

    @PutMapping("/update/wifi/ssid")
    @PreAuthorize("hasAnyAuthority('u_all','u_hydroponic_wifi_ssid')")
    public ResponseEntity<String> updateWifiSsid(@RequestHeader(AUTHORIZATION) String token,
                                                 @RequestParam String uuid,
                                                 @RequestParam String value) {
        operationService.updateWifiSsid(uuid, value, TEXT);
        return ResponseEntity.ok("OK");
    }

    @PutMapping("/update/wifi/pass")
    @PreAuthorize("hasAnyAuthority('u_all','u_hydroponic_wifi_pass')")
    public ResponseEntity<String> updateWifiPassword(@RequestHeader(AUTHORIZATION) String token,
                                                     @RequestParam String uuid,
                                                     @RequestParam String value) {
        operationService.updateWifiPassword(uuid, value, TEXT);
        return ResponseEntity.ok("OK");
    }
}

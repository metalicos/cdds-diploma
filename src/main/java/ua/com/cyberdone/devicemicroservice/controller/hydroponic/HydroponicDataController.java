package ua.com.cyberdone.devicemicroservice.controller.hydroponic;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.com.cyberdone.devicemicroservice.controller.docs.HydroponicDataApi;
import ua.com.cyberdone.devicemicroservice.model.microcontrollers.hydroponic.HydroponicDataDto;
import ua.com.cyberdone.devicemicroservice.persistence.service.HydroponicDataService;

import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hydroponic/data")
public class HydroponicDataController implements HydroponicDataApi {
    private final HydroponicDataService hydroponicDataService;

    @GetMapping("/last")
    @PreAuthorize("hasAnyAuthority('r_all','r_all_hydroponic_data')")
    public ResponseEntity<List<HydroponicDataDto>> getLastDataInDeviceWithUuid(@RequestHeader(AUTHORIZATION) String token,
                                                                               @RequestParam String uuid,
                                                                               @RequestParam Integer page,
                                                                               @RequestParam Integer limit) {
        return ResponseEntity.ok(hydroponicDataService.getLastDataByUuid(uuid, page, limit));
    }

    @DeleteMapping
    @PreAuthorize("hasAnyAuthority('d_all','d_all_hydroponic_data')")
    public ResponseEntity<Void> deleteAllDataInDeviceWithUuid(@RequestHeader(AUTHORIZATION) String token,
                                                              @RequestParam String uuid) {
        hydroponicDataService.deleteDataByUuid(uuid);
        return ResponseEntity.ok().build();
    }
}

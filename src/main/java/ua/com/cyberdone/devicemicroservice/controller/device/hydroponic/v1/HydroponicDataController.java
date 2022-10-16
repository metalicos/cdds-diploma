package ua.com.cyberdone.devicemicroservice.controller.device.hydroponic.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.com.cyberdone.devicemicroservice.data.entity.hydroponic.v1.HydroponicData;
import ua.com.cyberdone.devicemicroservice.service.hydroponic.v1.HydroponicDataService;

import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequiredArgsConstructor
@RequestMapping("/device/hydroponic/v1/data")
public class HydroponicDataController {
    private final HydroponicDataService hydroponicDataService;

    @GetMapping("/last")
    @PreAuthorize("hasAnyAuthority('r_all','r_all_hydroponic_data')")
    public ResponseEntity<List<HydroponicData>> getLastDataInDeviceWithUuid(@RequestHeader(AUTHORIZATION) String token,
                                                                            @RequestParam String uuid,
                                                                            @RequestParam Long page,
                                                                            @RequestParam Long limit) {
        return ResponseEntity.ok(hydroponicDataService.findAll(uuid, page, limit));
    }

    @GetMapping("/range")
    @PreAuthorize("hasAnyAuthority('r_all','r_all_hydroponic_data')")
    public ResponseEntity<List<HydroponicData>> getLastDataInDeviceWithUuid(@RequestHeader(AUTHORIZATION) String token,
                                                                            @RequestParam String uuid,
                                                                            @RequestParam String fromDate,
                                                                            @RequestParam String toDate,
                                                                            @RequestParam int dataStep) {
        return ResponseEntity.ok(hydroponicDataService.findAllInRangeWithMinutesStep(uuid, fromDate, toDate, dataStep));
    }

    @DeleteMapping
    @PreAuthorize("hasAnyAuthority('d_all','d_all_hydroponic_data')")
    public ResponseEntity<Void> deleteAllDataInDeviceWithUuid(@RequestHeader(AUTHORIZATION) String token,
                                                              @RequestParam String uuid) {
        hydroponicDataService.deleteAll(uuid);
        return ResponseEntity.ok().build();
    }

}

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
import ua.com.cyberdone.devicemicroservice.controller.docs.HydroponicCalibrationApi;
import ua.com.cyberdone.devicemicroservice.persistence.model.microcontrollers.hydroponic.HydroponicCalibrationDataDto;
import ua.com.cyberdone.devicemicroservice.persistence.service.HydroponicCalibrationDataService;

import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hydroponic/calibration-data")
public class HydroponicCalibrationController implements HydroponicCalibrationApi {
    private final HydroponicCalibrationDataService calibrationDataService;

    @GetMapping("/last")
    @PreAuthorize("hasAnyAuthority('r_all','r_all_hydroponic_calibration_data')")
    public ResponseEntity<List<HydroponicCalibrationDataDto>> getLastCalibrationDataInDeviceWithUuid(@RequestHeader(AUTHORIZATION) String token, @RequestParam String uuid, @RequestParam Integer page, @RequestParam Integer limit) {
        return ResponseEntity.ok(calibrationDataService.getLastCalibrationByUuid(uuid, page, limit));
    }

    @DeleteMapping
    @PreAuthorize("hasAnyAuthority('d_all','d_hydroponic_calibration_data')")
    public ResponseEntity<Void> deleteCalibrationDataByUuid(@RequestHeader(AUTHORIZATION) String token, @RequestParam String uuid) {
        calibrationDataService.deleteCalibrationByUuid(uuid);
        return ResponseEntity.ok().build();
    }
}

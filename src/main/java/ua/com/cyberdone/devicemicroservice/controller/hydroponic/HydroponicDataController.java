package ua.com.cyberdone.devicemicroservice.controller.hydroponic;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.com.cyberdone.devicemicroservice.controller.docs.HydroponicDataApi;
import ua.com.cyberdone.devicemicroservice.model.dto.microcontrollers.hydroponic.HydroponicDataDto;
import ua.com.cyberdone.devicemicroservice.persistence.service.HydroponicDataService;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static ua.com.cyberdone.devicemicroservice.validation.ValidationConstants.NOT_POSITIVE_MSG;
import static ua.com.cyberdone.devicemicroservice.validation.ValidationConstants.UUID_FAILED_MSG;
import static ua.com.cyberdone.devicemicroservice.validation.ValidationConstants.UUID_PATTERN;
import static ua.com.cyberdone.devicemicroservice.validation.ValidationConstants.VALUE_IS_BLANK_MSG;
import static ua.com.cyberdone.devicemicroservice.validation.ValidationConstants.VALUE_IS_NULL_MSG;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hydroponic/data")
public class HydroponicDataController implements HydroponicDataApi {
    private final HydroponicDataService hydroponicDataService;

    @GetMapping("/last")
    public ResponseEntity<List<HydroponicDataDto>> getLastDataInDeviceWithUuid(
            @RequestHeader(AUTHORIZATION) String token,
            @NotBlank(message = VALUE_IS_BLANK_MSG) @Pattern(regexp = UUID_PATTERN, message = UUID_FAILED_MSG)
            @RequestParam String uuid,
            @NotNull(message = VALUE_IS_NULL_MSG) @Positive(message = NOT_POSITIVE_MSG)
            @RequestParam Integer page,
            @NotNull(message = VALUE_IS_NULL_MSG) @Positive(message = NOT_POSITIVE_MSG)
            @RequestParam Integer limit) {
        return ResponseEntity.ok(hydroponicDataService.getLastDataByUuid(uuid, page, limit));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllDataInDeviceWithUuid(
            @RequestHeader(AUTHORIZATION) String token,
            @NotNull(message = VALUE_IS_NULL_MSG) @Positive(message = NOT_POSITIVE_MSG)
            @RequestParam String uuid) {
        hydroponicDataService.deleteDataByUuid(uuid);
        return ResponseEntity.ok().build();
    }
}

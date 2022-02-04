package ua.com.cyberdone.devicemicroservice.controller.hydroponic;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.com.cyberdone.devicemicroservice.controller.docs.HydroponicSettingsApi;
import ua.com.cyberdone.devicemicroservice.model.dto.microcontrollers.hydroponic.HydroponicSettingsDto;
import ua.com.cyberdone.devicemicroservice.persistence.service.HydroponicSettingsService;

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

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/hydroponic/settings")
public class HydroponicSettingsController implements HydroponicSettingsApi {
    private final HydroponicSettingsService hydroponicSettingsService;

    @GetMapping("/last")
    public ResponseEntity<List<HydroponicSettingsDto>> getLastSettingsInDeviceWithUuid(
            @RequestHeader(AUTHORIZATION) String token,
            @NotBlank(message = VALUE_IS_BLANK_MSG) @Pattern(regexp = UUID_PATTERN, message = UUID_FAILED_MSG)
            @RequestParam String uuid,
            @NotNull(message = VALUE_IS_NULL_MSG) @Positive(message = NOT_POSITIVE_MSG)
            @RequestParam(defaultValue = "0") Integer page,
            @NotNull(message = VALUE_IS_NULL_MSG) @Positive(message = NOT_POSITIVE_MSG)
            @RequestParam(defaultValue = "15") Integer limit) {
        return ResponseEntity.ok(hydroponicSettingsService.getLastSettingsByUuid(uuid, page, limit));
    }
}

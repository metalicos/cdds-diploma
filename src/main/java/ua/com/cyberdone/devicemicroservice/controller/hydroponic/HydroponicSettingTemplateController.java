package ua.com.cyberdone.devicemicroservice.controller.hydroponic;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.com.cyberdone.devicemicroservice.constant.ControllerConstantUtils;
import ua.com.cyberdone.devicemicroservice.controller.docs.HydroponicSettingTemplateApi;
import ua.com.cyberdone.devicemicroservice.model.dto.microcontrollers.hydroponic.HydroponicSettingTemplateDto;
import ua.com.cyberdone.devicemicroservice.model.dto.microcontrollers.hydroponic.HydroponicSettingsDto;
import ua.com.cyberdone.devicemicroservice.persistence.service.HydroponicSettingTemplateService;
import ua.com.cyberdone.devicemicroservice.security.JwtService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static ua.com.cyberdone.devicemicroservice.validation.ValidationConstants.NOT_POSITIVE_MSG;
import static ua.com.cyberdone.devicemicroservice.validation.ValidationConstants.VALUE_IS_NULL_MSG;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/hydroponic/setting/template")
public class HydroponicSettingTemplateController implements HydroponicSettingTemplateApi {
    private final HydroponicSettingTemplateService hydroponicSettingTemplateService;
    private final JwtService jwtService;

    @GetMapping("/last")
    public ResponseEntity<List<HydroponicSettingsDto>> getLastSettingTemplate(
            @RequestHeader(AUTHORIZATION) String token,
            @NotNull(message = VALUE_IS_NULL_MSG) @Positive(message = NOT_POSITIVE_MSG)
            @RequestParam(defaultValue = "0") Integer page,
            @NotNull(message = VALUE_IS_NULL_MSG) @Positive(message = NOT_POSITIVE_MSG)
            @RequestParam(defaultValue = "15") Integer limit) {
        var userId = jwtService.getUserId(token);
        return ResponseEntity.ok(hydroponicSettingTemplateService.getLastHydroponicSettingTemplates(userId, page, limit));
    }

    @PostMapping
    public ResponseEntity<HydroponicSettingTemplateDto> createHydroponicSettingTemplate(
            @Valid HydroponicSettingTemplateDto dto) {
        return ResponseEntity.ok(hydroponicSettingTemplateService.saveHydroponicSettingTemplate(dto));
    }

    @PutMapping
    public ResponseEntity<String> updateHydroponicSettingTemplate(@Valid HydroponicSettingTemplateDto dto) {
        hydroponicSettingTemplateService.updateHydroponicSettingTemplate(dto);
        return ResponseEntity.ok(ControllerConstantUtils.OK);
    }

    @DeleteMapping("/{templateId}")
    public ResponseEntity<String> deleteHydroponicSettingTemplate(@PathVariable Long templateId) {
        hydroponicSettingTemplateService.deleteHydroponicSettingTemplate(templateId);
        return ResponseEntity.ok(ControllerConstantUtils.OK);
    }
}

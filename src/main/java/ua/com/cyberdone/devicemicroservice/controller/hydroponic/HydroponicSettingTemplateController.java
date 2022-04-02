package ua.com.cyberdone.devicemicroservice.controller.hydroponic;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.com.cyberdone.devicemicroservice.constant.ControllerConstantUtils;
import ua.com.cyberdone.devicemicroservice.controller.docs.HydroponicSettingTemplateApi;
import ua.com.cyberdone.devicemicroservice.model.microcontrollers.hydroponic.HydroponicSettingTemplateDto;
import ua.com.cyberdone.devicemicroservice.model.microcontrollers.hydroponic.HydroponicSettingsDto;
import ua.com.cyberdone.devicemicroservice.persistence.service.HydroponicSettingTemplateService;
import ua.com.cyberdone.devicemicroservice.security.JwtService;

import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/hydroponic/setting/template")
public class HydroponicSettingTemplateController implements HydroponicSettingTemplateApi {
    private final HydroponicSettingTemplateService hydroponicSettingTemplateService;
    private final JwtService jwtService;

    @GetMapping("/last")
    @PreAuthorize("hasAnyAuthority('r_all','r_all_hydroponic_setting_templates')")
    public ResponseEntity<List<HydroponicSettingsDto>> getLastSettingTemplate(@RequestHeader(AUTHORIZATION) String token,
                                                                              @RequestParam(defaultValue = "0") Integer page,
                                                                              @RequestParam(defaultValue = "15") Integer limit) {
        var userId = jwtService.getUserId(token);
        return ResponseEntity.ok(hydroponicSettingTemplateService.getLastHydroponicSettingTemplates(userId, page, limit));
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('w_all','w_hydroponic_setting_template')")
    public ResponseEntity<HydroponicSettingTemplateDto> createHydroponicSettingTemplate(@RequestHeader(AUTHORIZATION) String token,
                                                                                        @RequestBody HydroponicSettingTemplateDto dto) {
        return ResponseEntity.ok(hydroponicSettingTemplateService.saveHydroponicSettingTemplate(dto));
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('u_all','u_hydroponic_setting_template')")
    public ResponseEntity<String> updateHydroponicSettingTemplate(@RequestHeader(AUTHORIZATION) String token,
                                                                  @RequestBody HydroponicSettingTemplateDto dto) {
        hydroponicSettingTemplateService.updateHydroponicSettingTemplate(dto);
        return ResponseEntity.ok(ControllerConstantUtils.OK);
    }

    @DeleteMapping("/{templateId}")
    @PreAuthorize("hasAnyAuthority('d_all','d_hydroponic_setting_template')")
    public ResponseEntity<String> deleteHydroponicSettingTemplate(@RequestHeader(AUTHORIZATION) String token,
                                                                  @PathVariable Long templateId) {
        hydroponicSettingTemplateService.deleteHydroponicSettingTemplate(templateId);
        return ResponseEntity.ok(ControllerConstantUtils.OK);
    }
}

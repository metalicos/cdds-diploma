package ua.com.cyberdone.devicemicroservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.com.cyberdone.devicemicroservice.controller.docs.DelegatedDeviceControlApi;
import ua.com.cyberdone.devicemicroservice.exception.AlreadyExistException;
import ua.com.cyberdone.devicemicroservice.exception.IllegalOperationException;
import ua.com.cyberdone.devicemicroservice.exception.NotFoundException;
import ua.com.cyberdone.devicemicroservice.persistence.entity.DelegationStatus;
import ua.com.cyberdone.devicemicroservice.persistence.model.delegation.DelegatedDeviceControlDto;
import ua.com.cyberdone.devicemicroservice.persistence.model.delegation.PageableDelegatedDeviceControlDto;
import ua.com.cyberdone.devicemicroservice.persistence.service.DelegatedDeviceControlService;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static ua.com.cyberdone.devicemicroservice.util.ControllerConstantUtils.DEFAULT_COMMENT_FOR_DEVICE_CONTROL_DELEGATION;
import static ua.com.cyberdone.devicemicroservice.util.ControllerConstantUtils.DEFAULT_DIRECTION;
import static ua.com.cyberdone.devicemicroservice.util.ControllerConstantUtils.DEFAULT_ELEMENTS_AMOUNT;
import static ua.com.cyberdone.devicemicroservice.util.ControllerConstantUtils.DEFAULT_PAGE;
import static ua.com.cyberdone.devicemicroservice.util.ControllerConstantUtils.DEFAULT_SEARCH;
import static ua.com.cyberdone.devicemicroservice.util.ControllerConstantUtils.DELEGATION_STATUS_PARAMETER;
import static ua.com.cyberdone.devicemicroservice.util.ControllerConstantUtils.DEVICE_UUID_PARAMETER;
import static ua.com.cyberdone.devicemicroservice.util.ControllerConstantUtils.OK;
import static ua.com.cyberdone.devicemicroservice.util.ControllerConstantUtils.USERNAME_PARAMETER;

@RestController
@RequiredArgsConstructor
@RequestMapping("/delegated-device-controls")
public class DelegatedDeviceControlController implements DelegatedDeviceControlApi {
    private final DelegatedDeviceControlService delegatedDeviceControlService;

    @GetMapping("/self")
    @PreAuthorize("hasAnyAuthority('r_all','r_self_delegated_device_control')")
    public ResponseEntity<PageableDelegatedDeviceControlDto> getDelegatedDeviceControlForUserByToken(
            @RequestHeader(AUTHORIZATION) String token,
            @RequestParam(defaultValue = DEFAULT_PAGE, required = false) Integer page,
            @RequestParam(defaultValue = DEFAULT_ELEMENTS_AMOUNT, required = false) Integer size,
            @RequestParam(defaultValue = DEFAULT_DIRECTION, required = false) String direction,
            @RequestParam(defaultValue = DEFAULT_SEARCH, required = false) String sortBy) {
        return ResponseEntity.ok(delegatedDeviceControlService.getSelfDelegatedDevices(token, page, size, direction, sortBy));
    }

    @GetMapping("/list")
    @PreAuthorize("hasAnyAuthority('r_all','r_delegated_device_control')")
    public ResponseEntity<PageableDelegatedDeviceControlDto> getAllDelegatedDeviceControlByDeviceUuidAndOwnerToken(
            @RequestHeader(AUTHORIZATION) String token,
            @RequestParam(DEVICE_UUID_PARAMETER) String deviceUuid,
            @RequestParam(DELEGATION_STATUS_PARAMETER) DelegationStatus delegationStatus,
            @RequestParam(defaultValue = DEFAULT_PAGE, required = false) Integer page,
            @RequestParam(defaultValue = DEFAULT_ELEMENTS_AMOUNT, required = false) Integer size,
            @RequestParam(defaultValue = DEFAULT_DIRECTION, required = false) String direction,
            @RequestParam(defaultValue = DEFAULT_SEARCH, required = false) String sortBy) {
        return ResponseEntity.ok(delegatedDeviceControlService.getAllDelegatedDeviceControlByDeviceUuidAndOwnerToken(token,
                deviceUuid, delegationStatus, page, size, direction, sortBy));
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('r_all','r_delegated_device_control')")
    public ResponseEntity<DelegatedDeviceControlDto> getDelegatedDeviceControlForUser(
            @RequestHeader(AUTHORIZATION) String token,
            @RequestParam(USERNAME_PARAMETER) String username,
            @RequestParam(DEVICE_UUID_PARAMETER) String deviceUuid) throws NotFoundException {
        return ResponseEntity.ok(delegatedDeviceControlService.getDelegatedDeviceControl(username, deviceUuid));
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('u_all','u_delegated_device_control')")
    public ResponseEntity<String> updateDelegationStatus(
            @RequestHeader(AUTHORIZATION) String token,
            @RequestParam(DEVICE_UUID_PARAMETER) String deviceUuid,
            @RequestParam(USERNAME_PARAMETER) String username,
            @RequestParam(DELEGATION_STATUS_PARAMETER) DelegationStatus delegationStatus) {
        delegatedDeviceControlService.updateDelegationStatus(token, username, deviceUuid, delegationStatus);
        return ResponseEntity.ok(OK);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('w_all','w_delegated_device_control_request')")
    public ResponseEntity<DelegatedDeviceControlDto> createDelegatedDeviceControl(
            @RequestHeader(AUTHORIZATION) String token,
            @RequestParam(defaultValue = DEFAULT_COMMENT_FOR_DEVICE_CONTROL_DELEGATION, required = false) String comment,
            @RequestParam(DEVICE_UUID_PARAMETER) String deviceUuid) throws IllegalOperationException, AlreadyExistException, NotFoundException {
        return ResponseEntity.ok(delegatedDeviceControlService.createRequestForDelegation(token, comment, deviceUuid));
    }
}

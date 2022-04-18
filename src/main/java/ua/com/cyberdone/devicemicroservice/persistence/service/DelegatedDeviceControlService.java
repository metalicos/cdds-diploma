package ua.com.cyberdone.devicemicroservice.persistence.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ua.com.cyberdone.devicemicroservice.exception.AlreadyExistException;
import ua.com.cyberdone.devicemicroservice.exception.IllegalOperationException;
import ua.com.cyberdone.devicemicroservice.exception.NotFoundException;
import ua.com.cyberdone.devicemicroservice.mapper.DelegatedDeviceControlMapper;
import ua.com.cyberdone.devicemicroservice.persistence.entity.DelegatedDeviceControl;
import ua.com.cyberdone.devicemicroservice.persistence.entity.DelegationStatus;
import ua.com.cyberdone.devicemicroservice.persistence.entity.DeviceMetadata;
import ua.com.cyberdone.devicemicroservice.persistence.model.delegation.DelegatedDeviceControlDto;
import ua.com.cyberdone.devicemicroservice.persistence.model.delegation.PageableDelegatedDeviceControlDto;
import ua.com.cyberdone.devicemicroservice.persistence.repository.DelegatedDeviceControlRepository;
import ua.com.cyberdone.devicemicroservice.security.JwtService;

import java.util.Optional;

import static java.util.Objects.isNull;
import static ua.com.cyberdone.devicemicroservice.util.ControllerConstantUtils.DEFAULT_SEARCH;

@Slf4j
@Service
@RequiredArgsConstructor
public class DelegatedDeviceControlService {
    private final DelegatedDeviceControlRepository delegatedDeviceControlRepository;
    private final DeviceMetadataService deviceMetadataService;
    private final ModelMapper modelMapper;
    private final JwtService jwtService;

    public DelegatedDeviceControlDto createRequestForDelegation(String token, String commentToDelegationRequest,
                                                                String deviceUuid) throws IllegalOperationException, AlreadyExistException, NotFoundException {
        var username = jwtService.getUsername(token);
        var ownerId = jwtService.getUserId(token);
        var deviceMetadata = modelMapper.map(deviceMetadataService.getMetadataByUuid(deviceUuid), DeviceMetadata.class);
        if (isNull(deviceMetadata.getUserId())) {
            throw new IllegalOperationException("Can not create request for delegation. Device is not assigned to owner.");
        }
        if (ownerId == deviceMetadata.getUserId()) {
            throw new IllegalOperationException("Can not create request for delegation. You are owner.");
        }
        if (delegatedDeviceControlRepository.existsByUsernameAndDeviceUuid(username, deviceUuid)){
            throw new AlreadyExistException("Delegation request is already created.");
        }
        var delegationEntity = new DelegatedDeviceControl(username, commentToDelegationRequest, deviceMetadata, DelegationStatus.REQUESTED);
        log.info("Delegation request: {}", delegationEntity);
        return modelMapper.map(delegatedDeviceControlRepository.save(delegationEntity), DelegatedDeviceControlDto.class);
    }

    public void updateDelegationStatus(String token, String delegationCandidateUser,
                                       String deviceUuid, DelegationStatus delegationStatus) {
        var ownerId = jwtService.getUserId(token);
        delegatedDeviceControlRepository.updateDelegationStatus(ownerId, delegationCandidateUser, deviceUuid, delegationStatus.name());
    }

    public DelegatedDeviceControlDto getDelegatedDeviceControl(String username, String deviceUuid) throws NotFoundException {
        var delegatedControl = delegatedDeviceControlRepository.getDelegatedDeviceControlByUsernameAndDeviceUuid(username, deviceUuid)
                .orElseThrow(() -> new NotFoundException("Delegated device control for username='" + username +
                        "' device='" + deviceUuid + "' is not found"));
        return modelMapper.map(delegatedControl, DelegatedDeviceControlDto.class);
    }

    public PageableDelegatedDeviceControlDto getDelegatedDeviceControlByDeviceUuid(String deviceUuid, int page, int limit,
                                                                                   String direction, String sortBy) {
        if (DEFAULT_SEARCH.equals(sortBy)) {
            return getPageableDto(delegatedDeviceControlRepository
                    .getDelegatedDeviceControlByDeviceUuid(PageRequest.of(page, limit), deviceUuid));
        }
        return getPageableDto(delegatedDeviceControlRepository
                .getDelegatedDeviceControlByDeviceUuid(PageRequest.of(page, limit,
                        Sort.by(Sort.Direction.fromString(direction), sortBy)), deviceUuid));
    }

    public PageableDelegatedDeviceControlDto getAllDelegatedDeviceControlByDeviceUuidAndOwnerToken(String token, String deviceUuid,
                                                                                                   DelegationStatus delegationStatus,
                                                                                                   int page, int limit, String direction, String sortBy) {
        var ownerId = jwtService.getUserId(token);


        if (DEFAULT_SEARCH.equals(sortBy)) {
            return getPageableDto(delegatedDeviceControlRepository.getDelegatedDeviceControlByOwnerIdAndStatus(PageRequest.of(page, limit),
                    ownerId, deviceUuid, delegationStatus));
        }
        return getPageableDto(delegatedDeviceControlRepository.getDelegatedDeviceControlByOwnerIdAndStatus(
                PageRequest.of(page, limit, Sort.by(Sort.Direction.fromString(direction), sortBy)),
                ownerId, deviceUuid, delegationStatus));
    }

    public PageableDelegatedDeviceControlDto getSelfDelegatedDevices(String token, int page, int limit,
                                                                     String direction, String sortBy) {
        var username = jwtService.getUsername(token);
        return getDelegatedDeviceControlByUsername(username, page, limit, direction, sortBy);
    }

    public PageableDelegatedDeviceControlDto getDelegatedDeviceControlByUsername(String username, int page, int limit,
                                                                                 String direction, String sortBy) {
        if (DEFAULT_SEARCH.equals(sortBy)) {
            return getPageableDto(delegatedDeviceControlRepository
                    .getDelegatedDeviceControlByUsername(PageRequest.of(page, limit), username));
        }
        return getPageableDto(delegatedDeviceControlRepository.getDelegatedDeviceControlByUsername(
                PageRequest.of(page, limit, Sort.by(Sort.Direction.fromString(direction), sortBy)), username));
    }

    private PageableDelegatedDeviceControlDto getPageableDto(Page<DelegatedDeviceControl> pageable) {
        return PageableDelegatedDeviceControlDto.builder()
                .page(pageable.getNumber())
                .elementsOnThePage(pageable.getNumberOfElements())
                .totallyElements(pageable.getTotalElements())
                .totallyPages(pageable.getTotalPages())
                .content(new DelegatedDeviceControlMapper<DelegatedDeviceControlDto>(modelMapper)
                        .toDtoList(pageable.getContent(), DelegatedDeviceControlDto.class))
                .build();
    }
}

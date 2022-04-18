package ua.com.cyberdone.devicemicroservice.persistence.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.com.cyberdone.devicemicroservice.persistence.entity.DelegatedDeviceControl;
import ua.com.cyberdone.devicemicroservice.persistence.entity.DelegationStatus;

import java.util.Optional;

@Repository
public interface DelegatedDeviceControlRepository extends PagingAndSortingRepository<DelegatedDeviceControl, Long> {

    @Modifying
    @Query("update DelegatedDeviceControl d set d.delegationStatus = :status where d.deviceMetadata.userId = :ownerId " +
            "and d.delegatedUserUsername = :username and d.deviceMetadata.uuid = :uuid")
    void updateDelegationStatus(@Param("ownerId") Long ownerId,
                                @Param("username") String delegateUsername,
                                @Param("uuid") String deviceUuid,
                                @Param("status") String newStatus);

    @Query("from DelegatedDeviceControl d where d.delegatedUserUsername = :username and d.deviceMetadata.uuid = :uuid")
    Optional<DelegatedDeviceControl> getDelegatedDeviceControlByUsernameAndDeviceUuid(@Param("username") String username,
                                                                                      @Param("uuid") String deviceUuid);


    @Query("from DelegatedDeviceControl d where d.deviceMetadata.userId = :ownerId and d.deviceMetadata.uuid = :uuid " +
            "and d.delegationStatus = :status")
    Page<DelegatedDeviceControl> getDelegatedDeviceControlByOwnerIdAndStatus(Pageable pageable,
                                                                             @Param("ownerId") Long ownerId,
                                                                             @Param("uuid") String deviceUuid,
                                                                             @Param("status") DelegationStatus status);

    @Query("from DelegatedDeviceControl d where d.delegatedUserUsername = :username")
    Page<DelegatedDeviceControl> getDelegatedDeviceControlByUsername(Pageable pageable,
                                                                     @Param("username") String username);

    @Query("from DelegatedDeviceControl d where d.deviceMetadata.uuid = :uuid")
    Page<DelegatedDeviceControl> getDelegatedDeviceControlByDeviceUuid(Pageable pageable,
                                                                       @Param("uuid") String deviceUuid);

    @Query("select case when count(d.id) > 0 then true else false end " +
            "from DelegatedDeviceControl d where d.deviceMetadata.uuid = :uuid and d.delegatedUserUsername = :username")
    boolean existsByUsernameAndDeviceUuid(@Param("username") String username,
                                          @Param("uuid") String deviceUuid);
}

package ua.com.cyberdone.devicemicroservice.persistence.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.com.cyberdone.devicemicroservice.persistence.entity.DeviceMetadata;

import java.util.List;
import java.util.Optional;

public interface DeviceMetadataRepository extends JpaRepository<DeviceMetadata, Long> {

    Optional<DeviceMetadata> findByUuid(String uuid);

    @Query("select m.accessEnabled from DeviceMetadata m where m.uuid = :uuid")
    boolean isEnabled(@Param("uuid") String uuid);

    void deleteByUuid(String uuid);

    List<DeviceMetadata> findAllByUserId(Long userId);

    @Modifying
    @Query("update DeviceMetadata m set m.userId = :userId where m.uuid = :uuid")
    void linkDeviceMetadataToUser(@Param("uuid") String uuid, @Param("userId") Long userId);

    @Modifying
    @Query("update DeviceMetadata m set m.userId = 0 where m.uuid = :uuid")
    void unlinkDeviceMetadataFromUser(@Param("uuid") String uuid);

    @Query("select case when (m.userId > 0) then true else false end " +
            "from DeviceMetadata m where m.uuid = :uuid")
    boolean isMetadataLinkedToAccount(@Param("uuid") String uuid);

    @Query("select case when (m.userId <> 0) then true else false end " +
            "from DeviceMetadata m where m.uuid = :uuid")
    boolean isMetadataUnlinkedToAccount(@Param("uuid") String uuid);

    boolean existsByUuid(String uuid);
}
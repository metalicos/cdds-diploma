package ua.com.cyberdone.devicemicroservice.device.common.repository;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import ua.com.cyberdone.devicemicroservice.device.common.entity.DeviceMetadata;

import java.sql.Blob;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public interface DeviceMetadataRepository extends Repository<DeviceMetadata, Long> {

    @Query("""
            SELECT * FROM public."DEVICE_METADATA"
            WHERE uuid = :uuid
            """)
    Optional<DeviceMetadata> findByUuid(String uuid);

    @Query("""
            SELECT * FROM public."DEVICE_METADATA"
            WHERE owner_id = :ownerId
            """)
    List<DeviceMetadata> findAllByOwnerId(@Param("ownerId") Long ownerId);

    @Query("""
            SELECT * FROM public."DEVICE_METADATA"
            WHERE owner_id = :ownerId LIMIT :size OFFSET :from
            """)
    List<DeviceMetadata> findAllByOwnerId(@Param("ownerId") Long ownerId,
                                          @Param("from") Long from,
                                          @Param("size") Long size);

    @Modifying
    @Query("""
            INSERT INTO public."DEVICE_METADATA"
            (uuid, name, description, owner_id, delegation_key, logo, device_type_id, created_timestamp) 
            VALUES 
            (:uuid, :name, :description, :ownerId, :delegationKey, :logo, :deviceTypeId, :createdTimestamp)
            """)
    void save(@Param("uuid") String uuid,
              @Param("name") String name,
              @Param("description") String description,
              @Param("ownerId") Long ownerId,
              @Param("delegationKey") String delegationKey,
              @Param("logo") Blob logo,
              @Param("deviceTypeId") Long deviceTypeId,
              @Param("createdTimestamp") LocalDateTime createdTimestamp);

    @Modifying
    @Query("""
            UPDATE public."DEVICE_METADATA" 
            SET name = :name, description = :description, owner_id = :ownerId, 
            delegation_key = :delegationKey, logo = :logo, device_type_id = :deviceTypeId 
            WHERE 
            uuid = :uuid""")
    void updateDeviceMetadata(@Param("uuid") String uuid,
                              @Param("name") String name,
                              @Param("description") String description,
                              @Param("ownerId") Long ownerId,
                              @Param("delegationKey") String delegationKey,
                              @Param("logo") Blob logo,
                              @Param("deviceTypeId") Long deviceTypeId);

    @Modifying
    @Query("""
            UPDATE public."DEVICE_METADATA"
            SET name = :name, description = :description
            WHERE uuid = :uuid
            """)
    void updateDeviceMetadata(@Param("uuid") String uuid,
                              @Param("name") String name,
                              @Param("description") String description);

    @Modifying
    @Query("""
            UPDATE public."DEVICE_METADATA"
            SET name = :name, logo = :logo
            WHERE uuid = :uuid
            """)
    void updateDeviceMetadata(@Param("uuid") String uuid,
                              @Param("logo") Blob logo);

    @Modifying
    @Query("""
            UPDATE public."DEVICE_METADATA"
            SET owner_id = :owner_id
            WHERE uuid = :uuid
            """)
    void linkWithOwner(@Param("uuid") String uuid,
                       @Param("owner_id") Long ownerId);

    @Modifying
    @Query("""
            UPDATE public."DEVICE_METADATA"
            SET owner_id = 0
            WHERE uuid = :uuid
            """)
    void unlinkWithOwner(@Param("uuid") String uuid);

    @Modifying
    @Query("""
            DELETE FROM public."DEVICE_METADATA"
            WHERE uuid = :uuid
            """)
    void deleteByUuid(@Param("uuid") String uuid);

    @Query("""
            SELECT (owner_id IS NOT NULL)
            FROM public."DEVICE_METADATA"
            WHERE uuid = :uuid""")
    boolean hasOwner(@Param("uuid") String uuid);
}

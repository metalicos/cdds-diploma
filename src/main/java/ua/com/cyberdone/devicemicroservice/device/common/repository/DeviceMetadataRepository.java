package ua.com.cyberdone.devicemicroservice.device.common.repository;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import ua.com.cyberdone.devicemicroservice.device.common.entity.DeviceMetadata;

import java.util.List;
import java.util.Optional;


public interface DeviceMetadataRepository extends CrudRepository<DeviceMetadata, Long> {

    @Query("""
            SELECT * FROM public."DEVICE_METADATA"
            WHERE uuid = :uuid
            """)
    Optional<DeviceMetadata> findByUuid(@Param("uuid") String uuid);

    @Query("""
            SELECT * FROM public."DEVICE_METADATA"
            WHERE owner_id = :ownerId
            """)
    List<DeviceMetadata> find(@Param("ownerId") Long ownerId);

    @Query("""
            SELECT * FROM public."DEVICE_METADATA"
            WHERE "tariff" = :tariff AND device_type_id = :device_type_id
            """)
    List<DeviceMetadata> find(@Param("tariff") String tariff, @Param("device_type_id") Long deviceTypeId);

    @Query("""
            SELECT * FROM public."DEVICE_METADATA" WHERE device_type_id = :device_type_id
            """)
    List<DeviceMetadata> findAllByDeviceType(@Param("device_type_id") Long deviceTypeId);

    @Query("""
            SELECT * FROM public."DEVICE_METADATA"
            WHERE owner_id = :ownerId LIMIT :size OFFSET :from
            """)
    List<DeviceMetadata> find(@Param("ownerId") Long ownerId,
                              @Param("from") Long from,
                              @Param("size") Long size);

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
            SET owner_id = NULL
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
            SELECT (owner_id > 0 OR (owner_id IS NOT NULL AND owner_id <> 0))
            FROM public."DEVICE_METADATA"
            WHERE uuid = :uuid""")
    boolean hasOwner(@Param("uuid") String uuid);
}

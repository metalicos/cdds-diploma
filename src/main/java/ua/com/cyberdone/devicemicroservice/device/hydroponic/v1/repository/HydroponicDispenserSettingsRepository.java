package ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.repository;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.entity.HydroponicDispenserSettings;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public interface HydroponicDispenserSettingsRepository extends CrudRepository<HydroponicDispenserSettings, Long> {

    @Query("""
            SELECT * FROM public."HYDROPONIC_DISPENSER_SETTINGS_V1" AS hds
            INNER JOIN public."HYDROPONIC_SETTING_DETAILS_V1" AS hsd
            ON hds."setting_detail_id" = hsd."id"
            WHERE hds."device_uuid" = :device_uuid
            AND hsd."setting_type" = 'ACTIVE'
            AND hds."index" = :index
            """)
    Optional<HydroponicDispenserSettings> findActive(@Param("device_uuid") String deviceUuid, @Param("index") Integer index);

    @Query("""
            SELECT * FROM public."HYDROPONIC_DISPENSER_SETTINGS_V1" AS hds
            INNER JOIN public."HYDROPONIC_SETTING_DETAILS_V1" AS hsd
            ON hds."setting_detail_id" = hsd."id"
            WHERE "device_uuid" = :device_uuid
            AND hsd."setting_type" = :type
            LIMIT :size OFFSET :from
            """)
    List<HydroponicDispenserSettings> find(@Param("device_uuid") String deviceUuid,
                                                 @Param("from") Long from,
                                                 @Param("size") Long size,
                                                 @Param("type") String type);


    @Modifying
    @Query("""
            DELETE hds FROM public."HYDROPONIC_DISPENSER_SETTINGS_V1" AS hds
            INNER JOIN public."HYDROPONIC_SETTING_DETAILS_V1" AS hsd
            ON hds."setting_detail_id" = hsd."id"
            WHERE hds."device_uuid" = :device_uuid
            AND hsd."setting_type" = :type
            """)
    void delete(@Param("device_uuid") String deviceUuid,
                @Param("type") String type);

    @Modifying
    @Query("""
            DELETE hds FROM public."HYDROPONIC_DISPENSER_SETTINGS_V1" AS hds
            INNER JOIN public."HYDROPONIC_SETTING_DETAILS_V1" AS hsd
            ON hds."setting_detail_id" = hsd."id"
            WHERE hds."device_uuid" = :device_uuid
            AND hsd."setting_type" = :type
            AND hsd."name" = :name
            """)
    void delete(@Param("device_uuid") String deviceUuid,
                @Param("type") String type,
                @Param("name") String name);
}

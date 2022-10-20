package ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.repository;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.entity.HydroponicSystemSettings;

import java.util.List;
import java.util.Optional;


public interface HydroponicSystemSettingsRepository extends CrudRepository<HydroponicSystemSettings, Long> {

    @Query("""
            SELECT * FROM public."HYDROPONIC_SYSTEM_SETTINGS_V1" AS hpss
            INNER JOIN public."HYDROPONIC_SETTING_DETAILS_V1" AS hsd
            ON hpss."setting_detail_id" = hsd."id"
            WHERE hpss."device_uuid" = :device_uuid
            AND hsd."setting_type" = 'ACTIVE'
            """)
    Optional<HydroponicSystemSettings> findActive(@Param("device_uuid") String deviceUuid);

    @Query("""
            SELECT * FROM public."HYDROPONIC_SYSTEM_SETTINGS_V1" AS hpss
            INNER JOIN public."HYDROPONIC_SETTING_DETAILS_V1" AS hsd
            ON hpss."setting_detail_id" = hsd."id"
            WHERE "device_uuid" = :device_uuid
            AND hsd."setting_type" = :type
            LIMIT :size OFFSET :from
            """)
    List<HydroponicSystemSettings> find(@Param("device_uuid") String deviceUuid,
                                        @Param("from") Long from,
                                        @Param("size") Long size,
                                        @Param("type") String type);


    @Modifying
    @Query("""
            DELETE hpss FROM public."HYDROPONIC_SYSTEM_SETTINGS_V1" AS hpss
            INNER JOIN public."HYDROPONIC_SETTING_DETAILS_V1" AS hsd
            ON hpss."setting_detail_id" = hsd."id"
            WHERE hpss."device_uuid" = :device_uuid
            AND hsd."setting_type" = :type
            """)
    void delete(@Param("device_uuid") String deviceUuid,
                @Param("type") String type);

    @Modifying
    @Query("""
            DELETE hpss FROM public."HYDROPONIC_SYSTEM_SETTINGS_V1" AS hpss
            INNER JOIN public."HYDROPONIC_SETTING_DETAILS_V1" AS hsd
            ON hpss."setting_detail_id" = hsd."id"
            WHERE hpss."device_uuid" = :device_uuid
            AND hsd."setting_type" = :type
            AND hsd."name" = :name
            """)
    void delete(@Param("device_uuid") String deviceUuid,
                @Param("type") String type,
                @Param("name") String name);
}

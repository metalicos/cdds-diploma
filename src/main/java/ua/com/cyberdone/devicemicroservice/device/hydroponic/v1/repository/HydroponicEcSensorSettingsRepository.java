package ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.repository;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.entity.HydroponicDispenserSettings;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.entity.HydroponicEcSensorSettings;

import java.util.List;
import java.util.Optional;


public interface HydroponicEcSensorSettingsRepository extends CrudRepository<HydroponicEcSensorSettings, Long> {

    @Query("""
            SELECT * FROM public."HYDROPONIC_EC_SENSOR_SETTINGS_V1" AS hess
            INNER JOIN public."HYDROPONIC_SETTING_DETAILS_V1" AS hsd
            ON hess."setting_detail_id" = hsd."id"
            WHERE hess."device_uuid" = :device_uuid
            AND hsd."setting_type" = 'ACTIVE'
            """)
    Optional<HydroponicEcSensorSettings> findActive(@Param("device_uuid") String deviceUuid);

    @Query("""
            SELECT * FROM public."HYDROPONIC_EC_SENSOR_SETTINGS_V1" AS hess
            INNER JOIN public."HYDROPONIC_SETTING_DETAILS_V1" AS hsd
            ON hess."setting_detail_id" = hsd."id"
            WHERE "device_uuid" = :device_uuid
            AND hsd."setting_type" = :type
            LIMIT :size OFFSET :from
            """)
    List<HydroponicEcSensorSettings> find(@Param("device_uuid") String deviceUuid,
                                                 @Param("from") Long from,
                                                 @Param("size") Long size,
                                                 @Param("type") String type);


    @Modifying
    @Query("""
            DELETE hess FROM public."HYDROPONIC_EC_SENSOR_SETTINGS_V1" AS hess
            INNER JOIN public."HYDROPONIC_SETTING_DETAILS_V1" AS hsd
            ON hess."setting_detail_id" = hsd."id"
            WHERE hess."device_uuid" = :device_uuid
            AND hsd."setting_type" = :type
            """)
    void delete(@Param("device_uuid") String deviceUuid,
                @Param("type") String type);

    @Modifying
    @Query("""
            DELETE hess FROM public."HYDROPONIC_EC_SENSOR_SETTINGS_V1" AS hess
            INNER JOIN public."HYDROPONIC_SETTING_DETAILS_V1" AS hsd
            ON hess."setting_detail_id" = hsd."id"
            WHERE hess."device_uuid" = :device_uuid
            AND hsd."setting_type" = :type
            AND hsd."name" = :name
            """)
    void delete(@Param("device_uuid") String deviceUuid,
                @Param("type") String type,
                @Param("name") String name);
}

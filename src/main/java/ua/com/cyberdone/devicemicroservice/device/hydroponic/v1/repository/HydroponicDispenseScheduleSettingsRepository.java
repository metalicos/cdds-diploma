package ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.entity.HydroponicDispenseScheduleSettings;

import java.util.Optional;


public interface HydroponicDispenseScheduleSettingsRepository extends CrudRepository<HydroponicDispenseScheduleSettings, Long> {

    @Query("""
            SELECT * FROM public."HYDROPONIC_DISPENSE_SCHEDULE_SETTINGS_V1" AS hdss
            INNER JOIN public."HYDROPONIC_SETTING_DETAILS_V1" AS hsd
            ON hdss."setting_detail_id" = hsd."id"
            WHERE hdss."device_uuid" = :device_uuid
            AND hsd."setting_type" = 'ACTIVE'
            AND hdss."index" = :index
            """)
    Optional<HydroponicDispenseScheduleSettings> findActive(@Param("device_uuid") String deviceUuid, @Param("index") Integer index);
}

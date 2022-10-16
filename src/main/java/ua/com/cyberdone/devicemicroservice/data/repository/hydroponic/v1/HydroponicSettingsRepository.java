package ua.com.cyberdone.devicemicroservice.data.repository.hydroponic.v1;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import ua.com.cyberdone.devicemicroservice.data.entity.hydroponic.v1.HydroponicSettings;
import ua.com.cyberdone.devicemicroservice.model.hydroponic.v1.*;

import java.time.LocalDateTime;
import java.util.List;


public interface HydroponicSettingsRepository extends Repository<HydroponicSettings, Long> {

    @Query("""
            SELECT * FROM public."HYDROPONIC_SETTINGS_V1"
            WHERE device_uuid = :uuid
            AND type = :type
            LIMIT :size OFFSET :from
            """)
    List<HydroponicSettings> findAll(@Param("uuid") String uuid,
                                     @Param("from") Long from,
                                     @Param("size") Long size,
                                     @Param("type") String type);

    @Modifying
    @Query("""
            INSERT INTO public."HYDROPONIC_SETTINGS_V1"
            (device_uuid, name, description, dispensers_sett, dispense_schedules_sett, system_sett, special_system_sett,
            ph_sensor_sett, ec_sensor_sett, type, created_timestamp)
            VALUES
            (:deviceUuid, :name, :description, :dispensersSett, :dispenseSchedulesSett, :systemSett,
            :specialSystemSett, :phSensorSett, :ecSensorSett, :type, :createdTimestamp)
            """)
    void save(@Param("deviceUuid") String deviceUuid,
              @Param("name") String name,
              @Param("description") String description,
              @Param("dispensersSett") DispensersSettings dispensersSett,
              @Param("dispenseSchedulesSett") DispenseSchedulesSettings dispenseSchedulesSett,
              @Param("systemSett") SystemSettings systemSett,
              @Param("specialSystemSett") SpecialSystemSettings specialSystemSett,
              @Param("phSensorSett") PhSensorSettings phSensorSett,
              @Param("ecSensorSett") EcSensorSettings ecSensorSett,
              @Param("type") String type,
              @Param("createdTimestamp") LocalDateTime createdTimestamp);

    @Modifying
    @Query("""
            UPDATE public."HYDROPONIC_SETTINGS_V1"
            SET dispensers_sett = :dispensersSett, updated_timestamp = :updateTime
            WHERE device_uuid = :deviceUuid
            AND type = 'DISPENSERS_SETTINGS_TEMPLATE'
            """)
    void update(@Param("deviceUuid") String deviceUuid,
                @Param("dispensersSett") DispensersSettings dispensersSett,
                @Param("updateTime") LocalDateTime updateTime);

    @Modifying
    @Query("""
            UPDATE public."HYDROPONIC_SETTINGS_V1"
            SET dispense_schedules_sett = :dispenseSchedulesSett, updated_timestamp = :updateTime
            WHERE device_uuid = :deviceUuid
            AND type = 'DISPENSE_SCHEDULES_SETTINGS_TEMPLATE'
            """)
    void update(@Param("deviceUuid") String deviceUuid,
                @Param("dispenseSchedulesSett") DispenseSchedulesSettings dispenseSchedulesSett,
                @Param("updateTime") LocalDateTime updateTime);

    @Modifying
    @Query("""
            UPDATE public."HYDROPONIC_SETTINGS_V1"
            SET system_sett = :systemSett, updated_timestamp = :updateTime
            WHERE device_uuid = :deviceUuid
            AND type = 'SYSTEM_SETTINGS_TEMPLATE'
            """)
    void update(@Param("deviceUuid") String deviceUuid,
                @Param("systemSett") SystemSettings systemSett,
                @Param("updateTime") LocalDateTime updateTime);

    @Modifying
    @Query("""
            UPDATE public."HYDROPONIC_SETTINGS_V1"
            SET special_system_sett = :specialSystemSett, updated_timestamp = :updateTime
            WHERE device_uuid = :deviceUuid
            AND type = 'SPECIAL_SYSTEM_SETTINGS_TEMPLATE'
            """)
    void update(@Param("deviceUuid") String deviceUuid,
                @Param("specialSystemSett") SpecialSystemSettings specialSystemSettings,
                @Param("updateTime") LocalDateTime updateTime);


    @Modifying
    @Query("""
            UPDATE public."HYDROPONIC_SETTINGS_V1"
            SET ph_sensor_sett = :phSensorSett, updated_timestamp = :updateTime
            WHERE device_uuid = :deviceUuid
            AND type = 'PH_SENSOR_SETTINGS_TEMPLATE'
            """)
    void update(@Param("deviceUuid") String deviceUuid,
                @Param("phSensorSett") PhSensorSettings phSensorSett,
                @Param("updateTime") LocalDateTime updateTime);

    @Modifying
    @Query("""
            UPDATE public."HYDROPONIC_SETTINGS_V1"
            SET ec_sensor_sett = :ecSensorSett, updated_timestamp = :updateTime
            WHERE device_uuid = :deviceUuid
            AND type = 'EC_SENSOR_SETTINGS_TEMPLATE'
            """)
    void update(@Param("deviceUuid") String deviceUuid,
                @Param("ecSensorSett") EcSensorSettings ecSensorSett,
                @Param("updateTime") LocalDateTime updateTime);

    @Modifying
    @Query("""
            UPDATE public."HYDROPONIC_SETTINGS_V1"
            SET name = :name, description = :description, updated_timestamp = :updateTime
            WHERE device_uuid = :deviceUuid
            AND type = :type
            """)
    void update(@Param("deviceUuid") String deviceUuid,
                @Param("name") String name,
                @Param("description") String description,
                @Param("type") String type,
                @Param("updateTime") LocalDateTime updateTime);


    @Modifying
    @Query("""
            DELETE FROM public."HYDROPONIC_SETTINGS_V1"
            WHERE device_uuid = :uuid
            """)
    void deleteAll(@Param("uuid") String uuid);
}

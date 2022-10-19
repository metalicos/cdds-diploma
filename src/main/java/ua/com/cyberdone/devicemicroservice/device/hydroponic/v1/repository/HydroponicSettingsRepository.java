//package ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.repository;
//
//import org.springframework.data.jdbc.repository.query.Modifying;
//import org.springframework.data.jdbc.repository.query.Query;
//import org.springframework.data.repository.Repository;
//import org.springframework.data.repository.query.Param;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Optional;
//
//
//public interface HydroponicSettingsRepository extends Repository<HydroponicSettings, Long> {
//
//    @Query("""
//            SELECT * FROM public."HYDROPONIC_SETTINGS_V1"
//            WHERE device_uuid = :uuid
//            AND type = 'ACTIVE'
//            """)
//    Optional<HydroponicSettings> findActive(@Param("uuid") String uuid);
//
//    @Query("""
//            SELECT * FROM public."HYDROPONIC_SETTINGS_V1"
//            WHERE device_uuid = :uuid
//            AND type = :type
//            LIMIT :size OFFSET :from
//            """)
//    List<HydroponicSettings> findActive(@Param("uuid") String uuid,
//                                        @Param("from") Long from,
//                                        @Param("size") Long size,
//                                        @Param("type") String type);
//
//    @Modifying
//    @Query("""
//            INSERT INTO public."HYDROPONIC_SETTINGS_V1"
//            (device_uuid, name, description, JSON_ARRAY(dispensers_sett), to_json(dispense_schedules_sett), to_json(system_sett), to_json(special_system_sett),
//            to_json(ph_sensor_sett), to_json(ec_sensor_sett), type, created_timestamp)
//            VALUES
//            (:deviceUuid, :name, :description, :dispensersSett, :dispenseSchedulesSett, :systemSett,
//            :specialSystemSett, :phSensorSett, :ecSensorSett, :type, :createdTimestamp)
//            """)
//    void save(@Param("deviceUuid") String deviceUuid,
//              @Param("name") String name,
//              @Param("description") Object description,
//              @Param("dispensersSett") Object dispensersSett,
//              @Param("dispenseSchedulesSett") Object dispenseSchedulesSett,
//              @Param("systemSett") Object systemSett,
//              @Param("specialSystemSett") Object specialSystemSett,
//              @Param("phSensorSett") Object phSensorSett,
//              @Param("ecSensorSett") Object ecSensorSett,
//              @Param("type") Object type,
//              @Param("createdTimestamp") LocalDateTime createdTimestamp);
//
//    @Modifying
//    @Query("""
//            UPDATE public."HYDROPONIC_SETTINGS_V1"
//            SET name = :name, description = :description, dispensers_sett = :dispensersSett,
//            dispense_schedules_sett = :dispenseSchedulesSett, system_sett = :systemSett,
//            special_system_sett = :specialSystemSett, ph_sensor_sett = :phSensorSett, ec_sensor_sett = :ecSensorSett,
//            type = :type, updated_timestamp = :updatedTimestamp
//            WHERE device_uuid = :deviceUuid
//            AND type = :type
//            """)
//    void update(@Param("deviceUuid") String deviceUuid,
//                @Param("name") String name,
//                @Param("description") String description,
//                @Param("dispensersSett") Object dispensersSett,
//                @Param("dispenseSchedulesSett") Object dispenseSchedulesSett,
//                @Param("systemSett") Object systemSett,
//                @Param("specialSystemSett") Object specialSystemSett,
//                @Param("phSensorSett") Object phSensorSett,
//                @Param("ecSensorSett") Object ecSensorSett,
//                @Param("type") Object type,
//                @Param("updatedTimestamp") LocalDateTime updatedTimestamp);
//
//    @Modifying
//    @Query("""
//            DELETE FROM public."HYDROPONIC_SETTINGS_V1"
//            WHERE device_uuid = :uuid
//            """)
//    void deleteAll(@Param("uuid") String uuid);
//}

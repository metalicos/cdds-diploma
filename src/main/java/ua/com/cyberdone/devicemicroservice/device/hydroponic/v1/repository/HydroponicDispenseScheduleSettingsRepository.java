//package ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.repository;
//
//import org.springframework.data.jdbc.repository.query.Modifying;
//import org.springframework.data.jdbc.repository.query.Query;
//import org.springframework.data.repository.Repository;
//import org.springframework.data.repository.query.Param;
//import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.entity.HydroponicDispenseScheduleSettings;
//import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.entity.HydroponicDispenserSettings;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Optional;
//
//
//public interface HydroponicDispenseScheduleSettingsRepository extends Repository<HydroponicDispenseScheduleSettings, Long> {
//
//    @Query("""
//            SELECT * FROM public."HYDROPONIC_DISPENSER_SETTINGS_V1" AS hds
//            INNER JOIN public."HYDROPONIC_SETTING_DETAILS_V1" AS hsd
//            ON hds."setting_detail_id" = hsd."id"
//            WHERE hds."device_uuid" = :device_uuid
//            AND hsd."setting_type" = 'ACTIVE'
//            """)
//    Optional<HydroponicDispenseScheduleSettings> findActive(@Param("device_uuid") String deviceUuid);
//
//    @Query("""
//            SELECT * FROM public."HYDROPONIC_DISPENSER_SETTINGS_V1" AS hds
//            INNER JOIN public."HYDROPONIC_SETTING_DETAILS_V1" AS hsd
//            ON hds."setting_detail_id" = hsd."id"
//            WHERE "device_uuid" = :device_uuid
//            AND hsd."setting_type" = :type
//            LIMIT :size OFFSET :from
//            """)
//    List<HydroponicDispenseScheduleSettings> find(@Param("device_uuid") String deviceUuid,
//                                                 @Param("from") Long from,
//                                                 @Param("size") Long size,
//                                                 @Param("type") String type);
//
//    @Modifying
//    @Query("""
//            INSERT INTO public."HYDROPONIC_DISPENSER_SETTINGS_V1"
//            ("setting_detail_id","device_uuid","index","time","dispenser_name","pin_a","regulation_direction",
//            "enable","polarity","smart_dose","total_added_ml","ml_per_ms","target_value",
//            "error","recheck_dispenser_after_seconds","last_dispenser_recheck_time",
//            "mixing_volume_ml","created_timestamp","updated_timestamp")
//            VALUES
//            (:setting_detail_id,:device_uuid,:index,:time,:dispenser_name,:pin_a,:regulation_direction,
//            :enable,:polarity,:smart_dose,:total_added_ml,:ml_per_ms,:target_value,
//            :error,:recheck_dispenser_after_seconds,:last_dispenser_recheck_time,
//            :mixing_volume_ml,:created_timestamp,:updated_timestamp)
//            """)
//    void save(@Param("setting_detail_id") Long settingDetailId,
//              @Param("device_uuid") String deviceUuid,
//              @Param("index") Long index,
//              @Param("time") LocalDateTime time,
//              @Param("dispenser_name") String dispenserName,
//              @Param("pin_a") String pinA,
//              @Param("regulation_direction") Boolean regulationDirection,
//              @Param("enable") Boolean enable,
//              @Param("polarity") Boolean polarity,
//              @Param("smart_dose") Boolean smartDose,
//              @Param("total_added_ml") Double totalAddedMl,
//              @Param("ml_per_ms") Double mlPerMl,
//              @Param("target_value") Double targetValue,
//              @Param("error") Double error,
//              @Param("recheck_dispenser_after_seconds") Integer recheckDispenserAfterSeconds,
//              @Param("last_dispenser_recheck_time") Integer lastDispenserRecheckTime,
//              @Param("mixing_volume_ml") Integer mixingVolumeMl,
//              @Param("created_timestamp") LocalDateTime createdTimestamp,
//              @Param("updated_timestamp") LocalDateTime updatedTimestamp);
//
//    @Modifying
//    @Query("""
//            UPDATE public."HYDROPONIC_DISPENSER_SETTINGS_V1" AS hds
//            INNER JOIN public."HYDROPONIC_SETTING_DETAILS_V1" AS hsd
//            ON hds."setting_detail_id" = hsd."id"
//            SET hds."setting_detail_id" = :setting_detail_id,
//            hds."index" = :index,
//            hds."time" = :time,
//            hds."dispenser_name" = :dispenser_name,
//            hds."pin_a" = :pin_a,
//            hds."regulation_direction" = :regulation_direction,
//            hds."enable" = :enable,
//            hds."polarity" = :polarity,
//            hds."smart_dose" = :smart_dose,
//            hds."total_added_ml" = :total_added_ml,
//            hds."ml_per_ms" = :ml_per_ms,
//            hds."target_value" = :target_value,
//            hds."error" = :error,
//            hds."recheck_dispenser_after_seconds" = :recheck_dispenser_after_seconds,
//            hds."last_dispenser_recheck_time" = :last_dispenser_recheck_time,
//            hds."mixing_volume_ml" = :mixing_volume_ml,
//            hds."created_timestamp" = :created_timestamp,
//            hds."updated_timestamp" = :updated_timestamp
//            WHERE hds."device_uuid" = :device_uuid
//            AND hsd."setting_type" = :type
//            """)
//    void update(@Param("setting_detail_id") Long settingDetailId,
//                @Param("device_uuid") String deviceUuid,
//                @Param("index") Long index,
//                @Param("time") LocalDateTime time,
//                @Param("dispenser_name") String dispenserName,
//                @Param("pin_a") String pinA,
//                @Param("regulation_direction") Boolean regulationDirection,
//                @Param("enable") Boolean enable,
//                @Param("polarity") Boolean polarity,
//                @Param("smart_dose") Boolean smartDose,
//                @Param("total_added_ml") Double totalAddedMl,
//                @Param("ml_per_ms") Double mlPerMl,
//                @Param("target_value") Double targetValue,
//                @Param("error") Double error,
//                @Param("recheck_dispenser_after_seconds") Integer recheckDispenserAfterSeconds,
//                @Param("last_dispenser_recheck_time") Integer lastDispenserRecheckTime,
//                @Param("mixing_volume_ml") Integer mixingVolumeMl,
//                @Param("created_timestamp") LocalDateTime createdTimestamp,
//                @Param("updated_timestamp") LocalDateTime updatedTimestamp,
//                @Param("type") String type);
//
//
//    @Modifying
//    @Query("""
//            DELETE hds FROM public."HYDROPONIC_DISPENSER_SETTINGS_V1" AS hds
//            INNER JOIN public."HYDROPONIC_SETTING_DETAILS_V1" AS hsd
//            ON hds."setting_detail_id" = hsd."id"
//            WHERE hds."device_uuid" = :device_uuid
//            AND hsd."setting_type" = :type
//            """)
//    void delete(@Param("device_uuid") String deviceUuid,
//                @Param("type") String type);
//
//    @Modifying
//    @Query("""
//            DELETE hds FROM public."HYDROPONIC_DISPENSER_SETTINGS_V1" AS hds
//            INNER JOIN public."HYDROPONIC_SETTING_DETAILS_V1" AS hsd
//            ON hds."setting_detail_id" = hsd."id"
//            WHERE hds."device_uuid" = :device_uuid
//            AND hsd."setting_type" = :type
//            AND hsd."name" = :name
//            """)
//    void delete(@Param("device_uuid") String deviceUuid,
//                @Param("type") String type,
//                @Param("name") String name);
//}

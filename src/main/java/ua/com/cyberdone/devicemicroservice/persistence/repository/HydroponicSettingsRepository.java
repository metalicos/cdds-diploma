package ua.com.cyberdone.devicemicroservice.persistence.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.com.cyberdone.devicemicroservice.persistence.entity.hydroponic.HydroponicSettings;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface HydroponicSettingsRepository extends JpaRepository<HydroponicSettings, Long> {

    @Query("select s from HydroponicSettings s where s.uuid = :uuid order by s.createdTimestamp desc ")
    List<HydroponicSettings> findLastSettings(@Param("uuid") String uuid, Pageable pageable);

    Optional<HydroponicSettings> getByUuid(String uuid);

    @Modifying
    @Transactional
    @Query(value = "UPDATE hydroponic_settings s SET ph_value = :phValue, temperature_value = :temperatureValue, " +
            "tds_value = :tdsValue, setup_ph_value = :setupPhValue, setup_tds_value = :setupTdsValue, " +
            "regulate_error_ph = :regulateErrorPh, regulate_error_fertilizer = :regulateErrorFertilizer, " +
            "ml_per_millisecond = :mlPerMillisecond, ph_up_dose_ml = :phUpDoseMl, ph_down_dose_ml = :phDownDoseMl, " +
            "fertilizer_dose_ml = :fertilizerDoseMl, recheck_dispensers_after_ms = :recheckDispensersAfterMs, " +
            "restart_counter = :restartCounter, dispensers_enable = :dispensersEnable, sensors_enable = :sensorsEnable, " +
            "autotime = :autotime, time_zone = :timeZone, wifi_ssid = :wifiSsid, wifi_pass = :wifiPass, " +
            "microcontroller_time = :microcontrollerTime, updated_timestamp = :updatedTimestamp " +
            "WHERE uuid = :uuid;", nativeQuery = true)
    void updateHydroponicSettings(
            @Param("uuid") String uuid,
            @Param("setupPhValue") Double setupPhValue, @Param("setupTdsValue") Long setupTdsValue,
            @Param("regulateErrorPh") Double regulateErrorPh,
            @Param("regulateErrorFertilizer") Double regulateErrorFertilizer,
            @Param("mlPerMillisecond") Double mlPerMillisecond, @Param("phUpDoseMl") Double phUpDoseMl,
            @Param("phDownDoseMl") Double phDownDoseMl, @Param("fertilizerDoseMl") Double fertilizerDoseMl,
            @Param("recheckDispensersAfterMs") Long recheckDispensersAfterMs,
            @Param("restartCounter") Long restartCounter,
            @Param("dispensersEnable") Boolean dispensersEnable, @Param("sensorsEnable") Boolean sensorsEnable,
            @Param("autotime") Boolean autotime, @Param("timeZone") String timeZone,
            @Param("wifiSsid") String wifiSsid, @Param("wifiPass") String wifiPass,
            @Param("microcontrollerTime") LocalDateTime microcontrollerTime,
            @Param("updatedTimestamp") LocalDateTime updatedTimestamp);
}
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

    @Query("select s from HydroponicSettings s where s.deviceMetadata.uuid = :uuid order by s.createdTimestamp desc ")
    List<HydroponicSettings> findLastSettings(@Param("uuid") String uuid, Pageable pageable);

    @Query("select s from HydroponicSettings s where s.deviceMetadata.uuid = :uuid")
    Optional<HydroponicSettings> getByUuid(@Param("uuid") String uuid);

    @Modifying
    @Transactional
    @Query("UPDATE HydroponicSettings s SET s.setupPhValue = :setupPhValue, s.setupTdsValue = :setupTdsValue, " +
            "s.regulateErrorPh = :regulateErrorPh, s.regulateErrorFertilizer = :regulateErrorFertilizer, " +
            "s.mlPerMillisecond = :mlPerMillisecond, s.phUpDoseMl = :phUpDoseMl, s.phDownDoseMl = :phDownDoseMl, " +
            "s.fertilizerDoseMl = :fertilizerDoseMl, s.recheckDispensersAfterMs = :recheckDispensersAfterMs, " +
            "s.restartCounter = :restartCounter, s.dispensersEnable = :dispensersEnable, s.sensorsEnable = :sensorsEnable, " +
            "s.autotime = :autotime, s.timeZone = :timeZone, s.wifiSsid = :wifiSsid, s.wifiPass = :wifiPass, " +
            "s.microcontrollerTime = :microcontrollerTime, s.updatedTimestamp = :updatedTimestamp " +
            "WHERE s.deviceMetadata.uuid = :uuid")
    void updateHydroponicSettings(
            @Param("uuid") String uuid,
            @Param("setupPhValue") Double setupPhValue, @Param("setupTdsValue") Long setupTdsValue,
            @Param("regulateErrorPh") Double regulateErrorPh,
            @Param("regulateErrorFertilizer") Double regulateErrorFertilizer,
            @Param("mlPerMillisecond") Double mlPerMillisecond,
            @Param("phUpDoseMl") Double phUpDoseMl,
            @Param("phDownDoseMl") Double phDownDoseMl,
            @Param("fertilizerDoseMl") Double fertilizerDoseMl,
            @Param("recheckDispensersAfterMs") Long recheckDispensersAfterMs,
            @Param("restartCounter") Long restartCounter,
            @Param("dispensersEnable") Boolean dispensersEnable,
            @Param("sensorsEnable") Boolean sensorsEnable,
            @Param("autotime") Boolean autotime,
            @Param("timeZone") String timeZone,
            @Param("wifiSsid") String wifiSsid,
            @Param("wifiPass") String wifiPass,
            @Param("microcontrollerTime") LocalDateTime microcontrollerTime,
            @Param("updatedTimestamp") LocalDateTime updatedTimestamp);
}
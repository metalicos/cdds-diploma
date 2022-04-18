package ua.com.cyberdone.devicemicroservice.persistence.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.com.cyberdone.devicemicroservice.persistence.entity.hydroponic.HydroponicSettingTemplate;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface HydroponicSettingTemplateRepository extends JpaRepository<HydroponicSettingTemplate, Long> {

    List<HydroponicSettingTemplate> findAllByUserId(Long userId, Pageable pageable);

    Optional<HydroponicSettingTemplate> findById(Long userId);

    @Modifying
    @Transactional
    @Query("UPDATE HydroponicSettingTemplate t SET t.name = :name, t.description = :description, " +
            "t.setupPhValue = :setupPhValue, t.setupTdsValue = :setupTdsValue, " +
            "t.regulateErrorPh = :regulateErrorPh, t.regulateErrorFertilizer = :regulateErrorFertilizer, " +
            "t.mlPerMillisecond = :mlPerMillisecond, t.phUpDoseMl = :phUpDoseMl, t.phDownDoseMl = :phDownDoseMl, " +
            "t.fertilizerDoseMl = :fertilizerDoseMl, t.recheckDispensersAfterMs = :recheckDispensersAfterMs, " +
            "t.restartCounter = :restartCounter, t.dispensersEnable = :dispensersEnable, t.sensorsEnable = :sensorsEnable, " +
            "t.autotime = :autotime, t.timeZone = :timeZone, t.wifiSsid = :wifiSsid, t.wifiPass = :wifiPass, " +
            "t.microcontrollerTime = :microcontrollerTime, t.updatedTimestamp = :updatedTimestamp " +
            "WHERE t.userId = :userId")
    void updateHydroponicSettings(
            @Param("userId") Long userId,
            @Param("name") String name,
            @Param("description") String description,
            @Param("setupPhValue") Double setupPhValue,
            @Param("setupTdsValue") Long setupTdsValue,
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
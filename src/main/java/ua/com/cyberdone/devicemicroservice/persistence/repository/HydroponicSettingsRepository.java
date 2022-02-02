package ua.com.cyberdone.devicemicroservice.persistence.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.com.cyberdone.devicemicroservice.persistence.entity.hydroponic.HydroponicSettings;

import java.util.List;

public interface HydroponicSettingsRepository extends JpaRepository<HydroponicSettings, Long> {

    @Query("select s from HydroponicSettings s where s.uuid = :uuid order by s.createdTimestamp desc ")
    List<HydroponicSettings> findLastSettings(@Param("uuid") String uuid,
                                              Pageable pageable);
}
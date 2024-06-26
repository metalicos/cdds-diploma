package ua.com.cyberdone.devicemicroservice.persistence.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.com.cyberdone.devicemicroservice.persistence.entity.hydroponic.HydroponicCalibrationData;

import java.util.List;

public interface HydroponicCalibrationDataRepository extends JpaRepository<HydroponicCalibrationData, Long> {

    @Query("select d from HydroponicCalibrationData d where d.deviceMetadata.uuid = :uuid order by d.createdTimestamp desc ")
    List<HydroponicCalibrationData> findLastData(@Param("uuid") String uuid, Pageable pageable);

    @Modifying
    @Query("delete from HydroponicCalibrationData data where data.deviceMetadata.uuid = :uuid")
    void deleteAllDataForUuid(@Param("uuid") String uuid);
}

package ua.com.cyberdone.devicemicroservice.persistence.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.com.cyberdone.devicemicroservice.persistence.entity.hydroponic.HydroponicData;

import java.time.LocalDateTime;

@Repository
public interface HydroponicDataStatisticsRepository extends JpaRepository<HydroponicData, Long> {

    @Query("SELECT MAX(p.phValue) FROM HydroponicData p WHERE p.uuid = :uuid " +
            "AND p.microcontrollerTime > :fromT AND p.microcontrollerTime < :toT")
    Double maxPhByUuid(@Param("uuid") String uuid,
                       @Param("fromT") LocalDateTime from,
                       @Param("toT") LocalDateTime to);

    @Query("SELECT MAX(p.temperatureValue) FROM HydroponicData p WHERE p.uuid = :uuid " +
            "AND p.microcontrollerTime > :fromT AND p.microcontrollerTime < :toT")
    Double maxTemperatureByUuid(@Param("uuid") String uuid,
                                @Param("fromT") LocalDateTime from,
                                @Param("toT") LocalDateTime to);

    @Query("SELECT MAX(p.tdsValue) FROM HydroponicData p WHERE p.uuid = :uuid " +
            "AND p.microcontrollerTime > :fromT AND p.microcontrollerTime < :toT")
    Double maxTdsByUuid(@Param("uuid") String uuid,
                        @Param("fromT") LocalDateTime from,
                        @Param("toT") LocalDateTime to);


    @Query("SELECT MIN(p.phValue) FROM HydroponicData p WHERE p.uuid = :uuid " +
            "AND p.microcontrollerTime > :fromT AND p.microcontrollerTime < :toT")
    Double minPhByUuid(@Param("uuid") String uuid,
                       @Param("fromT") LocalDateTime from,
                       @Param("toT") LocalDateTime to);

    @Query("SELECT MIN(p.temperatureValue) FROM HydroponicData p WHERE p.uuid = :uuid " +
            "AND p.microcontrollerTime > :fromT AND p.microcontrollerTime < :toT")
    Double minTemperatureByUuid(@Param("uuid") String uuid,
                                @Param("fromT") LocalDateTime from,
                                @Param("toT") LocalDateTime to);

    @Query("SELECT MIN(p.tdsValue) FROM HydroponicData p WHERE p.uuid = :uuid " +
            "AND p.microcontrollerTime > :fromT AND p.microcontrollerTime < :toT")
    Double minTdsByUuid(@Param("uuid") String uuid,
                        @Param("fromT") LocalDateTime from,
                        @Param("toT") LocalDateTime to);

    @Query("SELECT AVG(p.phValue) FROM HydroponicData p WHERE p.uuid = :uuid")
    Double averagePhByUuid(@Param("uuid") String uuid);

    @Query("SELECT AVG(p.temperatureValue) FROM HydroponicData p WHERE p.uuid = :uuid")
    Double averageTemperatureByUuid(@Param("uuid") String uuid);

    @Query("SELECT AVG(p.tdsValue) FROM HydroponicData p WHERE p.uuid = :uuid")
    Double averageTdsByUuid(@Param("uuid") String uuid);
}

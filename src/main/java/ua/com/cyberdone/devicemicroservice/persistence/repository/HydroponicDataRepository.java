package ua.com.cyberdone.devicemicroservice.persistence.repository;


import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.com.cyberdone.devicemicroservice.persistence.entity.hydroponic.HydroponicData;

import java.util.List;

@Repository
public interface HydroponicDataRepository extends JpaRepository<HydroponicData, Long> {

    @Query("select d from HydroponicData d where d.deviceMetadata.uuid = :uuid order by d.createdTimestamp desc ")
    List<HydroponicData> findLastData(@Param("uuid") String uuid, Pageable pageable);

    @Query(value = """
            SELECT DISTINCT
                d.id AS id,
                d.uuid AS uuid,
                ROUND(AVG(d.ph_value), 2) AS ph_value,
                ROUND(AVG(d.temperature_value), 2) AS temperature_value,
                ROUND(AVG(d.tds_value), 0) AS tds_value,
                d.is_dispenser_ph_up_open AS is_dispenser_ph_up_open,
                d.is_dispenser_ph_down_open AS is_dispenser_ph_down_open,
                d.is_dispenser_tds_open AS is_dispenser_tds_open,
                DATE_FORMAT(CONCAT(DATE(d.microcontroller_time),
                        ' ',
                        HOUR(d.microcontroller_time),
                        ':',
                        ROUND(MINUTE(d.microcontroller_time) / (:stepMinutes), 0) * (:stepMinutes),
                        ':',
                        '00',
                        '.',
                        '000000'),
                        '%Y-%m-%d %T.000000') AS microcontroller_time,
                 DATE_FORMAT(CONCAT(DATE(d.created_timestamp),
                        ' ',
                        HOUR(d.created_timestamp),
                        ':',
                        ROUND(MINUTE(d.created_timestamp) / (:stepMinutes), 0) * (:stepMinutes),
                        ':',
                        '00',
                        '.',
                        '000000'),
                        '%Y-%m-%d %T.000000') AS created_timestamp,
                 DATE_FORMAT(CONCAT(DATE(d.updated_timestamp),
                        ' ',
                        HOUR(d.updated_timestamp),
                        ':',
                        ROUND(MINUTE(d.updated_timestamp) / (:stepMinutes), 0) * (:stepMinutes),
                        ':',
                        '00',
                        '.',
                        '000000'),
                        '%Y-%m-%d %T.000000') AS updated_timestamp
            FROM
                cdds.hydroponic_data d
            WHERE
                ROUND(ROUND(MINUTE(d.created_timestamp) / (:stepMinutes), 0) * (:stepMinutes), 0) != 60 AND
                ROUND(ROUND(MINUTE(d.updated_timestamp) / (:stepMinutes), 0) * (:stepMinutes), 0) != 60 AND
                ROUND(ROUND(MINUTE(d.microcontroller_time) / (:stepMinutes), 0) * (:stepMinutes), 0) != 60
                        AND d.created_timestamp > :fromDate AND d.created_timestamp < :toDate
                        AND d.uuid = :uuid
            GROUP BY DATE(d.created_timestamp) , HOUR(d.created_timestamp) , ROUND(MINUTE(d.created_timestamp) / (:stepMinutes), 0) * (:stepMinutes);
            """, nativeQuery = true)
    List<HydroponicData> findDataInRageWithStep(
            @Param("uuid") String uuid,
            @Param("fromDate") String fromDate,
            @Param("toDate") String toDate,
            @Param("stepMinutes") Integer stepMinutes
    );

    @Modifying
    @Query("delete from HydroponicData data where data.deviceMetadata.uuid = :uuid")
    void deleteAllDataForUuid(@Param("uuid") String uuid);
}

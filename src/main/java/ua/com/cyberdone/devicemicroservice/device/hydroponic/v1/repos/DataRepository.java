package ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.repos;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.domain.Data;

import java.util.List;


public interface DataRepository extends JpaRepository<Data, Long> {

    @Query("select data from Data data where data.device.uuid = :uuid")
    List<Data> findAllByDeviceUuid(@Param("uuid") String uuid, Pageable pageable);

    @Query(value = """
            SELECT DISTINCT
                d.device_uuid AS id,
                ROUND(AVG(d.ec_solution), 2) AS ec_solution,
                ROUND(AVG(d.ph_solution), 2) AS ph_solution,
                ROUND(AVG(d.t_solution), 0) AS t_solution,
                ROUND(AVG(d.t_air), 0) AS t_air,
                ROUND(AVG(d.humidity_air), 0) AS humidity_air,
                ROUND(AVG(d.atmospheric_pressure), 0) AS atmospheric_pressure,
                 DATE_FORMAT(CONCAT(DATE(d.created_timestamp),
                        ' ',
                        HOUR(d.created_timestamp),
                        ':',
                        ROUND(MINUTE(d.created_timestamp) / (:stepMinutes), 0) * (:stepMinutes),
                        ':',
                        '00',
                        '.',
                        '000000'),
                        '%Y-%m-%d %T.000000') AS created_timestamp
            FROM
                "hydroponic_v1".data AS d
            WHERE
                ROUND(ROUND(MINUTE(d.created_timestamp) / (:stepMinutes), 0) * (:stepMinutes), 0) != 60
                        AND d.created_timestamp > :fromDate AND d.created_timestamp < :toDate
                        AND d.device_uuid = :uuid
            GROUP BY DATE(d.created_timestamp), HOUR(d.created_timestamp), ROUND(MINUTE(d.created_timestamp) / (:stepMinutes), 0) * (:stepMinutes);
            """, nativeQuery = true)
    List<Data> findAllInRange(@Param("uuid") String uuid,
                              @Param("fromDate") String fromDate,
                              @Param("toDate") String toDate,
                              @Param("stepMinutes") int dataStep);
}

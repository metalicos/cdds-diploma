package ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.repository;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.entity.HydroponicData;

import java.time.LocalDateTime;
import java.util.List;


public interface HydroponicDataRepository extends Repository<HydroponicData, Long> {

    @Query("""
            SELECT * FROM public.HYDROPONIC_DATA_V1
            WHERE device_uuid = :uuid LIMIT :size OFFSET :from
            """)
    List<HydroponicData> findAllByUuid(@Param("uuid") String uuid,
                                       @Param("from") Long from,
                                       @Param("size") Long size);

    @Modifying
    @Query("""
            INSERT INTO public.HYDROPONIC_DATA_V1
            (device_uuid, ec_solution, ph_solution, t_solution, t_air, humidity_air, atmospheric_pressure, created_timestamp)
            VALUES
            (:deviceUuid, :ecSolution, :phSolution, :tSolution, :tAir, :humidityAir, :atmosphericPressure, :createdTimestamp)
            """)
    void save(@Param("deviceUuid") String deviceUuid,
              @Param("ecSolution") Double ecSolution,
              @Param("phSolution") Double phSolution,
              @Param("tSolution") Double tSolution,
              @Param("tAir") Double tAir,
              @Param("humidityAir") Double humidityAir,
              @Param("atmosphericPressure") Double atmosphericPressure,
              @Param("createdTimestamp") LocalDateTime createdTimestamp);

    @Modifying
    @Query("""
            DELETE FROM public.HYDROPONIC_DATA_V1
            WHERE device_uuid = :uuid
            """)
    void deleteAllByUuid(@Param("uuid") String uuid);

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
                public.HYDROPONIC_DATA_V1 AS d
            WHERE
                ROUND(ROUND(MINUTE(d.created_timestamp) / (:stepMinutes), 0) * (:stepMinutes), 0) != 60
                        AND d.created_timestamp > :fromDate AND d.created_timestamp < :toDate
                        AND d.uuid = :uuid
            GROUP BY DATE(d.created_timestamp) , HOUR(d.created_timestamp) , ROUND(MINUTE(d.created_timestamp) / (:stepMinutes), 0) * (:stepMinutes);
            """)
    List<HydroponicData> findAllInRange(@Param("uuid") String uuid,
                                        @Param("fromDate") String fromDate,
                                        @Param("toDate") String toDate,
                                        @Param("stepMinutes") int dataStep);
}
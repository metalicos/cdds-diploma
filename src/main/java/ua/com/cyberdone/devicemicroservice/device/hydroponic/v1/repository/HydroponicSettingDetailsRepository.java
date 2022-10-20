package ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.entity.HydroponicSettingDetails;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public interface HydroponicSettingDetailsRepository extends CrudRepository<HydroponicSettingDetails, Long> {

    @Query("""
            SELECT * FROM public."HYDROPONIC_SETTING_DETAILS_V1" AS hsd
            WHERE hsd."id" = :id
            AND hsd."setting_type" = 'ACTIVE'
            """)
    Optional<HydroponicSettingDetails> findActive(@Param("id") Long id);

    @Query("""
            SELECT * FROM public."HYDROPONIC_SETTING_DETAILS_V1" AS hsd
            WHERE hsd."id" = :id
            """)
    Optional<HydroponicSettingDetails> find(@Param("id") Long id);

    @Query("""
            SELECT * FROM public."HYDROPONIC_SETTING_DETAILS_V1" AS hsd
            WHERE hsd."setting_type" = :setting_type
            LIMIT :size OFFSET :from
            """)
    List<HydroponicSettingDetails> find(
            @Param("setting_type") String settingType,
            @Param("from") Long from,
            @Param("size") Long size);
}

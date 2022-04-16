package ua.com.cyberdone.devicemicroservice.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.com.cyberdone.devicemicroservice.persistence.entity.RegularSchedule;

import java.util.List;

public interface RegularScheduleRepository extends JpaRepository<RegularSchedule, Long> {

    @Query("from RegularSchedule s where s.monday = :mon or s.tuesday = :tue or s.wednesday = :wed " +
            "or s.thursday = :thu or  s.friday = :fri or s.saturday = :sat or s.sunday = :sun")
    List<RegularSchedule> findByDays(@Param("mon") Boolean mon, @Param("tue") Boolean tue,
                                     @Param("wed") Boolean wed, @Param("thu") Boolean thu,
                                     @Param("fri") Boolean fri, @Param("sat") Boolean sat,
                                     @Param("sun") Boolean sun);

    @Query("from RegularSchedule s where s.deviceMetadata.uuid = :uuid and s.key = :key")
    List<RegularSchedule> findAllByUuidAndKey(@Param("uuid") String uuid, @Param("key") String key);
}
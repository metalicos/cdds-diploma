package ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.domain.DispenseSchedule;

import java.util.Optional;


public interface DispenseScheduleRepository extends JpaRepository<DispenseSchedule, Long> {

    @Query("select d from DispenseSchedule d where d.device.uuid = ?1")
    Optional<DispenseSchedule> findByDevice(String deviceUuid);
}

package ua.com.cyberdone.devicemicroservice.device.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.cyberdone.devicemicroservice.device.domain.DispenseSchedule;


public interface DispenseScheduleRepository extends JpaRepository<DispenseSchedule, Long> {
}

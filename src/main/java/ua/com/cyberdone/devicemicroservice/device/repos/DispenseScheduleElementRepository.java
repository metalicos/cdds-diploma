package ua.com.cyberdone.devicemicroservice.device.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.cyberdone.devicemicroservice.device.domain.DispenseScheduleElement;


public interface DispenseScheduleElementRepository extends JpaRepository<DispenseScheduleElement, Long> {
}

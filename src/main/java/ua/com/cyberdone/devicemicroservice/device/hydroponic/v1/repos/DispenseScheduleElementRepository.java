package ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.domain.DispenseScheduleElement;


public interface DispenseScheduleElementRepository extends JpaRepository<DispenseScheduleElement, Long> {
}

package ua.com.cyberdone.devicemicroservice.device.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.cyberdone.devicemicroservice.device.domain.PhSensorTemplate;


public interface PhSensorTemplateRepository extends JpaRepository<PhSensorTemplate, Long> {
}

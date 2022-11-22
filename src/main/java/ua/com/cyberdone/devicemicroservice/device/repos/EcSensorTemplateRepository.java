package ua.com.cyberdone.devicemicroservice.device.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.cyberdone.devicemicroservice.device.domain.EcSensorTemplate;


public interface EcSensorTemplateRepository extends JpaRepository<EcSensorTemplate, Long> {
}

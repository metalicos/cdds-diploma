package ua.com.cyberdone.devicemicroservice.device.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.cyberdone.devicemicroservice.device.domain.EcSensor;


public interface EcSensorRepository extends JpaRepository<EcSensor, Long> {
}

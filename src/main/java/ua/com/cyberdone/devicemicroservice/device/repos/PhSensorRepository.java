package ua.com.cyberdone.devicemicroservice.device.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.cyberdone.devicemicroservice.device.domain.PhSensor;


public interface PhSensorRepository extends JpaRepository<PhSensor, Long> {
}

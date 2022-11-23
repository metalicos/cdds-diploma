package ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.domain.PhSensorTemplate;

import java.awt.print.Pageable;
import java.util.List;


public interface PhSensorTemplateRepository extends JpaRepository<PhSensorTemplate, Long> {
    List<PhSensorTemplate> findAllByOwnerId(Long ownerId, Pageable pageable);
}

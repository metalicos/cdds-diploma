package ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.repos;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.domain.EcSensorTemplate;

import java.util.List;


public interface EcSensorTemplateRepository extends JpaRepository<EcSensorTemplate, Long> {
    List<EcSensorTemplate> findAllByOwnerId(Long ownerId, Pageable pageable);
}

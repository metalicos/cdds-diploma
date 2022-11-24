package ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.domain.PhSensorPhSensorTemplate;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.domain.embeddable.PhSensorPhSensorTemplateId;

import java.util.Optional;


public interface PhSensorPhSensorTemplateRepository extends JpaRepository<PhSensorPhSensorTemplate, PhSensorPhSensorTemplateId> {

    @Query("from PhSensorPhSensorTemplate t where t.phSensor.id = ?1 and t.phSensorTemplate.id = ?2")
    Optional<PhSensorPhSensorTemplate> findByPhSensorIdAndPhSensorTemplateId(Long phSensorId, Long phSensorTemplateId);

}

package ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.domain.EcSensorEcSensorTemplate;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.domain.embeddable.EcSensorEcSensorTemplateId;

import java.util.Optional;


public interface EcSensorEcSensorTemplateRepository extends JpaRepository<EcSensorEcSensorTemplate, EcSensorEcSensorTemplateId> {

    @Query("from EcSensorEcSensorTemplate t where t.ecSensor.id = ?1 and t.ecSensorTemplate.id = ?2")
    Optional<EcSensorEcSensorTemplate> findByEcSensorIdAndEcSensorTemplateId(Long ecSensorId, Long ecSensorTemplateId);

}

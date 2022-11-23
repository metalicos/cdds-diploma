package ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.domain.EcSensor;

import java.util.Optional;


public interface EcSensorRepository extends JpaRepository<EcSensor, Long> {
    @Query("select ec from EcSensor ec where ec.device.uuid = ?1")
    Optional<EcSensor> findByDeviceUuid(String uuid);
}

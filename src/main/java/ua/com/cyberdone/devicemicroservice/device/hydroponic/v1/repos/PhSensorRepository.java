package ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.domain.PhSensor;

import java.util.Optional;


public interface PhSensorRepository extends JpaRepository<PhSensor, Long> {
    @Query("select ph from PhSensor ph where ph.device.uuid = ?1")
    Optional<PhSensor> findByDeviceUuid(String uuid);
}

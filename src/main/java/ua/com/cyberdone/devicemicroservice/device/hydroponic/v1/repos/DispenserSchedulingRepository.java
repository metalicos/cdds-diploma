package ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.domain.DispenserScheduling;


public interface DispenserSchedulingRepository extends JpaRepository<DispenserScheduling, Long> {
}

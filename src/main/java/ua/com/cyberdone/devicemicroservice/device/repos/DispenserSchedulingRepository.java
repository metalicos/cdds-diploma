package ua.com.cyberdone.devicemicroservice.device.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.cyberdone.devicemicroservice.device.domain.DispenserScheduling;


public interface DispenserSchedulingRepository extends JpaRepository<DispenserScheduling, Long> {
}

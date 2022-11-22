package ua.com.cyberdone.devicemicroservice.device.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.cyberdone.devicemicroservice.device.domain.Dispenser;


public interface DispenserRepository extends JpaRepository<Dispenser, Long> {
}

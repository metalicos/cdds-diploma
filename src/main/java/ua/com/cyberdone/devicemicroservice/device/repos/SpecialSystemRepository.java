package ua.com.cyberdone.devicemicroservice.device.repos;


import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.cyberdone.devicemicroservice.device.domain.SpecialSystem;

public interface SpecialSystemRepository extends JpaRepository<SpecialSystem, Long> {
}

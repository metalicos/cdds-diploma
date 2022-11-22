package ua.com.cyberdone.devicemicroservice.device.repos;


import org.springframework.data.jpa.repository.JpaRepository;

public interface SystemRepository extends JpaRepository<ua.com.cyberdone.devicemicroservice.device.domain.System, Long> {
}

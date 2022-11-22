package ua.com.cyberdone.devicemicroservice.device.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.cyberdone.devicemicroservice.device.domain.SystemTemplate;


public interface SystemTemplateRepository extends JpaRepository<SystemTemplate, Long> {
}

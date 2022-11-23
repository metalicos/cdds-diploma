package ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.repos;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.domain.SystemTemplate;

import java.util.List;


public interface SystemTemplateRepository extends JpaRepository<SystemTemplate, Long> {
    List<SystemTemplate> findAllByOwnerId(Long ownerId, Pageable pageable);
}

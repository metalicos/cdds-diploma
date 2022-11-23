package ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.domain.DispenserTemplate;

import java.awt.print.Pageable;
import java.util.List;


public interface DispenserTemplateRepository extends JpaRepository<DispenserTemplate, Long> {

    List<DispenserTemplate> findAllByOwnerId(Long ownerId, Pageable pageable);
}

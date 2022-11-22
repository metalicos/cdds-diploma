package ua.com.cyberdone.devicemicroservice.device.repos;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.cyberdone.devicemicroservice.device.domain.DispenserTemplate;

import java.awt.print.Pageable;
import java.util.List;


public interface DispenserTemplateRepository extends JpaRepository<DispenserTemplate, Long> {

    List<DispenserTemplate> findAllByOwnerId(Long ownerId, Pageable pageable);
}

package ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.domain.DispenseScheduleTemplate;

import java.awt.print.Pageable;
import java.util.List;


public interface DispenseScheduleTemplateRepository extends JpaRepository<DispenseScheduleTemplate, Long> {
    List<DispenseScheduleTemplate> findAllByOwnerId(Long ownerId, Pageable of);
}

package ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.repos;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.domain.DispenseScheduleTemplate;

import java.util.List;


public interface DispenseScheduleTemplateRepository extends JpaRepository<DispenseScheduleTemplate, Long> {
    List<DispenseScheduleTemplate> findAllByOwnerId(Long ownerId, Pageable pageable);
}

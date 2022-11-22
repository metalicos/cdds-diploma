package ua.com.cyberdone.devicemicroservice.device.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.cyberdone.devicemicroservice.device.domain.DispenseScheduleTemplate;


public interface DispenseScheduleTemplateRepository extends JpaRepository<DispenseScheduleTemplate, Long> {
}

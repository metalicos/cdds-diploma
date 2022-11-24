package ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.domain.DispenseScheduleDispenseScheduleTemplate;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.domain.DispenserDispenserTemplate;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.domain.embeddable.DispenseScheduleDispenseScheduleTemplateId;

import java.util.Optional;


public interface DispenseScheduleDispenseScheduleTemplateRepository
        extends JpaRepository<DispenseScheduleDispenseScheduleTemplate, DispenseScheduleDispenseScheduleTemplateId> {

    @Query("from DispenseScheduleDispenseScheduleTemplate t where t.dispenseSchedule.id = ?1 and t.dispenseScheduleTemplate.id = ?2")
    Optional<DispenseScheduleDispenseScheduleTemplate> findByDispenseScheduleIdAndDispenseScheduleTemplateId(
            Long dispenseScheduleId, Long dispenseScheduleTemplateId);

}

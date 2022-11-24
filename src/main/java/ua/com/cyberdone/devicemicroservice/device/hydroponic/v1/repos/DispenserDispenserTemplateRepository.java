package ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.domain.DispenserDispenserTemplate;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.domain.embeddable.DispenserDispenserTemplateId;

import java.util.Optional;


public interface DispenserDispenserTemplateRepository extends JpaRepository<DispenserDispenserTemplate, DispenserDispenserTemplateId> {

    @Query("from DispenserDispenserTemplate t where t.dispenser.id = ?1 and t.dispenserTemplate.id = ?2")
    Optional<DispenserDispenserTemplate> findByDispenserIdAndDispenserTemplateId(Long dispenserId, Long dispenserTemplateId);
}

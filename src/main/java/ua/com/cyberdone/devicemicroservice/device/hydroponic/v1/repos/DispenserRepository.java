package ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.repos;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.domain.Dispenser;

import java.util.List;


public interface DispenserRepository extends JpaRepository<Dispenser, Long> {

    @Query("select d from Dispenser d where d.device.uuid = :uuid")
    List<Dispenser> findDispensersByDevice(@Param("uuid") String uuid, Pageable pageable);
}

package ua.com.cyberdone.devicemicroservice.device.repos;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.com.cyberdone.devicemicroservice.device.domain.Device;
import ua.com.cyberdone.devicemicroservice.device.domain.Dispenser;
import ua.com.cyberdone.devicemicroservice.device.domain.DispenserTemplate;

import java.util.List;
import java.util.Optional;


public interface DispenserRepository extends JpaRepository<Dispenser, Long> {

    @Query("select d from Dispenser d where d.device.uuid = ?1")
    List<Dispenser> findDispensersByDevice(String uuid, Pageable pageable);
}

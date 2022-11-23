package ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.repos;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.domain.SpecialSystem;

import java.util.Optional;

public interface SpecialSystemRepository extends JpaRepository<SpecialSystem, Long> {
    @Query("select ss from SpecialSystem ss where ss.device.uuid = ?1")
    Optional<SpecialSystem> findByDeviceUuid(String uuid);
}

package ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.repos;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.domain.System;

import java.util.Optional;

public interface SystemRepository extends JpaRepository<System, Long> {
    @Query("select s from System s where s.device.uuid = ?1")
    Optional<System> findByDeviceUuid(String uuid);
}

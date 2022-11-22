package ua.com.cyberdone.devicemicroservice.device.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.com.cyberdone.devicemicroservice.device.domain.Device;
import ua.com.cyberdone.devicemicroservice.device.model.DeviceType;

import java.util.List;
import java.util.Optional;


public interface DeviceRepository extends JpaRepository<Device, Long> {

    Optional<Device> findByUuid(String uuid);

    List<Device> findAllByType(DeviceType deviceType);

    List<Device> findAllByOwnerId(Long ownerId);

    boolean existsByUuid(String uuid);

}

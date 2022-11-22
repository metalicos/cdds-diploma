package ua.com.cyberdone.devicemicroservice.device.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.cyberdone.devicemicroservice.device.domain.DeviceDetails;


public interface DeviceDetailsRepository extends JpaRepository<DeviceDetails, Long> {
}

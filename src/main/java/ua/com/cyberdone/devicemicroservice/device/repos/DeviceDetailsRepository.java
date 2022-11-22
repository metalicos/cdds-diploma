package ua.com.cyberdone.devicemicroservice.device.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.cyberdone.devicemicroservice.device.domain.Device;
import ua.com.cyberdone.devicemicroservice.device.domain.DeviceDetails;
import ua.com.cyberdone.devicemicroservice.device.model.DeviceDetailsDTO;

import java.util.Optional;


public interface DeviceDetailsRepository extends JpaRepository<DeviceDetails, Long> {

    Optional<DeviceDetails> findByDevice(Device device);
}

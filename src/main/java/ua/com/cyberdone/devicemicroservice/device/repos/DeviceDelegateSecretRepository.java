package ua.com.cyberdone.devicemicroservice.device.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import ua.com.cyberdone.devicemicroservice.device.domain.DeviceDelegateSecret;


public interface DeviceDelegateSecretRepository extends JpaRepository<DeviceDelegateSecret, Long> {

    @Modifying
    void deleteAllByDeviceId(Long deviceId);
}

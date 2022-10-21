package ua.com.cyberdone.devicemicroservice.device.common.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ua.com.cyberdone.devicemicroservice.device.common.entity.DeviceType;

import java.util.List;
import java.util.Optional;


public interface DeviceTypeRepository extends CrudRepository<DeviceType, Long> {

    @Query("""
            SELECT * FROM public."DEVICE_TYPE"
            WHERE type = :type
            """)
    Optional<DeviceType> find(@Param("type") String type);
}

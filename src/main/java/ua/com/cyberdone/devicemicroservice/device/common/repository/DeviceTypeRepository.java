package ua.com.cyberdone.devicemicroservice.device.common.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.Repository;
import ua.com.cyberdone.devicemicroservice.device.common.entity.DeviceType;

import java.util.List;


public interface DeviceTypeRepository extends Repository<DeviceType, Long> {

    @Query("SELECT type FROM public.\"DEVICE_TYPE\"")
    List<DeviceType> findAll();
}

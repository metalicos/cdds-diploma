package ua.com.cyberdone.devicemicroservice.persistence.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.com.cyberdone.devicemicroservice.persistence.entity.DeviceSpecialInformation;
import ua.com.cyberdone.devicemicroservice.persistence.entity.hydroponic.HydroponicData;

import java.util.List;

public interface DeviceSpecialInformationRepository extends JpaRepository<DeviceSpecialInformation, Long> {

    @Query("select s from DeviceSpecialInformation s where s.deviceMetadata.uuid = :uuid order by s.createdTimestamp desc ")
    List<HydroponicData> findLastInformation(@Param("uuid") String uuid,
                                             Pageable pageable);
}
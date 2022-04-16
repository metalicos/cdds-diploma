package ua.com.cyberdone.devicemicroservice.persistence.repository;


import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.com.cyberdone.devicemicroservice.persistence.entity.hydroponic.HydroponicData;

import java.util.List;

@Repository
public interface HydroponicDataRepository extends JpaRepository<HydroponicData, Long> {

    @Query("select d from HydroponicData d where d.deviceMetadata.uuid = :uuid order by d.createdTimestamp desc ")
    List<HydroponicData> findLastData(@Param("uuid") String uuid, Pageable pageable);

    @Modifying
    @Query("delete from HydroponicData data where data.deviceMetadata.uuid = :uuid")
    void deleteAllDataForUuid(@Param("uuid") String uuid);
}

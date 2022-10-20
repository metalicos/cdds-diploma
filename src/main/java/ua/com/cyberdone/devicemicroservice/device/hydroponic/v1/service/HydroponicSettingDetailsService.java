package ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.entity.HydroponicSettingDetails;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.repository.HydroponicSettingDetailsRepository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public record HydroponicSettingDetailsService(
        HydroponicSettingDetailsRepository hydroponicSettingDetailsRepository) {

    public List<HydroponicSettingDetails> findAll(String settingType, Long page, Long limit) {
        long start = System.currentTimeMillis();
        log.info("[START] {} [findAll] [settingType={}]", this.getClass().getCanonicalName(), settingType);

        var from = page * limit;
        var hydroponicSettingDetailsList = hydroponicSettingDetailsRepository.find(settingType, from, limit);

        log.info("[END] [TAKEN:{}ms] {} [findAll] [settingType={}]", System.currentTimeMillis() - start, this.getClass().getCanonicalName(), settingType);
        return hydroponicSettingDetailsList;
    }

    public Optional<HydroponicSettingDetails> findActive(Long id) {
        long start = System.currentTimeMillis();
        log.info("[START] {} [findActive] [id={}]", this.getClass().getCanonicalName(), id);

        var hydroponicSettingDetails = hydroponicSettingDetailsRepository.findActive(id);

        log.info("[END] [TAKEN:{}ms] {} [findActive] [id={}]", System.currentTimeMillis() - start, this.getClass().getCanonicalName(), id);
        return hydroponicSettingDetails;
    }

    public HydroponicSettingDetails save(HydroponicSettingDetails data) {
        long start = System.currentTimeMillis();
        log.info("[START] {} [save] [{}]", this.getClass().getCanonicalName(), data);

        var saved = hydroponicSettingDetailsRepository.save(data);
        log.info("[END] [TAKEN:{}ms] {} [save] [saved={}]", System.currentTimeMillis() - start, this.getClass().getCanonicalName(), saved);
        return saved;
    }
}

package ua.com.cyberdone.devicemicroservice.persistence.entity.hydroponic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import ua.com.cyberdone.devicemicroservice.persistence.entity.BasicEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "hydroponic_settings")
public class HydroponicSettings extends BasicEntity {
    @Column(name = "uuid", length = 500)
    private String uuid;
    @Column(name = "ph_value")
    private Double phValue;
    @Column(name = "temperature_value")
    private Double temperatureValue;
    @Column(name = "tds_value")
    private Double tdsValue;
    @Column(name = "setup_ph_value")
    private Double setupPhValue;
    @Column(name = "setup_tds_value")
    private Long setupTdsValue;
    @Column(name = "regulate_error_ph")
    private Double regulateErrorPh;
    @Column(name = "regulate_error_fertilizer")
    private Double regulateErrorFertilizer;
    @Column(name = "ml_per_millisecond")
    private Double mlPerMillisecond;
    @Column(name = "ph_up_dose_ml")
    private Double phUpDoseMl;
    @Column(name = "ph_down_dose_ml")
    private Double phDownDoseMl;
    @Column(name = "fertilizer_dose_ml")
    private Double fertilizerDoseMl;
    @Column(name = "recheck_dispensers_after_ms")
    private Long recheckDispensersAfterMs;
    @Column(name = "restart_counter")
    private Long restartCounter;
    @Column(name = "dispensers_enable")
    private Boolean dispensersEnable;
    @Column(name = "sensors_enable")
    private Boolean sensorsEnable;
    @Column(name = "autotime")
    private Boolean autotime;
    @Column(name = "time_zone")
    private String timeZone;
    @Column(name = "wifi_ssid")
    private String wifiSsid;
    @Column(name = "wifi_pass")
    private String wifiPass;
    @Column(name = "microcontroller_time")
    private LocalDateTime microcontrollerTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        HydroponicSettings that = (HydroponicSettings) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

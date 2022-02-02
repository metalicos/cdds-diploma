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
@Table(name = "hydroponic_calibration_data")
public class HydroponicCalibrationData extends BasicEntity {
    @Column(name = "tds_calibration_coefficient_value")
    public Double tdsCalibrationCoefficientValue;
    @Column(name = "tds_oversampling")
    public Integer tdsOversampling;
    @Column(name = "ph_calibration_value_point")
    public Integer phCalibrationValuePoint;
    @Column(name = "ph_calibration_etalon_value_1")
    public Double phCalibrationEtalonValue1;
    @Column(name = "ph_calibration_etalon_value_2")
    public Double phCalibrationEtalonValue2;
    @Column(name = "ph_calibration_adc_value_1")
    public Long phCalibrationAdcValue1;
    @Column(name = "ph_calibration_adc_value_2")
    public Long phCalibrationAdcValue2;
    @Column(name = "ph_calibration_slope")
    public Double phCalibrationSlope;
    @Column(name = "ph_calibration_value_offset")
    public Long phCalibrationValueOffset;
    @Column(name = "ph_oversampling")
    public Integer phOversampling;
    @Column(name = "uuid", length = 500)
    private String uuid;
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
        HydroponicCalibrationData that = (HydroponicCalibrationData) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

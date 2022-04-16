package ua.com.cyberdone.devicemicroservice.persistence.entity.hydroponic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import ua.com.cyberdone.devicemicroservice.persistence.entity.BasicEntity;
import ua.com.cyberdone.devicemicroservice.persistence.entity.DeviceMetadata;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
@Table(name = "hydroponic_data")
public class HydroponicData extends BasicEntity {
    @Column(name = "ph_value")
    private Double phValue;
    @Column(name = "temperature_value")
    private Double temperatureValue;
    @Column(name = "tds_value")
    private Integer tdsValue;
    @Column(name = "is_dispenser_ph_up_open")
    private Boolean isDispenserPhUpOpen;
    @Column(name = "is_dispenser_ph_down_open")
    private Boolean isDispenserPhDownOpen;
    @Column(name = "is_dispenser_tds_open")
    private Boolean isDispenserTdsOpen;
    @Column(name = "microcontroller_time")
    private LocalDateTime microcontrollerTime;

    @OneToOne(optional = false, cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "uuid", nullable = false, referencedColumnName = "uuid", foreignKey = @ForeignKey(
            name = "FK_HYDROPONIC_DATA_uuid_DEVICE_METADATA_uuid",
            value = ConstraintMode.CONSTRAINT))
    private DeviceMetadata deviceMetadata;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        HydroponicData that = (HydroponicData) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

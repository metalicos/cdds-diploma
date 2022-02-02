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
@Table(name = "hydroponic_data")
public class HydroponicData extends BasicEntity {
    @Column(name = "uuid", length = 500)
    private String uuid;
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
    private LocalDateTime microcontrollerTime;

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

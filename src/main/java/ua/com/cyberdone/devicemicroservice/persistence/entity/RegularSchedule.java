package ua.com.cyberdone.devicemicroservice.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "regular_schedules")
public class RegularSchedule extends BasicEntity {
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "key_value")
    private String key;
    @Column(name = "topic_value")
    private String topic;
    @Column(name = "monday")
    private Boolean monday;
    @Column(name = "tuesday")
    private Boolean tuesday;
    @Column(name = "wednesday")
    private Boolean wednesday;
    @Column(name = "thursday")
    private Boolean thursday;
    @Column(name = "friday")
    private Boolean friday;
    @Column(name = "saturday")
    private Boolean saturday;
    @Column(name = "sunday")
    private Boolean sunday;
    @Column(name = "time")
    private LocalTime time;
    @Column(name = "value")
    private String value;
    @Enumerated(EnumType.STRING)
    private ValueType valueType;

    @OneToOne(optional = false, cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "uuid", nullable = false, referencedColumnName = "uuid", foreignKey = @ForeignKey(
            name = "FK_REGULAR_SCHEDULES_uuid_DEVICE_METADATA_uuid",
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
        RegularSchedule that = (RegularSchedule) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

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
import java.util.Objects;

@Entity
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "delegated_device_control")
public class DelegatedDeviceControl extends BasicEntity {

    @Column(name = "delegated_user_username", length = 100, nullable = false)
    private String delegatedUserUsername;

    @Column(name = "comment", length = 500)
    private String comment;

    @OneToOne(optional = false, cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "device_uuid", nullable = false, referencedColumnName = "uuid", foreignKey = @ForeignKey(
            name = "FK_DELEGATED_DEVICE_CONTROL_device_uuid_DEVICE_METADATA_uuid",
            value = ConstraintMode.CONSTRAINT))
    private DeviceMetadata deviceMetadata;

    @Enumerated(EnumType.STRING)
    private DelegationStatus delegationStatus;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        DelegatedDeviceControl that = (DelegatedDeviceControl) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

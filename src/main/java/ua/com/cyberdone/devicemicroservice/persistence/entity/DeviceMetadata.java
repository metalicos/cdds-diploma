package ua.com.cyberdone.devicemicroservice.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "device_metadata")
public class DeviceMetadata extends BasicEntity {
    @Column(name = "uuid", length = 36, unique = true)
    private String uuid;
    @Lob
    @Column(name = "device_image")
    private byte[] deviceImage;
    @Column(name = "name", length = 500, nullable = false)
    private String name;
    @Column(name = "description", length = 500, nullable = false)
    private String description;
    @Enumerated(value = EnumType.STRING)
    private DeviceType deviceType;
    @Column(name = "access_enabled")
    private Boolean accessEnabled;
    @Column(name = "user_id")
    private Long userId;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        DeviceMetadata that = (DeviceMetadata) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

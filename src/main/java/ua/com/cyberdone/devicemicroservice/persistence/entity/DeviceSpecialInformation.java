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
@Table(name = "device_special_information")
public class DeviceSpecialInformation extends BasicEntity {
    @Column(name = "wifi_rssi")
    public Integer wifiRssi;
    @Column(name = "wifi_bsid")
    public String wifiBsid;
    @Column(name = "local_ip")
    public String localIp;
    @Column(name = "subnet_mask")
    public String subnetMask;
    @Column(name = "gateway_ip")
    public String gatewayIP;
    @Column(name = "mac_addr")
    public String macAddr;
    @Column(name = "wifi_ssid", length = 30)
    private String wifiSSID;
    @Column(name = "wifi_pass", length = 30)
    private String wifiPASS;

    @OneToOne(optional = false, cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "uuid", nullable = false, referencedColumnName = "uuid", foreignKey = @ForeignKey(
            name = "FK_DEVICE_SPECIAL_INFORMATION_uuid_DEVICE_METADATA_uuid",
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
        DeviceSpecialInformation that = (DeviceSpecialInformation) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

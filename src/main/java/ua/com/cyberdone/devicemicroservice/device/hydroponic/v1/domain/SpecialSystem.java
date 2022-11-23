package ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.domain;

import lombok.*;
import ua.com.cyberdone.devicemicroservice.device.domain.Device;

import javax.persistence.*;
import java.time.LocalDateTime;


@Builder
@ToString
@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "special_system", schema = "hydroponic_v1")
public class SpecialSystem {

    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(name = "special_system_id_seq", sequenceName = "special_system_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "special_system_id_seq")
    private Long id;
    @Column(name = "time")
    private LocalDateTime time;
    @Column(name = "software_version", columnDefinition = "text")
    private String softwareVersion;
    @Column(name = "schedule_amount")
    private Integer scheduleAmount;
    @Column(name = "dispensers_amount")
    private Integer dispensersAmount;
    @Column(name = "wifi_rssi", columnDefinition = "text")
    private String wifiRssi;
    @Column(name = "wifi_bsid", columnDefinition = "text")
    private String wifiBsid;
    @Column(name = "local_ip", columnDefinition = "text")
    private String localIp;
    @Column(name = "subnet_mask", columnDefinition = "text")
    private String subnetMask;
    @Column(name = "gateway_ip", columnDefinition = "text")
    private String gatewayIp;
    @Column(name = "mac_addr", columnDefinition = "text")
    private String macAddr;
    @Column(name = "created_timestamp")
    private LocalDateTime createdTimestamp;
    @Column(name = "updated_timestamp")
    private LocalDateTime updatedTimestamp;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_uuid")
    @ToString.Exclude
    private Device device;
}

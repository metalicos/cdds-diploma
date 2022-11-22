package ua.com.cyberdone.devicemicroservice.device.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;


@Builder
@ToString
@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class SpecialSystem {

    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(name = "special_system_id_seq", sequenceName = "special_system_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "special_system_id_seq")
    private Long id;

    @Column
    private LocalDateTime time;

    @Column(columnDefinition = "text")
    private String softwareVersion;

    @Column
    private Integer scheduleAmount;

    @Column
    private Integer dispensersAmount;

    @Column(columnDefinition = "text")
    private String wifiRssi;

    @Column(columnDefinition = "text")
    private String wifiBsid;

    @Column(columnDefinition = "text")
    private String localIp;

    @Column(columnDefinition = "text")
    private String subnetMask;

    @Column(columnDefinition = "text")
    private String gatewayIp;

    @Column(columnDefinition = "text")
    private String macAddr;

    @Column
    private LocalDateTime createdTimestamp;

    @Column
    private LocalDateTime updatedTimestamp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_uuid_id")
    @ToString.Exclude
    private Device device;
}

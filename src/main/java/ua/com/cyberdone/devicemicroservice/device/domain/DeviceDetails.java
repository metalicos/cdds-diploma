package ua.com.cyberdone.devicemicroservice.device.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;


@Builder
@ToString
@Setter
@Getter
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class DeviceDetails {

    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(name = "device_details_id_seq", sequenceName = "device_details_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "device_details_id_seq")
    private Long id;

    @Column(columnDefinition = "text")
    private String version;

    @Column(columnDefinition = "text")
    private String modification;

    @Column
    private LocalDateTime manufacturedTimestamp;

    @Column(columnDefinition = "text")
    private String manufacturedCountry;

    @Column
    private LocalDateTime soldTimestamp;

    @Column(columnDefinition = "text")
    private String soldCountry;

    @Column
    private LocalDateTime warrantyFrom;

    @Column
    private LocalDateTime warrantyTo;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_uuid")
    @ToString.Exclude
    private Device device;
}

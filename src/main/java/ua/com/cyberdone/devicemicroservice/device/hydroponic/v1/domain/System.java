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
@Table(name = "system", schema = "hydroponic_v1")
public class System {
    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(name = "system_id_seq", sequenceName = "system_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "system_id_seq")
    private Long id;
    @Column(name = "updated_timestamp")
    private LocalDateTime updatedTimestamp;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_uuid")
    @ToString.Exclude
    private Device device;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private SystemTemplate systemTemplate;
}

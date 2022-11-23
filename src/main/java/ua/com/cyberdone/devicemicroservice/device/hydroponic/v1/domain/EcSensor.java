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
@Table(name = "data", schema = "hydroponic_v1")
public class EcSensor {

    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(name = "ec_sensor_id_seq", sequenceName = "ec_sensor_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ec_sensor_id_seq")
    private Long id;
    @Column(name = "updated_timestamp")
    private LocalDateTime updatedTimestamp;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "ec_sensor_ec_sensor_template",
            joinColumns = @JoinColumn(name = "ec_sensor_id"),
            inverseJoinColumns = @JoinColumn(name = "ec_sensor_template_id")
    )
    @ToString.Exclude
    private EcSensorTemplate ecSensorTemplate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_uuid")
    @ToString.Exclude
    private Device device;
}

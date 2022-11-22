package ua.com.cyberdone.devicemicroservice.device.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Builder
@ToString
@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class EcSensor {

    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(name = "ec_sensor_id_seq", sequenceName = "ec_sensor_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ec_sensor_id_seq")
    private Long id;

    @Column
    private LocalDateTime updatedTimestamp;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "ec_sensor_ec_sensor_template",
            joinColumns = @JoinColumn(name = "ec_sensor_id"),
            inverseJoinColumns = @JoinColumn(name = "ec_sensor_template_id")
    )
    @ToString.Exclude
    private List<EcSensorTemplate> ecSensorTemplateList = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_uuid")
    @ToString.Exclude
    private Device device;
}

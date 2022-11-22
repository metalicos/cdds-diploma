package ua.com.cyberdone.devicemicroservice.device.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Builder
@ToString
@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class PhSensor {

    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(name = "ph_sensor_id_seq", sequenceName = "ph_sensor_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ph_sensor_id_seq")
    private Long id;

    @Column
    private LocalDateTime updatedTimestamp;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_uuid")
    @ToString.Exclude
    private Device device;

    @ManyToMany(mappedBy = "phSensorList")
    @ToString.Exclude
    private List<PhSensorTemplate> phSensorTemplateList;
}

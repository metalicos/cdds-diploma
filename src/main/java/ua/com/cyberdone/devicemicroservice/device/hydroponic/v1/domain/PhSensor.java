package ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.domain;

import lombok.*;
import ua.com.cyberdone.devicemicroservice.device.domain.Device;

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
@Table(name = "ph_sensor", schema = "hydroponic_v1")
public class PhSensor {
    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(name = "ph_sensor_id_seq", sequenceName = "ph_sensor_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ph_sensor_id_seq")
    private Long id;
    @Column(name = "created_timestamp")
    private LocalDateTime createdTimestamp;
    @Column(name = "updated_timestamp")
    private LocalDateTime updatedTimestamp;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_uuid", columnDefinition = "text")
    @ToString.Exclude
    private Device device;

    @ManyToMany(mappedBy = "phSensorList")
    @ToString.Exclude
    private List<PhSensorTemplate> phSensorTemplateList;
}

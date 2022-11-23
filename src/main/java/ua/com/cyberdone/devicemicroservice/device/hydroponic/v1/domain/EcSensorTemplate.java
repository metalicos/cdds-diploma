package ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.domain;

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
@Table(name = "ec_sensor_template", schema = "hydroponic_v1")
public class EcSensorTemplate {

    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(name = "ec_sensor_template_id_seq", sequenceName = "ec_sensor_template_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ec_sensor_template_id_seq")
    private Long id;
    @Column(name = "name", columnDefinition = "text")
    private String name;
    @Column(name = "description", columnDefinition = "text")
    private String description;
    @Column(name = "owner_id")
    private Long ownerId;
    @Column(name = "time")
    private LocalDateTime time;
    @Column(name = "k_low_point")
    private Double kLowPoint;
    @Column(name = "k_high_point")
    private Double kHighPoint;
    @Column(name = "raw_ec")
    private Double rawEc;
    @Column(name = "created_timestamp")
    private LocalDateTime createdTimestamp;
    @Column(name = "updated_timestamp")
    private LocalDateTime updatedTimestamp;

    @ManyToMany(mappedBy = "ecSensorTemplateList")
    @ToString.Exclude
    private List<EcSensor> ecSensorList = new ArrayList<>();
}

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
public class PhSensorTemplate {

    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(name = "ph_sensor_template_id_seq", sequenceName = "ph_sensor_template_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ph_sensor_template_id_seq")
    private Long id;

    @Column(columnDefinition = "text")
    private String name;

    @Column(columnDefinition = "text")
    private String description;

    @Column
    private Long ownerId;

    @Column
    private LocalDateTime time;

    @Column
    private Double point;

    @Column
    private Double value;

    @Column
    private Integer adc;

    @Column
    private Double slope;

    @Column
    private Integer adcOffset;

    @Column
    private Integer oversampling;

    @Column
    private LocalDateTime createdTimestamp;

    @Column
    private LocalDateTime updatedTimestamp;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "ph_sensor_ph_sensor_template",
            joinColumns = @JoinColumn(name = "ph_sensor_template_id"),
            inverseJoinColumns = @JoinColumn(name = "ph_sensor_id")
    )
    @ToString.Exclude
    private List<PhSensor> phSensorList = new ArrayList<>();
}

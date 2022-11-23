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
@Table(name = "ph_sensor_template", schema = "hydroponic_v1")
public class PhSensorTemplate {
    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(name = "ph_sensor_template_id_seq", sequenceName = "ph_sensor_template_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ph_sensor_template_id_seq")
    private Long id;
    @Column(name = "name", columnDefinition = "text")
    private String name;
    @Column(name = "description", columnDefinition = "text")
    private String description;
    @Column(name = "owner_id")
    private Long ownerId;
    @Column(name = "time")
    private LocalDateTime time;
    @Column(name = "point")
    private Double point;
    @Column(name = "value")
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Double> value = new ArrayList<>();
    @Column(name = "adc")
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Integer> adc = new ArrayList<>();
    @Column(name = "slope")
    private Double slope;
    @Column(name = "adc_offset")
    private Integer adcOffset;
    @Column(name = "oversampling")
    private Integer oversampling;
    @Column(name = "created_timestamp")
    private LocalDateTime createdTimestamp;
    @Column(name = "updated_timestamp")
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

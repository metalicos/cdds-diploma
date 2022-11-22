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
public class EcSensorTemplate {

    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(name = "ec_sensor_template_id_seq", sequenceName = "ec_sensor_template_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ec_sensor_template_id_seq")
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
    private Double kLowPoint;

    @Column
    private Double kHighPoint;

    @Column
    private Double rawEc;

    @Column
    private LocalDateTime createdTimestamp;

    @Column
    private LocalDateTime updatedTimestamp;

    @ManyToMany(mappedBy = "ecSensorTemplateList")
    @ToString.Exclude
    private List<EcSensor> ecSensorList = new ArrayList<>();
}

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
public class Data {
    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(name = "data_id_seq", sequenceName = "data_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "data_id_seq")
    private Long id;
    @Column(name = "ec_solution")
    private Double ecSolution;
    @Column(name = "ph_solution")
    private Double phSolution;
    @Column(name = "t_solution")
    private Double tSolution;
    @Column(name = "t_air")
    private Double tAir;
    @Column(name = "humidity_air")
    private Double humidityAir;
    @Column(name = "atmospheric_pressure")
    private Double atmosphericPressure;
    @Column(name = "created_timestamp")
    private LocalDateTime createdTimestamp;
    @Column(name = "updated_timestamp")
    private LocalDateTime updatedTimestamp;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_uuid", columnDefinition = "text",
            foreignKey = @ForeignKey(name = "fk_data_device_uuid", value = ConstraintMode.CONSTRAINT),
            referencedColumnName = "uuid")
    @ToString.Exclude
    private Device device;
}

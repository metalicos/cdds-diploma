package ua.com.cyberdone.devicemicroservice.device.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;


@Builder
@ToString
@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Data {

    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(name = "data_id_seq", sequenceName = "data_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "data_id_seq")
    private Long id;

    @Column
    private Double ecSolution;

    @Column
    private Double phSolution;

    @Column
    private Double tSolution;

    @Column
    private Double tAir;

    @Column
    private Double humidityAir;

    @Column
    private Double atmosphericPressure;

    @Column
    private LocalDateTime createdTimestamp;

    @Column
    private LocalDateTime updatedTimestamp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_uuid_id")
    @ToString.Exclude
    private Device device;
}

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
public class DispenserTemplate {

    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(name = "dispenser_template_id_seq", sequenceName = "dispenser_template_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dispenser_template_id_seq")
    private Long id;

    @Column(columnDefinition = "text")
    private String name;

    @Column(columnDefinition = "text")
    private String description;

    @Column
    private Long ownerId;

    @Column
    private Integer index;

    @Column
    private LocalDateTime time;

    @Column(columnDefinition = "text")
    private String dispenserName;

    @Column
    private Integer pinA;

    @Column
    private Integer pinB;

    @Column
    private Boolean regulationDirection;

    @Column
    private Boolean enable;

    @Column
    private Boolean polarity;

    @Column
    private Boolean smartDose;

    @Column
    private Double totalAddedMl;

    @Column
    private Double mlPerMs;

    @Column
    private Double targetValue;

    @Column
    private Double error;

    @Column
    private Long recheckDispenserAfterSeconds;

    @Column
    private Long lastDispenserRecheckTime;

    @Column
    private Integer mixingVolumeMl;

    @Column
    private LocalDateTime updatedTimestamp;

    @ManyToMany(mappedBy = "dispenserTemplateList")
    @ToString.Exclude
    private List<Dispenser> dispenserList = new ArrayList<>();
}

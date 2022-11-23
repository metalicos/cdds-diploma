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
@Table(name = "dispenser_template", schema = "hydroponic_v1")
public class DispenserTemplate {
    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(name = "dispenser_template_id_seq", sequenceName = "dispenser_template_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dispenser_template_id_seq")
    private Long id;
    @Column(name = "name", columnDefinition = "text")
    private String name;
    @Column(name = "description", columnDefinition = "text")
    private String description;
    @Column(name = "owner_id")
    private Long ownerId;
    @Column(name = "index")
    private Integer index;
    @Column(name = "time")
    private LocalDateTime time;
    @Column(name = "dispenser_name", columnDefinition = "text")
    private String dispenserName;
    @Column(name = "pin_a")
    private Integer pinA;
    @Column(name = "pin_b")
    private Integer pinB;
    @Column(name = "regulation_direction")
    private Boolean regulationDirection;
    @Column(name = "enable")
    private Boolean enable;
    @Column(name = "polarity")
    private Boolean polarity;
    @Column(name = "smart_dose")
    private Boolean smartDose;
    @Column(name = "total_added_ml")
    private Double totalAddedMl;
    @Column(name = "ml_per_ms")
    private Double mlPerMs;
    @Column(name = "target_value")
    private Double targetValue;
    @Column(name = "error")
    private Double error;
    @Column(name = "recheck_dispenser_after_seconds")
    private Long recheckDispenserAfterSeconds;
    @Column(name = "last_dispenser_recheck_time")
    private Long lastDispenserRecheckTime;
    @Column(name = "mixing_volume_ml")
    private Integer mixingVolumeMl;
    @Column(name = "updated_timestamp")
    private LocalDateTime updatedTimestamp;
    @OneToMany(mappedBy = "dispenserTemplate")
    @ToString.Exclude
    private List<Dispenser> dispenserList = new ArrayList<>();
}

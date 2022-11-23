package ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.domain;

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
@Table(name = "dispenser_scheduling", schema = "hydroponic_v1")
public class DispenserScheduling {

    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(name = "dispenser_scheduling_id_seq", sequenceName = "dispenser_scheduling_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dispenser_scheduling_id_seq")
    private Long id;
    @Column(name = "index")
    private Integer index;
    @Column(name = "label", columnDefinition = "text")
    private String label;
    @Column(name = "dose_ml")
    private Double doseMl;
    @Column(name = "is_active")
    private Boolean isActive;
    @Column(name = "created_timestamp")
    private LocalDateTime createdTimestamp;
    @Column(name = "updated_timestamp")
    private LocalDateTime updatedTimestamp;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dispense_schedule_element_id")
    @ToString.Exclude
    private DispenseScheduleElement dispenseScheduleElement;
}

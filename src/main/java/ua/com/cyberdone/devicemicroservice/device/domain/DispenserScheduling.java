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
public class DispenserScheduling {

    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(name = "dispenser_scheduling_id_seq", sequenceName = "dispenser_scheduling_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dispenser_scheduling_id_seq")
    private Long id;

    @Column
    private Integer index;

    @Column(columnDefinition = "text")
    private String label;

    @Column
    private Double doseMl;

    @Column
    private Boolean isActive;

    @Column
    private LocalDateTime createdTimestamp;

    @Column
    private LocalDateTime updatedTimestamp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dispense_schedule_element_id")
    @ToString.Exclude
    private DispenseScheduleElement dispenseScheduleElement;
}

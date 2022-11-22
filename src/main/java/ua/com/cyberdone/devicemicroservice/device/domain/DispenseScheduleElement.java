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
public class DispenseScheduleElement {

    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(name = "dispense_schedule_element_id_seq", sequenceName = "dispense_schedule_element_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dispense_schedule_element_id_seq")
    private Long id;

    @Column
    private Integer index;

    @Column
    private LocalDateTime time;

    @Column
    private Integer day;

    @Column
    private Double targetEc;

    @Column
    private Double targetPh;

    @Column
    private Double ecError;

    @Column
    private Double phError;

    @Column
    private Long recheckAfterSec;

    @Column
    private LocalDateTime createdTimestamp;

    @Column
    private LocalDateTime updatedTimestamp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dispense_schedule_template_id")
    @ToString.Exclude
    private DispenseScheduleTemplate dispenseScheduleTemplate;

    @OneToMany(mappedBy = "dispenseScheduleElement")
    @ToString.Exclude
    private List<DispenserScheduling> dispenserSchedulingList = new ArrayList<>();
}

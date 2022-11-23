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
@Table(name = "dispense_schedule_element", schema = "hydroponic_v1")
public class DispenseScheduleElement {
    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(name = "dispense_schedule_element_id_seq", sequenceName = "dispense_schedule_element_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dispense_schedule_element_id_seq")
    private Long id;
    @Column(name = "index")
    private Integer index;
    @Column(name = "time")
    private LocalDateTime time;
    @Column(name = "day")
    private Integer day;
    @Column(name = "target_ec")
    private Double targetEc;
    @Column(name = "target_ph")
    private Double targetPh;
    @Column(name = "ec_error")
    private Double ecError;
    @Column(name = "ph_error")
    private Double phError;
    @Column(name = "recheck_after_sec")
    private Long recheckAfterSec;
    @Column(name = "created_timestamp")
    private LocalDateTime createdTimestamp;
    @Column(name = "updated_timestamp")
    private LocalDateTime updatedTimestamp;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dispense_schedule_template_id")
    @ToString.Exclude
    private DispenseScheduleTemplate dispenseScheduleTemplate;

    @OneToMany(mappedBy = "dispenseScheduleElement")
    @ToString.Exclude
    private List<DispenserScheduling> dispenserSchedulingList = new ArrayList<>();
}

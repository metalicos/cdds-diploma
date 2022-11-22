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
public class DispenseSchedule {

    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(name = "dispense_schedule_id_seq", sequenceName = "dispense_schedule_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dispense_schedule_id_seq")
    private Long id;

    @Column
    private LocalDateTime updatedTimestamp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_uuid_id")
    @ToString.Exclude
    private Device device;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "dispense_schedule_dispense_schedule_template",
            joinColumns = @JoinColumn(name = "dispense_schedule_id"),
            inverseJoinColumns = @JoinColumn(name = "dispense_schedule_template_id")
    )
    @ToString.Exclude
    private List<DispenseScheduleTemplate> dispenseScheduleTemplateList = new ArrayList<>();
}

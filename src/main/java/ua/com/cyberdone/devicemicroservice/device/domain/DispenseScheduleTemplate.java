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
public class DispenseScheduleTemplate {

    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(name = "dispense_schedule_template_id_seq", sequenceName = "dispense_schedule_template_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dispense_schedule_template_id_seq")
    private Long id;

    @Column(columnDefinition = "text")
    private String name;

    @Column(columnDefinition = "text")
    private String description;

    @Column
    private Long ownerId;

    @Column
    private LocalDateTime createdTimestamp;

    @Column
    private LocalDateTime updatedTimestamp;

    @ManyToMany(mappedBy = "dispenseScheduleTemplateList")
    @ToString.Exclude
    private List<DispenseSchedule> dispenseScheduleList = new ArrayList<>();

    @OneToMany(mappedBy = "dispenseScheduleTemplate")
    @ToString.Exclude
    private List<DispenseScheduleElement> dispenseScheduleElementList = new ArrayList<>();
}

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
@Table(name = "ec_sensor", schema = "hydroponic_v1")
public class DispenseScheduleTemplate {
    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(name = "dispense_schedule_template_id_seq", sequenceName = "dispense_schedule_template_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dispense_schedule_template_id_seq")
    private Long id;
    @Column(name = "name", columnDefinition = "text")
    private String name;
    @Column(name = "description", columnDefinition = "text")
    private String description;
    @Column(name = "owner_id")
    private Long ownerId;
    @Column(name = "created_timestamp")
    private LocalDateTime createdTimestamp;
    @Column(name = "updated_timestamp")
    private LocalDateTime updatedTimestamp;

    @OneToMany(mappedBy = "dispenseScheduleTemplate")
    @ToString.Exclude
    private List<DispenseSchedule> dispenseScheduleList = new ArrayList<>();

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "dispenseScheduleTemplate")
    @ToString.Exclude
    private List<DispenseScheduleDispenseScheduleTemplate> dispenseScheduleDispenseScheduleTemplates = new ArrayList<>();
}

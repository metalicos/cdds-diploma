package ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.domain;

import lombok.*;
import ua.com.cyberdone.devicemicroservice.device.domain.Device;

import javax.persistence.*;
import java.time.LocalDateTime;


@Builder
@ToString
@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "dispense_schedule", schema = "hydroponic_v1")
public class DispenseSchedule {

    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(name = "dispense_schedule_id_seq", sequenceName = "dispense_schedule_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dispense_schedule_id_seq")
    private Long id;
    @Column(name = "updated_timestamp")
    private LocalDateTime updatedTimestamp;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_uuid")
    @ToString.Exclude
    private Device device;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "dispense_schedule_dispense_schedule_template",
            joinColumns = @JoinColumn(name = "dispense_schedule_id"),
            inverseJoinColumns = @JoinColumn(name = "dispense_schedule_template_id")
    )
    @ToString.Exclude
    private DispenseScheduleTemplate dispenseScheduleTemplate;
}

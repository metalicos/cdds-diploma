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
public class Dispenser {

    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(name = "dispenser_id_seq", sequenceName = "dispenser_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dispenser_id_seq")
    private Long id;

    @Column
    private LocalDateTime updatedTimestamp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_uuid_id")
    @ToString.Exclude
    private Device device;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "dispenser_dispenser_template",
            joinColumns = @JoinColumn(name = "dispenser_id"),
            inverseJoinColumns = @JoinColumn(name = "dispenser_template_id")
    )
    @ToString.Exclude
    private List<DispenserTemplate> dispenserTemplateList = new ArrayList<>();
}

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
public class System {

    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(name = "system_id_seq", sequenceName = "system_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "system_id_seq")
    private Long id;

    @Column
    private LocalDateTime updatedTimestamp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_uuid_id")
    @ToString.Exclude
    private Device device;

    @ManyToMany(mappedBy = "systemList")
    @ToString.Exclude
    private List<SystemTemplate> systemTemplateList = new ArrayList<>();
}

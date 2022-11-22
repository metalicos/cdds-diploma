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
public class SystemTemplate {

    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(name = "system_template_id_seq", sequenceName = "system_template_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "system_template_id_seq")
    private Long id;

    @Column(columnDefinition = "text")
    private String name;

    @Column(columnDefinition = "text")
    private String description;

    @Column
    private Long ownerId;

    @Column
    private LocalDateTime time;

    @Column(columnDefinition = "text")
    private String timeZone;

    @Column(columnDefinition = "text")
    private String ntpServer;

    @Column(columnDefinition = "text")
    private String wifiSsid;

    @Column(columnDefinition = "text")
    private String wifiPass;

    @Column
    private Long restarts;

    @Column
    private Integer growingDay;

    @Column
    private Boolean isGrowing;

    @Column
    private Long growStartDate;

    @Column
    private Boolean dispensersEnable;

    @Column
    private Boolean sensorsEnable;

    @Column
    private LocalDateTime createdTimestamp;

    @Column
    private LocalDateTime updatedTimestamp;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "system_system_template",
            joinColumns = @JoinColumn(name = "system_template_id"),
            inverseJoinColumns = @JoinColumn(name = "system_id")
    )
    @ToString.Exclude
    private List<System> systemList = new ArrayList<>();
}

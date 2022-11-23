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
@Table(name = "system_template", schema = "hydroponic_v1")
public class SystemTemplate {
    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(name = "system_template_id_seq", sequenceName = "system_template_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "system_template_id_seq")
    private Long id;
    @Column(name = "name", columnDefinition = "text")
    private String name;
    @Column(name = "description", columnDefinition = "text")
    private String description;
    @Column(name = "owner_id")
    private Long ownerId;
    @Column(name = "time")
    private LocalDateTime time;
    @Column(name = "time_zone", columnDefinition = "text")
    private String timeZone;
    @Column(name = "ntp_server", columnDefinition = "text")
    private String ntpServer;
    @Column(name = "wifi_ssid", columnDefinition = "text")
    private String wifiSsid;
    @Column(name = "wifi_pass", columnDefinition = "text")
    private String wifiPass;
    @Column(name = "restarts")
    private Long restarts;
    @Column(name = "growing_day")
    private Integer growingDay;
    @Column(name = "is_growing")
    private Boolean isGrowing;
    @Column(name = "grow_start_date")
    private Long growStartDate;
    @Column(name = "dispensers_enable")
    private Boolean dispensersEnable;
    @Column(name = "sensors_enable")
    private Boolean sensorsEnable;
    @Column(name = "created_timestamp")
    private LocalDateTime createdTimestamp;
    @Column(name = "updated_timestamp")
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

package ua.com.cyberdone.devicemicroservice.device.domain;

import lombok.*;
import ua.com.cyberdone.devicemicroservice.device.model.DeviceType;

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
public class Device {

    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(name = "device_id_seq", sequenceName = "device_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "device_id_seq")
    private Long id;

    @Column(columnDefinition = "text")
    private String name;

    @Column(columnDefinition = "text")
    private String description;

    @Column(columnDefinition = "text")
    @Enumerated(EnumType.STRING)
    private DeviceType type;

    @Column(unique = true, columnDefinition = "text")
    private String uuid;

    @Column
    private Long ownerId;

    @Column
    private Integer delegatedAccountsNumber;

    @Column
    private Boolean delegatable;

    @Column
    private Integer repairedAmountNumber;

    @Column
    private LocalDateTime createdTimestamp;

    @Column
    private LocalDateTime lastRepairedTimestamp;

    @OneToMany(mappedBy = "device")
    @ToString.Exclude
    private List<DeviceDelegateSecret> deviceDelegateSecretList = new ArrayList<>();

    @OneToMany(mappedBy = "device")
    @ToString.Exclude
    private List<DeviceDetails> deviceDetailsList = new ArrayList<>();

    @OneToMany(mappedBy = "device")
    @ToString.Exclude
    private List<Data> deviceDataList = new ArrayList<>();

    @OneToMany(mappedBy = "device")
    @ToString.Exclude
    private List<Dispenser> deviceDispenserList = new ArrayList<>();

    @OneToMany(mappedBy = "device")
    @ToString.Exclude
    private List<DispenseSchedule> dispenseScheduleList = new ArrayList<>();

    @OneToMany(mappedBy = "device")
    @ToString.Exclude
    private List<EcSensor> ecSensorList = new ArrayList<>();

    @OneToMany(mappedBy = "device")
    @ToString.Exclude
    private List<PhSensor> phSensorList = new ArrayList<>();

    @OneToMany(mappedBy = "device")
    @ToString.Exclude
    private List<System> systemList = new ArrayList<>();

    @OneToMany(mappedBy = "device")
    @ToString.Exclude
    private List<SpecialSystem> specialSystemList = new ArrayList<>();
}

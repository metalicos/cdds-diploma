package ua.com.cyberdone.devicemicroservice.device.domain;

import lombok.*;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.domain.Data;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.domain.System;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.domain.*;
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
@Table(name = "device", schema = "common")
public class Device {
    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(name = "device_id_seq", sequenceName = "device_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "device_id_seq")
    private Long id;
    @Column(name = "name", columnDefinition = "text")
    private String name;
    @Column(name = "description", columnDefinition = "text")
    private String description;
    @Column(name = "type", columnDefinition = "text")
    @Enumerated(EnumType.STRING)
    private DeviceType type;
    @Column(name = "uuid", unique = true, columnDefinition = "text")
    private String uuid;
    @Column(name = "owner_id")
    private Long ownerId;
    @Column(name = "delegated_accounts_number")
    private Integer delegatedAccountsNumber;
    @Column(name = "delegatable")
    private Boolean delegatable;
    @Column(name = "repaired_amount_number")
    private Integer repairedAmountNumber;
    @Column(name = "created_timestamp")
    private LocalDateTime createdTimestamp;
    @Column(name = "last_repaired_timestamp")
    private LocalDateTime lastRepairedTimestamp;

    @OneToMany(mappedBy = "device")
    @ToString.Exclude
    private List<DeviceDelegateSecret> deviceDelegateSecretList = new ArrayList<>();

    @OneToOne(mappedBy = "device")
    @ToString.Exclude
    private DeviceDetails deviceDetails;

    @OneToMany(mappedBy = "device")
    @ToString.Exclude
    private List<Data> deviceDataList = new ArrayList<>();

    @OneToMany(mappedBy = "device")
    @ToString.Exclude
    private List<Dispenser> deviceDispenserList = new ArrayList<>();

    @OneToOne(mappedBy = "device")
    @ToString.Exclude
    private DispenseSchedule dispenseSchedule;

    @OneToOne(mappedBy = "device")
    @ToString.Exclude
    private EcSensor ecSensor;

    @OneToOne(mappedBy = "device")
    @ToString.Exclude
    private PhSensor phSensor;

    @OneToOne(mappedBy = "device")
    @ToString.Exclude
    private System systemSettings;

    @OneToOne(mappedBy = "device")
    @ToString.Exclude
    private SpecialSystem specialSystem;
}

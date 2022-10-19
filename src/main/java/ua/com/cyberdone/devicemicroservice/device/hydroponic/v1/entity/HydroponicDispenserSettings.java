package ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("HYDROPONIC_DISPENSER_SETTINGS_V1")
public class HydroponicDispenserSettings {
    @Id
    @Column("id")
    private Long id;
    @Column("setting_detail_id")
    private Long settingDetailId;

    @Column("device_uuid")
    private String deviceUuid;
    @Column("index")
    private int index;
    @Column("time")
    private LocalDateTime time;
    @Column("dispenser_name")
    private String dispenserName;
    @Column("pin_a")
    private int pinA;
    @Column("pin_b")
    private int pinB;
    @Column("regulation_direction")
    private boolean regulationDirection;
    @Column("enable")
    private boolean enable;
    @Column("polarity")
    private boolean polarity;
    @Column("smart_dose")
    private boolean smartDose;
    @Column("total_added_ml")
    private double totalAddedMl;
    @Column("ml_per_ms")
    private double mlPerMs;
    @Column("target_value")
    private double targetValue;
    @Column("error")
    private double error;
    @Column("recheck_dispenser_after_seconds")
    private int recheckDispenserAfterSeconds;
    @Column("last_dispenser_recheck_time")
    private int lastDispenserRecheckTime;
    @Column("mixing_volume_ml")
    private int mixingVolumeMl;

    @Column("created_timestamp")
    private LocalDateTime createdTimestamp;
    @Column("updated_timestamp")
    private LocalDateTime updatedTimestamp;
}
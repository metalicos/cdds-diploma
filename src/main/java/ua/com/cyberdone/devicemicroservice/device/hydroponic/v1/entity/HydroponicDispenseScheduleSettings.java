package ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("HYDROPONIC_DISPENSE_SCHEDULE_SETTINGS_V1")
public class HydroponicDispenseScheduleSettings {
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
    @Column("day")
    private int day;
    @Column("target_ec")
    private double targetEc;
    @Column("target_ph")
    private double targetPh;
    @Column("ec_error")
    private double ecError;
    @Column("ph_error")
    private double phError;
    @Column("recheck_after_sec")
    private int recheckAfterSec;
    @Column("is_active")
    private ArrayList<Boolean> isActive;
    @Column("dose_ml")
    private ArrayList<Double> doseMl;

    @Column("created_timestamp")
    private LocalDateTime createdTimestamp;
    @Column("updated_timestamp")
    private LocalDateTime updatedTimestamp;
}
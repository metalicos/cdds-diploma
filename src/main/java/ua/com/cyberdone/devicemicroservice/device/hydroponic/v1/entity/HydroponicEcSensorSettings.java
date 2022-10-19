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
@Table("HYDROPONIC_EC_SENSOR_SETTINGS_V1")
public class HydroponicEcSensorSettings {
    @Id
    @Column("id")
    private Long id;
    @Column("setting_detail_id")
    private Long settingDetailId;

    @Column("device_uuid")
    private String deviceUuid;
    @Column("time")
    private LocalDateTime time;
    @Column("k_low_point")
    private double kLowPoint;
    @Column("k_high_point")
    private double kHighPoint;
    @Column("raw_ec")
    private double rawEc;

    @Column("created_timestamp")
    private LocalDateTime createdTimestamp;
    @Column("updated_timestamp")
    private LocalDateTime updatedTimestamp;
}
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
@Table("HYDROPONIC_PH_SENSOR_SETTINGS_V1")
public class HydroponicPhSensorSettings {
    @Id
    @Column("id")
    private Long id;
    @Column("setting_detail_id")
    private Long settingDetailId;

    @Column("device_uuid")
    private String deviceUuid;
    @Column("time")
    private LocalDateTime time;
    @Column("point")
    private String point;
    @Column("value")
    private ArrayList<Double> value;
    @Column("adc")
    private ArrayList<Integer> adc;
    @Column("slope")
    private double slope;
    @Column("adc_offset")
    private int adcOffset;
    @Column("oversampling")
    private int oversampling;

    @Column("created_timestamp")
    private LocalDateTime createdTimestamp;
    @Column("updated_timestamp")
    private LocalDateTime updatedTimestamp;
}
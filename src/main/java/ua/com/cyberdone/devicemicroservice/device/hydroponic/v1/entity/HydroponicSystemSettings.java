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
@Table("HYDROPONIC_SYSTEM_SETTINGS_V1")
public class HydroponicSystemSettings {
    @Id
    @Column("id")
    private Long id;
    @Column("setting_detail_id")
    private Long settingDetailId;

    @Column("device_uuid")
    private String deviceUuid;
    @Column("time")
    private LocalDateTime time;
    @Column("time_zone")
    private String timeZone;
    @Column("ntp_server")
    private String ntpServer;
    @Column("wifi_ssid")
    private String wifiSSID;
    @Column("wifi_pass")
    private String wifiPASS;
    @Column("restarts")
    private int restarts;
    @Column("growing_day")
    private int growingDay;
    @Column("is_growing")
    private boolean isGrowing;
    @Column("grow_start_date")
    private int growStartDate;
    @Column("dispensers_enable")
    private boolean dispensersEnable;
    @Column("sensors_enable")
    private boolean sensorsEnable;

    @Column("created_timestamp")
    private LocalDateTime createdTimestamp;
    @Column("updated_timestamp")
    private LocalDateTime updatedTimestamp;
}
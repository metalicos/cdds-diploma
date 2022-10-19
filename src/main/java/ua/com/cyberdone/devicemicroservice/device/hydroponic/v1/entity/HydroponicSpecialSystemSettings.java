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
@Table("HYDROPONIC_SPECIAL_SYSTEM_SETTINGS_V1")
public class HydroponicSpecialSystemSettings {
    @Id
    @Column("id")
    private Long id;
    @Column("setting_detail_id")
    private Long settingDetailId;

    @Column("device_uuid")
    private String deviceUuid;
    @Column("time")
    private LocalDateTime time;
    @Column("software_version")
    private String softwareVersion;
    @Column("schedule_amount")
    private int scheduleAmount;
    @Column("dispensers_amount")
    private int dispensersAmount;
    @Column("wifi_rssi")
    private String wifiRssi;
    @Column("wifi_bsid")
    private String wifiBsid;
    @Column("local_ip")
    private String localIp;
    @Column("subnet_mask")
    private String subnetMask;
    @Column("gateway_ip")
    private String gatewayIp;
    @Column("mac_addr")
    private String macAddr;

    @Column("created_timestamp")
    private LocalDateTime createdTimestamp;
    @Column("updated_timestamp")
    private LocalDateTime updatedTimestamp;
}
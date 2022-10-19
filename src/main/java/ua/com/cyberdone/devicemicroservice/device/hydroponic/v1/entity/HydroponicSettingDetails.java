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
@Table("HYDROPONIC_SETTING_DETAILS_V1")
public class HydroponicSettingDetails {
    @Id
    @Column("id")
    private Long id;
    @Column("name")
    private String name;
    @Column("description")
    private String description;
    @Column("setting_type")
    private String settingType;
    @Column("created_timestamp")
    private LocalDateTime createdTimestamp;
    @Column("updated_timestamp")
    private LocalDateTime updatedTimestamp;
}
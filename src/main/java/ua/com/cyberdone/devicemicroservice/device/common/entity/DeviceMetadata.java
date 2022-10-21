package ua.com.cyberdone.devicemicroservice.device.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Blob;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("DEVICE_METADATA")
public class DeviceMetadata {
    @Id
    @Column("id")
    private Long id;
    @Column("uuid")
    private String uuid;
    @Column("name")
    private String name;
    @Column("description")
    private String description;
    @Column("tariff")
    private String tariff;
    @Column("owner_id")
    private long ownerId;
    @Column("delegation_key")
    private String delegationKey;
    @Column("logo")
    private Blob logo;
    @Column("device_type_id")
    private long deviceTypeId;
    @Column("created_timestamp")
    private LocalDateTime createdTimestamp;
    @Column("updated_timestamp")
    private LocalDateTime updatedTimestamp;
}
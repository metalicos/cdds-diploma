package ua.com.cyberdone.devicemicroservice.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Blob;
import java.time.LocalDateTime;

@Data
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
}
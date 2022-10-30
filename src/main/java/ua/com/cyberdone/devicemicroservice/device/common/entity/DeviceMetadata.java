package ua.com.cyberdone.devicemicroservice.device.common.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
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
    private Long ownerId;
    @Column("delegation_key")
    private String delegationKey;
    @Column("logo")
    @ToString.Exclude
    private byte[] logo;
    @Column("device_type_id")
    private Long deviceTypeId;
    @Column("created_timestamp")
    private LocalDateTime createdTimestamp;
    @Column("updated_timestamp")
    private LocalDateTime updatedTimestamp;
}
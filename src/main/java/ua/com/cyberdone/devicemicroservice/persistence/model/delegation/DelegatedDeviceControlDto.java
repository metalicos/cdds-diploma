package ua.com.cyberdone.devicemicroservice.persistence.model.delegation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.com.cyberdone.devicemicroservice.persistence.entity.DelegationStatus;
import ua.com.cyberdone.devicemicroservice.persistence.model.DeviceMetadataDto;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DelegatedDeviceControlDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 99043271L;

    private Long id;
    private String delegatedUserUsername;
    private String comment;
    private DeviceMetadataDto deviceMetadata;
    private DelegationStatus delegationStatus;
    private LocalDateTime createdTimestamp;
    private LocalDateTime updatedTimestamp;
}

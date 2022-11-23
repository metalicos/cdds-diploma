package ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EcSensorDTO {
    private Long id;
    private LocalDateTime updatedTimestamp;
    private String deviceUuid;
    private EcSensorTemplateDTO ecSensorTemplateDTO;
}

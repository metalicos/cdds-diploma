package ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.domain.embeddable;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serial;
import java.io.Serializable;

@Data
@Embeddable
public class EcSensorEcSensorTemplateId implements Serializable {
    @Serial
    private static final long serialVersionUID = -1214424124124L;

    private Long ecSensorId;
    private Long ecSensorTemplateId;
}
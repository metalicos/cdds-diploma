package ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.domain.embeddable;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serial;
import java.io.Serializable;

@Data
@Embeddable
public class DispenserDispenserTemplateId implements Serializable {
    @Serial
    private static final long serialVersionUID = -111114124124L;

    private Long dispenserId;
    private Long dispenserTemplateId;
}

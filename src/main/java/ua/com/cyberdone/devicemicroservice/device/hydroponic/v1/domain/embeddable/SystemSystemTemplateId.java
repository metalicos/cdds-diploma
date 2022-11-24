package ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.domain.embeddable;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serial;
import java.io.Serializable;

@Data
@Embeddable
public class SystemSystemTemplateId implements Serializable {
    @Serial
    private static final long serialVersionUID = -121883666124L;

    private Long systemId;
    private Long systemTemplateId;
}

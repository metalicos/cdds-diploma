package ua.com.cyberdone.devicemicroservice.persistence.model.delegation;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import ua.com.cyberdone.devicemicroservice.persistence.model.PageableDto;

import java.io.Serial;
import java.io.Serializable;

@Getter
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PageableDelegatedDeviceControlDto extends PageableDto<DelegatedDeviceControlDto> implements Serializable {
    @Serial
    private static final long serialVersionUID = 99043271L;
}

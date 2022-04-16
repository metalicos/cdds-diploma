package ua.com.cyberdone.devicemicroservice.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.com.cyberdone.devicemicroservice.persistence.entity.DeviceType;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

import static ua.com.cyberdone.devicemicroservice.util.ValidationConstants.VALUE_IS_BLANK_MSG;
import static ua.com.cyberdone.devicemicroservice.util.ValidationConstants.VALUE_IS_NULL_MSG;
import static ua.com.cyberdone.devicemicroservice.util.ValidationConstants.VALUE_NOT_NUMBER_MSG;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceMetadataDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 37281L;

    @NotNull(message = VALUE_IS_NULL_MSG)
    @Digits(message = VALUE_NOT_NUMBER_MSG, integer = Integer.MAX_VALUE, fraction = 10)
    private Long id;
    private String deviceImage;
    @NotBlank(message = VALUE_IS_BLANK_MSG)
    private String uuid;
    @NotBlank(message = VALUE_IS_BLANK_MSG)
    private String name;
    @NotBlank(message = VALUE_IS_BLANK_MSG)
    private String description;
    @NotNull(message = VALUE_IS_NULL_MSG)
    private DeviceType deviceType;
    @NotNull(message = VALUE_IS_NULL_MSG)
    private Boolean accessEnabled;
    @Digits(message = VALUE_NOT_NUMBER_MSG, integer = Integer.MAX_VALUE, fraction = 10)
    @NotNull(message = VALUE_IS_NULL_MSG)
    private Long userId;
}

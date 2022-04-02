package ua.com.cyberdone.devicemicroservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.com.cyberdone.devicemicroservice.persistence.entity.DeviceType;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static ua.com.cyberdone.devicemicroservice.constant.ValidationConstants.VALUE_IS_BLANK_MSG;
import static ua.com.cyberdone.devicemicroservice.constant.ValidationConstants.VALUE_IS_NULL_MSG;
import static ua.com.cyberdone.devicemicroservice.constant.ValidationConstants.VALUE_NOT_NUMBER_MSG;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaveDeviceMetadataDto {
    @NotNull(message = VALUE_IS_NULL_MSG)
    @Digits(message = VALUE_NOT_NUMBER_MSG, integer = Integer.MAX_VALUE, fraction = 10)
    private Long id;
    private byte[] deviceImage;
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

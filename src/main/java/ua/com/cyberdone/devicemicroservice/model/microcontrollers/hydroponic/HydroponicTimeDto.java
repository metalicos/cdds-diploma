package ua.com.cyberdone.devicemicroservice.model.microcontrollers.hydroponic;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

import static ua.com.cyberdone.devicemicroservice.constant.ValidationConstants.UUID_FAILED_MSG;
import static ua.com.cyberdone.devicemicroservice.constant.ValidationConstants.UUID_PATTERN;
import static ua.com.cyberdone.devicemicroservice.constant.ValidationConstants.VALUE_IS_BLANK_MSG;
import static ua.com.cyberdone.devicemicroservice.constant.ValidationConstants.VALUE_IS_NULL_MSG;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HydroponicTimeDto {
    @NotNull(message = VALUE_IS_NULL_MSG)
    private LocalDateTime microcontrollerTime;

    private String microcontrollerTimeZone = "+2:00";

    @Pattern(regexp = UUID_PATTERN, message = UUID_FAILED_MSG)
    @NotBlank(message = VALUE_IS_BLANK_MSG)
    private String uuid;
}

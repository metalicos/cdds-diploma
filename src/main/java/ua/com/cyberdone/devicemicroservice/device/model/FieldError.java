package ua.com.cyberdone.devicemicroservice.device.model;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class FieldError {
    private String field;
    private String errorCode;
}

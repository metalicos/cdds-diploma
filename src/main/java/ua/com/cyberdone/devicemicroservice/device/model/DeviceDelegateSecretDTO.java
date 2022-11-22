package ua.com.cyberdone.devicemicroservice.device.model;


import lombok.*;

@Data
@Builder
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class DeviceDelegateSecretDTO {
    private Long id;
    private String secret;
    private Long accountId;
    private Long deviceId;
}

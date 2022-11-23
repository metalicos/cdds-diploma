package ua.com.cyberdone.devicemicroservice.device.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeviceDelegateSecretDTO {
    private Long id;
    private String secret;
    private Long accountId;
    private String deviceUuid;
}

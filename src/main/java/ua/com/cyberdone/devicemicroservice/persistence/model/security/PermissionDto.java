package ua.com.cyberdone.devicemicroservice.persistence.model.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PermissionDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 33259937223L;

    private Long id;
    private LocalDateTime createdTimestamp;
    private String name;
    private String value;
}

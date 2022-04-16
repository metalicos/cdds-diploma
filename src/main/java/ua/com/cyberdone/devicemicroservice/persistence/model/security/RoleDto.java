package ua.com.cyberdone.devicemicroservice.persistence.model.security;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 37225553L;

    private Long id;
    private LocalDateTime createdTimestamp;
    private String role;
    private Set<PermissionDto> permissions;
}

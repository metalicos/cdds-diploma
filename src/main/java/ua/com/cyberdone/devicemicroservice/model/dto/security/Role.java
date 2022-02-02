package ua.com.cyberdone.devicemicroservice.model.dto.security;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    private Long id;
    private LocalDateTime createdTimestamp;
    private String role;
    private Set<Permission> permissions;
}

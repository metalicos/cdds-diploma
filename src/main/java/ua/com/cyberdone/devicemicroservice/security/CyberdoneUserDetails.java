package ua.com.cyberdone.devicemicroservice.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ua.com.cyberdone.devicemicroservice.persistence.model.security.PermissionDto;
import ua.com.cyberdone.devicemicroservice.persistence.model.security.RoleDto;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CyberdoneUserDetails implements UserDetails {

    private final Set<RoleDto> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        var permissions = new HashSet<PermissionDto>();
        for (RoleDto role : roles) {
            permissions.addAll(role.getPermissions());
        }
        return toSetAuthorities(permissions);
    }

    private Set<SimpleGrantedAuthority> toSetAuthorities(Set<PermissionDto> permissions) {
        return permissions.stream().map(p -> new SimpleGrantedAuthority(p.getValue())).collect(Collectors.toSet());
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return "";
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

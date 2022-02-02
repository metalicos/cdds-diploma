package ua.com.cyberdone.devicemicroservice.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ua.com.cyberdone.devicemicroservice.model.dto.security.Permission;
import ua.com.cyberdone.devicemicroservice.model.dto.security.Role;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CyberdoneUserDetails implements UserDetails {

    private final Set<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        var permissions = new HashSet<Permission>();
        for (Role role : roles) {
            permissions.addAll(role.getPermissions());
        }
        return toSetAuthorities(permissions);
    }

    private Set<SimpleGrantedAuthority> toSetAuthorities(Set<Permission> permissions) {
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

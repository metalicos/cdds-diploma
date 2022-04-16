package ua.com.cyberdone.devicemicroservice.security.filter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHeaders;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import ua.com.cyberdone.devicemicroservice.persistence.model.security.RoleDto;
import ua.com.cyberdone.devicemicroservice.security.JwtService;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
public abstract class AuthenticationFilter extends OncePerRequestFilter {
    public static final String BEARER = "Bearer ";
    protected final JwtService jwtService;
    protected Set<RoleDto> roles;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) {
        try {
            var token = parseJwt(request);
            if (jwtService.isValidToken(token)) {
                roles = new HashSet<>(Arrays.asList(jwtService.getRoles(token)));
                authenticate(request, jwtService.getUsername(token));
            }
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            log.error("Account authentication cannot be set:", e);
        }
    }

    public abstract void authenticate(HttpServletRequest request, String username);

    private String parseJwt(HttpServletRequest request) {
        var headerAuth = request.getHeader(HttpHeaders.AUTHORIZATION);
        return StringUtils.hasText(headerAuth) && headerAuth.startsWith(BEARER) ?
                headerAuth.substring(BEARER.length()) : null;
    }
}

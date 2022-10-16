package ua.com.cyberdone.devicemicroservice.security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import java.util.Arrays;

@Slf4j
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class LogAndSuppressRequestIllegalCharactersFilter extends GenericFilterBean {

    private static final String MALICIOUS_STRING =
            "The request was rejected because the URL contained a potentially malicious string.";
    private static final String LOG_ILLEGAL_CHARACTERS_IN_REQUEST =
            "The request contains illegal characters: {}";
    private static final String[] INVALID_CHARACTERS_ARRAY = {"!", "@", "$"};

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        String requestUri = httpServletRequest.getRequestURI();

        if (Arrays.stream(INVALID_CHARACTERS_ARRAY).anyMatch(requestUri::contains)) {
            sendBadRequest(httpServletResponse, MALICIOUS_STRING);
            return;
        }
        try {
            chain.doFilter(request, response);
        } catch (RequestRejectedException ex) {
            sendBadRequest(httpServletResponse, ex.getMessage());
        }
    }

    private void sendBadRequest(HttpServletResponse httpServletResponse, String message) {
        log.error(LOG_ILLEGAL_CHARACTERS_IN_REQUEST, message);
        httpServletResponse.setStatus(HttpStatus.BAD_REQUEST.value());
    }
}

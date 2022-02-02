package ua.com.cyberdone.devicemicroservice.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import ua.com.cyberdone.devicemicroservice.exception.AlreadyExistException;
import ua.com.cyberdone.devicemicroservice.exception.AuthenticationException;
import ua.com.cyberdone.devicemicroservice.exception.NotFoundException;
import ua.com.cyberdone.devicemicroservice.exception.ValidationException;
import ua.com.cyberdone.devicemicroservice.model.dto.RestError;

import java.nio.file.AccessDeniedException;
import java.util.stream.Collectors;

@ControllerAdvice
@RequestMapping(value = "/error", method = RequestMethod.GET)
public class ExceptionHandlerController {

    public static final String BAD_REQUEST_MSG = "The request does not follow the correct syntax";
    public static final String INTERNAL_SERVER_ERROR_MSG = "There was an error processing the request.";
    public static final String NOT_FOUND_MSG = "Resource not found";
    public static final String ACCESS_DENIED_MSG = "Access denied";
    public static final String NO_CONTENT_MSG = "The resource is null or empty";
    public static final String METHOD_NOT_ALLOWED_MSG = "Operation with resource not allowed";
    public static final String UNAUTHORIZED_MSG = "You are unauthorized.";

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<RestError> noHandlerFoundException(NoHandlerFoundException exception) {
        return new ResponseEntity<>(RestError.builder()
                .error(HttpStatus.NOT_FOUND.getReasonPhrase())
                .title(NOT_FOUND_MSG)
                .detail("Resource not found for " + exception.getRequestURL())
                .build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<RestError> nullPointerException(NullPointerException exception) {
        return new ResponseEntity<>(RestError.builder()
                .error(HttpStatus.NO_CONTENT.getReasonPhrase())
                .title(NO_CONTENT_MSG)
                .detail(exception.getMessage())
                .build(), HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<RestError> accessDeniedException(AccessDeniedException exception) {
        return new ResponseEntity<>(RestError.builder()
                .error(HttpStatus.FORBIDDEN.getReasonPhrase())
                .title(ACCESS_DENIED_MSG)
                .detail("You have no permission to access the resource " + exception.getMessage())
                .build(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<RestError> authenticationException(AuthenticationException exception) {
        return new ResponseEntity<>(RestError.builder()
                .error(HttpStatus.UNAUTHORIZED.getReasonPhrase())
                .title(UNAUTHORIZED_MSG)
                .detail("Authentication failed: " + exception.getMessage())
                .build(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AlreadyExistException.class)
    public ResponseEntity<RestError> alreadyExistException(AlreadyExistException exception) {
        return new ResponseEntity<>(RestError.builder()
                .error(HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase())
                .title(METHOD_NOT_ALLOWED_MSG)
                .detail(exception.getMessage())
                .build(), HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<RestError> notFoundException(NotFoundException exception) {
        return new ResponseEntity<>(RestError.builder()
                .error(HttpStatus.NOT_FOUND.getReasonPhrase())
                .title(NOT_FOUND_MSG)
                .detail(exception.getMessage())
                .build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<RestError> validationException(ValidationException exception) {
        return new ResponseEntity<>(RestError.builder()
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .title(BAD_REQUEST_MSG)
                .detail(exception.getMessage())
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<RestError> httpClientErrorException(HttpClientErrorException exception) {
        return new ResponseEntity<>(RestError.builder()
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .title(BAD_REQUEST_MSG)
                .detail(exception.getMessage())
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RestError> httpClientErrorException(MethodArgumentNotValidException exception) {
        return new ResponseEntity<>(RestError.builder()
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .title(BAD_REQUEST_MSG)
                .detail(String.format("Invalid parameters '%s'. %s",
                        exception.getBindingResult().getFieldErrors().stream()
                                .map(e -> "'" + e.getField() + "'->'" + e.getRejectedValue() + "'")
                                .collect(Collectors.toSet()), exception.getMessage()))
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<RestError> httpClientErrorException(MethodArgumentTypeMismatchException exception) {
        return new ResponseEntity<>(RestError.builder()
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .title(BAD_REQUEST_MSG)
                .detail(String.format("Invalid url parameter '%s' has been sent. %s", exception.getName(),
                        exception.getMessage()))
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<RestError> httpClientErrorException(Exception exception) {
        return new ResponseEntity<>(RestError.builder()
                .error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .title(INTERNAL_SERVER_ERROR_MSG)
                .detail(exception.getMessage())
                .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<RestError> httpClientErrorException(MissingServletRequestParameterException exception) {
        return new ResponseEntity<>(RestError.builder()
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .title(BAD_REQUEST_MSG)
                .detail(exception.getMessage())
                .build(), HttpStatus.BAD_REQUEST);
    }
}

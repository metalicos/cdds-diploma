package ua.com.cyberdone.devicemicroservice.exception;

public class AccessDeniedException extends Exception {

    public AccessDeniedException(String message) {
        super(message);
    }
}

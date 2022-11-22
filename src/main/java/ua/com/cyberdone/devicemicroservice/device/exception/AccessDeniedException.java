package ua.com.cyberdone.devicemicroservice.device.exception;

public class AccessDeniedException extends Exception {
    public AccessDeniedException(String message) {
        super(message);
    }
}

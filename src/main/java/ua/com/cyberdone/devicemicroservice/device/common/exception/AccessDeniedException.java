package ua.com.cyberdone.devicemicroservice.device.common.exception;

public class AccessDeniedException extends Exception {
    public AccessDeniedException(String message) {
        super(message);
    }
}

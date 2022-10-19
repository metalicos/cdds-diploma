package ua.com.cyberdone.devicemicroservice.device.common.exception;

public class NotFoundException extends Exception {
    public NotFoundException() {
    }

    public NotFoundException(String message) {
        super(message);
    }
}

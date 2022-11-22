package ua.com.cyberdone.devicemicroservice.device.exception;

public class AlreadyExistException extends Exception {
    public AlreadyExistException(String message) {
        super(message);
    }
}

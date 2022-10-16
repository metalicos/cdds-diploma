package ua.com.cyberdone.devicemicroservice.exception;

public class AlreadyExistException extends Exception {
    public AlreadyExistException(String message) {
        super(message);
    }
}

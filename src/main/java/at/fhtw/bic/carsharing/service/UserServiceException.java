package at.fhtw.bic.carsharing.service;

public class UserServiceException extends RuntimeException {
    public UserServiceException(Exception e) {
        super(e);
    }
}

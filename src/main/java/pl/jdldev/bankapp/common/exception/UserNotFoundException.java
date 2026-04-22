package pl.jdldev.bankapp.common.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(long message) {
        super("User with id '%s' not found".formatted(message));
    }
}

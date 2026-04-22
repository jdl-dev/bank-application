package pl.jdldev.bankapp.common.exception;

public class CannotCloseBankAccountException extends RuntimeException {
    public CannotCloseBankAccountException(String message) {
        super("Cannot close bank account, reason: " + message);
    }
}

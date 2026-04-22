package pl.jdldev.bankapp.common.exception;

public class BankAccountNotFoundException extends  RuntimeException {
    public BankAccountNotFoundException(Long id) {
        super("Could not find account with '%s'".formatted(id));
    }
}
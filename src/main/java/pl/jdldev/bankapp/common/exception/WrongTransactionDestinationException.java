package pl.jdldev.bankapp.common.exception;

public class WrongTransactionDestinationException extends RuntimeException {
    public WrongTransactionDestinationException(Long sourceAccountId, Long destinationAccountId) {
        super("Wrong transaction destination account, source: '%s' ->  destination: '%s'"
                .formatted(sourceAccountId, destinationAccountId));
    }
}

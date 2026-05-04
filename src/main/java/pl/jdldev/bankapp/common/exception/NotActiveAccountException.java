package pl.jdldev.bankapp.common.exception;

import pl.jdldev.bankapp.account.domain.AccountStatus;

public class NotActiveAccountException extends RuntimeException {
    public NotActiveAccountException(Long accountId, AccountStatus accountStatus) {
        super("Cannot process without active account, aacount id: '%s', account status: '%s'".formatted(accountId, accountStatus));
    }
}
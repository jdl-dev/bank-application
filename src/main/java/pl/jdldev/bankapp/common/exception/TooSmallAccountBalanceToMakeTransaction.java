package pl.jdldev.bankapp.common.exception;

import java.math.BigDecimal;

public class TooSmallAccountBalanceToMakeTransaction extends RuntimeException {
    public TooSmallAccountBalanceToMakeTransaction(BigDecimal accountBalance, BigDecimal transactionAmount) {
        super("Account balance is to small to make transaction. Balance: '%s' Transaction amount: '%s'"
                .formatted(accountBalance, transactionAmount));
    }
}

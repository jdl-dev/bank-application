package pl.jdldev.bankapp.common.exception;

import pl.jdldev.bankapp.account.domain.CurrencyCode;

public class DifferentCurrencyCodeException extends RuntimeException{
    public DifferentCurrencyCodeException(CurrencyCode sourceAccountCurrency, CurrencyCode destinationAccountCurrency) {
        super("Different currency code: '%s' and: '%s'"
                .formatted(
                        sourceAccountCurrency,
                        destinationAccountCurrency));
    }
}

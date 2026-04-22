package pl.jdldev.bankapp.account.api;

import jakarta.validation.constraints.NotNull;
import pl.jdldev.bankapp.account.domain.AccountStatus;
import pl.jdldev.bankapp.account.domain.CurrencyCode;

public record UpdateBankAccountCurrencyRequest(
        @NotNull
        CurrencyCode currencyCode
) {
}

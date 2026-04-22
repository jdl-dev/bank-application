package pl.jdldev.bankapp.account.api;

import jakarta.validation.constraints.NotNull;
import pl.jdldev.bankapp.account.domain.CurrencyCode;

public record CreatBankAccountRequest (
        @NotNull
        Long ownerId,

        @NotNull
        CurrencyCode currency
){}

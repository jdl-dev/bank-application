package pl.jdldev.bankapp.account.api;

import pl.jdldev.bankapp.account.domain.CurrencyCode;

public record CreatBankAccountRequest (
        Long ownerId,
        CurrencyCode currency
){}

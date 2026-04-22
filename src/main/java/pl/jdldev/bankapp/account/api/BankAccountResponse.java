package pl.jdldev.bankapp.account.api;

import pl.jdldev.bankapp.account.domain.AccountStatus;
import pl.jdldev.bankapp.account.domain.CurrencyCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record BankAccountResponse(
        Long id,
        String accountNumber,
        CurrencyCode currency,
        BigDecimal balance,
        AccountStatus status,
        Long ownerId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}

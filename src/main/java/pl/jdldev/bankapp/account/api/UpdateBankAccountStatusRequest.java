package pl.jdldev.bankapp.account.api;

import jakarta.validation.constraints.NotNull;
import pl.jdldev.bankapp.account.domain.AccountStatus;

public record UpdateBankAccountStatusRequest(
        @NotNull
        AccountStatus accountStatus
) {
}

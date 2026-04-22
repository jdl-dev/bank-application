package pl.jdldev.bankapp.account.api;

import jakarta.validation.constraints.NotNull;

public record GetBankAccountByIdRequest(
        @NotNull
        Long id
) {
}

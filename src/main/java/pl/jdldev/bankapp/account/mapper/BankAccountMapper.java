package pl.jdldev.bankapp.account.mapper;

import org.springframework.stereotype.Component;
import pl.jdldev.bankapp.account.api.BankAccountResponse;
import pl.jdldev.bankapp.account.domain.BankAccount;

@Component
public class BankAccountMapper {
    public BankAccountResponse toResponse(BankAccount bankAccount) {
        return new BankAccountResponse(
                bankAccount.getId(),
                bankAccount.getAccountNumber(),
                bankAccount.getCurrency(),
                bankAccount.getBalance(),
                bankAccount.getStatus(),
                bankAccount.getOwner().getId(),
                bankAccount.getCreatedAt(),
                bankAccount.getUpdatedAt()
        );
    }
}

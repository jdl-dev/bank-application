package pl.jdldev.bankapp.account.infrastructure;

import pl.jdldev.bankapp.account.domain.BankAccount;

public interface BankAccountRepository {
    boolean existsByAccountNumber(String accountNumber);
    BankAccount save(BankAccount bankAccount);
}

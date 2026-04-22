package pl.jdldev.bankapp.account.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.jdldev.bankapp.account.domain.BankAccount;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
    boolean existsByAccountNumber(String accountNumber);
}

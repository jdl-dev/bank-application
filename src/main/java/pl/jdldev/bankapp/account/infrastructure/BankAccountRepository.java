package pl.jdldev.bankapp.account.infrastructure;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.jdldev.bankapp.account.domain.BankAccount;

import java.util.Optional;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
    boolean existsByAccountNumber(String accountNumber);
    Optional<BankAccount> getBankAccountById(Long id);
    Page<BankAccount> findAllByOwnerId(Long ownerId, Pageable pageable);
}

package pl.jdldev.bankapp.transaction.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.jdldev.bankapp.transaction.domain.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}

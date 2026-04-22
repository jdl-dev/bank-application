package pl.jdldev.bankapp.transaction.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.jdldev.bankapp.account.domain.BankAccount;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "source_account_id", nullable = false)
    private BankAccount sourceAccount;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "destination_account_id", nullable = false)
    private BankAccount destinationAccount;

    @Column(nullable = false, updatable = false, precision = 19, scale = 2)
    private BigDecimal amount;

    @Column(nullable = false, updatable = false, length = 255)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionStatus status;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "processed_at")
    private LocalDateTime processedAt;

    public Transaction(LocalDateTime processedAt, LocalDateTime createdAt, TransactionStatus status, String title, BigDecimal amount, BankAccount destinationAccount, BankAccount sourceAccount) {
        this.processedAt = processedAt;
        this.createdAt = createdAt;
        this.status = status;
        this.title = title;
        this.amount = amount;
        this.destinationAccount = destinationAccount;
        this.sourceAccount = sourceAccount;
    }

    public void markCompleted(LocalDateTime processedAt) {
        this.status = TransactionStatus.COMPLETED;
        this.processedAt = processedAt;
    }

    public void markFailed(LocalDateTime processedAt) {
        this.status = TransactionStatus.FAILED;
        this.processedAt = processedAt;
    }
}

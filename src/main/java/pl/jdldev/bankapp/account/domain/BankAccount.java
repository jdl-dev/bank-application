package pl.jdldev.bankapp.account.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.jdldev.bankapp.user.domain.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "bank_accounts")
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_number", nullable = false, unique = true, length = 50)
    private String accountNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private CurrencyCode currency;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private AccountStatus status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public BankAccount(String accountNumber, CurrencyCode currency, BigDecimal balance, AccountStatus status, User owner, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.accountNumber = accountNumber;
        this.currency = currency;
        this.balance = balance;
        this.status = status;
        this.owner = owner;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}

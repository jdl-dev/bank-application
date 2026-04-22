package pl.jdldev.bankapp.account.application;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.jdldev.bankapp.account.api.BankAccountResponse;
import pl.jdldev.bankapp.account.api.CreatBankAccountRequest;
import pl.jdldev.bankapp.account.domain.AccountStatus;
import pl.jdldev.bankapp.account.domain.BankAccount;
import pl.jdldev.bankapp.account.infrastructure.BankAccountRepository;
import pl.jdldev.bankapp.account.mapper.BankAccountMapper;
import pl.jdldev.bankapp.common.exception.BankAccountNotFoundException;
import pl.jdldev.bankapp.common.exception.UserNotFoundException;
import pl.jdldev.bankapp.user.domain.User;
import pl.jdldev.bankapp.user.infrastructure.UserRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BankAccountService {

    private final BankAccountRepository bankAccountRepository;
    private final UserRepository userRepository;
    private final BankAccountMapper bankAccountMapper;

    @Transactional
    public BankAccountResponse createBankAccount(CreatBankAccountRequest createBankAccountRequest) {

        User accountOwner = userRepository
                .getUserById(createBankAccountRequest.ownerId())
                .orElseThrow(() -> new UserNotFoundException(createBankAccountRequest.ownerId()));

        BankAccount newBankAccount = new BankAccount(
                generateBankAccountNumber(),
                createBankAccountRequest.currency(),
                BigDecimal.ZERO,
                AccountStatus.ACTIVE,
                accountOwner,
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        BankAccount savedBankAccount = bankAccountRepository.save(newBankAccount);

        return bankAccountMapper.toResponse(savedBankAccount);
    }

    @Transactional(readOnly = true)
    public BankAccountResponse getBankAccountById(Long bankAccountId) {

        BankAccount bankAccount = bankAccountRepository
                .getBankAccountById(bankAccountId)
                .orElseThrow(() -> new BankAccountNotFoundException(bankAccountId));

        return bankAccountMapper.toResponse(bankAccount);
    }

    @Transactional(readOnly = true)
    public Page<BankAccountResponse> getBankAccounts(Pageable  pageable) {

        return bankAccountRepository
                .findAll(pageable)
                .map(bankAccountMapper::toResponse);
    }

    private String generateBankAccountNumber() {

        String accountNumber;

        do {
            accountNumber = UUID.randomUUID().toString();
        } while (bankAccountRepository.existsByAccountNumber(accountNumber));

        return accountNumber;
    }
}

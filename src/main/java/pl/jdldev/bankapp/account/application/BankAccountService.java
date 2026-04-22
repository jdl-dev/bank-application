package pl.jdldev.bankapp.account.application;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.jdldev.bankapp.account.api.BankAccountResponse;
import pl.jdldev.bankapp.account.api.CreatBankAccountRequest;
import pl.jdldev.bankapp.account.api.UpdateBankAccountCurrencyRequest;
import pl.jdldev.bankapp.account.api.UpdateBankAccountStatusRequest;
import pl.jdldev.bankapp.account.domain.AccountStatus;
import pl.jdldev.bankapp.account.domain.BankAccount;
import pl.jdldev.bankapp.account.infrastructure.BankAccountRepository;
import pl.jdldev.bankapp.account.mapper.BankAccountMapper;
import pl.jdldev.bankapp.common.exception.BankAccountNotFoundException;
import pl.jdldev.bankapp.common.exception.CannotCloseBankAccountException;
import pl.jdldev.bankapp.common.exception.UserNotFoundException;
import pl.jdldev.bankapp.user.domain.User;
import pl.jdldev.bankapp.user.infrastructure.UserRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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

    @Transactional
    public BankAccountResponse updateBankAccountStatus(Long bankAccountToUpdateId, UpdateBankAccountStatusRequest updateBankAccountStatusRequest) {

        BankAccount bankAccountToUpdate = bankAccountRepository
                .getBankAccountById(bankAccountToUpdateId)
                .orElseThrow(() -> new BankAccountNotFoundException(bankAccountToUpdateId));

        bankAccountToUpdate.setStatus(updateBankAccountStatusRequest.accountStatus());
        bankAccountToUpdate.setUpdatedAt(LocalDateTime.now());

        return bankAccountMapper.toResponse(bankAccountToUpdate);
    }

    @Transactional
    public BankAccountResponse updateBankAccountCurrency(Long bankAccountToUpdateId, UpdateBankAccountCurrencyRequest updateBankAccountCurrencyRequest) {

        BankAccount bankAccountToUpdate = bankAccountRepository
                .getBankAccountById(bankAccountToUpdateId)
                .orElseThrow(() -> new BankAccountNotFoundException(bankAccountToUpdateId));

        bankAccountToUpdate.setCurrency(updateBankAccountCurrencyRequest.currencyCode());
        bankAccountToUpdate.setUpdatedAt(LocalDateTime.now());

        return bankAccountMapper.toResponse(bankAccountToUpdate);
    }

    @Transactional(readOnly = true)
    public Page<BankAccountResponse> listOfUsersAccounts(Long bankAccountOwnerId, Pageable pageable) {

        if(!userRepository.existsById(bankAccountOwnerId)) {
            throw new UserNotFoundException(bankAccountOwnerId);
        }

        return bankAccountRepository
                .findAllByOwnerId(bankAccountOwnerId, pageable)
                .map(bankAccountMapper::toResponse);
    }

    @Transactional
    public BankAccountResponse closeBankAccountById(Long bankAccountIdToBeClosed) {

        BankAccount bankAccountToBeClosed = bankAccountRepository
                .getBankAccountById(bankAccountIdToBeClosed)
                .orElseThrow(() -> new BankAccountNotFoundException(bankAccountIdToBeClosed));

        if(bankAccountToBeClosed.getBalance().compareTo(BigDecimal.ZERO) > 0) {
            throw new CannotCloseBankAccountException("Bank account with positive balance cannot be closed");
        }

        if(bankAccountToBeClosed.getStatus().equals(AccountStatus.CLOSED)) {
            throw new CannotCloseBankAccountException("Bank account is already closed");
        }

        bankAccountToBeClosed.setStatus(AccountStatus.CLOSED);

        return bankAccountMapper.toResponse(bankAccountToBeClosed);
    }

    private String generateBankAccountNumber() {

        String accountNumber;

        do {
            accountNumber = UUID.randomUUID().toString();
        } while (bankAccountRepository.existsByAccountNumber(accountNumber));

        return accountNumber;
    }
}

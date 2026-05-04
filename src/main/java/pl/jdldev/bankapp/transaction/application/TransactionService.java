package pl.jdldev.bankapp.transaction.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.jdldev.bankapp.account.domain.AccountStatus;
import pl.jdldev.bankapp.account.domain.BankAccount;
import pl.jdldev.bankapp.account.domain.CurrencyCode;
import pl.jdldev.bankapp.common.exception.*;
import pl.jdldev.bankapp.account.infrastructure.BankAccountRepository;
import pl.jdldev.bankapp.transaction.api.CreateTransactionRequest;
import pl.jdldev.bankapp.transaction.domain.Transaction;
import pl.jdldev.bankapp.transaction.infrastructure.TransactionRepository;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final BankAccountRepository bankAccountRepository;

    public Transaction createTransaction(CreateTransactionRequest createTransactionRequest) {

        Long sourceAccountId = createTransactionRequest.sourceAccountId();
        Long destinationAccountId = createTransactionRequest.destinationAccountId();

        BankAccount sourceBankAccount = bankAccountRepository
                .getBankAccountById(sourceAccountId)
                .orElseThrow(() -> new BankAccountNotFoundException(sourceAccountId));

        BankAccount destinationBankAccount = bankAccountRepository
                .getBankAccountById(destinationAccountId)
                .orElseThrow(() -> new BankAccountNotFoundException(destinationAccountId));

        if(!isSourceAccountNotSameAsDestinationAccount(sourceAccountId, destinationAccountId)) {
            throw new WrongTransactionDestinationException(sourceAccountId, destinationAccountId);
        }

        BigDecimal accountBalance = sourceBankAccount.getBalance();
        BigDecimal transactionAmount = createTransactionRequest.amount();

        if(!isAccountBalanceGreaterThanTransactionAmount(accountBalance, transactionAmount)) {
            throw new TooSmallAccountBalanceToMakeTransaction(accountBalance, transactionAmount);
        }

        AccountStatus sourceAccountStatus = sourceBankAccount.getStatus();
        AccountStatus destinationAccountStatus = destinationBankAccount.getStatus();

        if(!isAccountStatusActive(sourceAccountStatus)){
            throw new NotActiveAccountException(sourceAccountId, sourceAccountStatus);
        }

        if(!isAccountStatusActive(destinationAccountStatus)) {
            throw new NotActiveAccountException(destinationAccountId, destinationAccountStatus);
        }

        CurrencyCode sourceAccountCurrency =  sourceBankAccount.getCurrency();
        CurrencyCode destinationAccountCurrency =  destinationBankAccount.getCurrency();

        if(!isCurrencyTransactionSameOnBothAccounts(sourceAccountCurrency, destinationAccountCurrency)) {
            throw new DifferentCurrencyCodeException(sourceAccountCurrency, destinationAccountCurrency);
        }

    }


    private boolean isSourceAccountNotSameAsDestinationAccount(Long sourceAccountId, Long destinationAccountId){
        return sourceAccountId.equals(destinationAccountId);
    }

    private boolean isAccountBalanceGreaterThanTransactionAmount(BigDecimal balance, BigDecimal transactionAmount) {
        return balance.compareTo(transactionAmount) > 0;
    }

    private boolean isAccountStatusActive(AccountStatus  accountStatus) {
        return accountStatus.equals(AccountStatus.ACTIVE);
    }

    private boolean isCurrencyTransactionSameOnBothAccounts(CurrencyCode sourceAccount, CurrencyCode destinationAccount) {
        return sourceAccount.equals(destinationAccount);
    }
}
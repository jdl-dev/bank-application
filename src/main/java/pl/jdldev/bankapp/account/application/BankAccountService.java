package pl.jdldev.bankapp.account.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.jdldev.bankapp.account.domain.BankAccount;
import pl.jdldev.bankapp.account.infrastructure.BankAccountRepository;

@Service
@RequiredArgsConstructor
public class BankAccountService {

    private final BankAccountRepository bankAccountRepository;

}

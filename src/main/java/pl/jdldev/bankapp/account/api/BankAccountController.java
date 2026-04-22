package pl.jdldev.bankapp.account.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import pl.jdldev.bankapp.account.infrastructure.BankAccountRepository;

@RestController
@RequiredArgsConstructor
public class BankAccountController {

    private final BankAccountRepository bankAccountRepository;
}

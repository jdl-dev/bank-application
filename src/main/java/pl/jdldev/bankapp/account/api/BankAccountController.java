package pl.jdldev.bankapp.account.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.jdldev.bankapp.account.application.BankAccountService;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class BankAccountController {

    private final BankAccountService bankAccountService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public BankAccountResponse createBankAccount(@Valid @RequestBody CreatBankAccountRequest request) {
        return bankAccountService.createBankAccount(request);
    }
}

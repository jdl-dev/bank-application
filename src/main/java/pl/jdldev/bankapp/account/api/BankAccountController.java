package pl.jdldev.bankapp.account.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.jdldev.bankapp.account.application.BankAccountService;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class BankAccountController {

    private final BankAccountService bankAccountService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BankAccountResponse createBankAccount(@Valid @RequestBody CreatBankAccountRequest request) {
        return bankAccountService.createBankAccount(request);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BankAccountResponse getBankAccountById(@PathVariable Long id) {
        return bankAccountService.getBankAccountById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<BankAccountResponse> getBankAccounts(Pageable pageable) {
        return bankAccountService.getBankAccounts(pageable);
    }

    @PatchMapping("/{id}/status")
    @ResponseStatus(HttpStatus.OK)
    public BankAccountResponse updateBankAccountStatus(@PathVariable Long id, @Valid @RequestBody UpdateBankAccountStatusRequest request) {
        return bankAccountService.updateBankAccountStatus(id, request);
    }

    @PatchMapping("/{id}/currency")
    @ResponseStatus(HttpStatus.OK)
    public BankAccountResponse updateBankAccountCurrency(@PathVariable Long id, @Valid @RequestBody UpdateBankAccountCurrencyRequest request) {
        return bankAccountService.updateBankAccountCurrency(id, request);
    }
}

package pl.jdldev.bankapp.transaction.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.jdldev.bankapp.transaction.application.TransactionService;
import pl.jdldev.bankapp.transaction.domain.Transaction;

@RestController
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public Transaction createTransaction(@Valid @RequestBody CreateTransactionRequest createTransactionRequest) {
        return transactionService.createTransaction(createTransactionRequest);
    }
}
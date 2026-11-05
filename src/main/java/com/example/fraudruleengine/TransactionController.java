package com.example.fraudruleengine;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import jakarta.validation.Valid;
import java.util.Optional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/api/transactions")
public class TransactionController  {
    private final FraudDetectionService fraudDetectionService;
    public TransactionController(FraudDetectionService fraudDetectionService){
        this.fraudDetectionService = fraudDetectionService;
    }

    @PostMapping
    public FraudResult receiveTransaction(@Valid @RequestBody Transaction transaction){
        boolean suspicious = fraudDetectionService.isSuspicious(transaction);
        String reason = fraudDetectionService.getFraudReason(transaction);
        transaction.setSuspicious(suspicious);
        transaction.setFraudReason(reason);
        transaction.setSeverity(fraudDetectionService.getFraudSeverity(transaction));

        transaction = fraudDetectionService.saveTransaction(transaction);

        return new FraudResult(
                transaction.getTransactionId(),
                transaction.getAmount(),
                suspicious,
                reason,
                transaction.getSeverity()
        );
    }

    @GetMapping
    public List<Transaction> getAllTransactions(){
        return fraudDetectionService.getAllTransactions();
    }

    @GetMapping("/{transactionId}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable String transactionId){
        return fraudDetectionService.getTransactionById(transactionId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}

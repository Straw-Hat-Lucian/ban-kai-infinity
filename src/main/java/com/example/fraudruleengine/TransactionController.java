package com.example.fraudruleengine;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController  {
    private final FraudDetectionService fraudDetectionService;
    public TransactionController(FraudDetectionService fraudDetectionService){
        this.fraudDetectionService = fraudDetectionService;
    }

    @PostMapping
    public FraudResult receiveTransaction(@RequestBody Transaction transaction){
        boolean suspicious = fraudDetectionService.isSuspicious(transaction);
        String reason = fraudDetectionService.getFraudReason(transaction);
        transaction.setSuspicious(suspicious);
        transaction.setFraudReason(reason);

        return new FraudResult(
                transaction.getTransactionId(),
                transaction.getAmount(),
                suspicious,
                reason
        );
    }

    @GetMapping
    public List<Transaction> getAllTransactions(){
        return fraudDetectionService.getAllTransactions();
    }
}

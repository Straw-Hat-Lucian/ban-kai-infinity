package com.example.fraudruleengine;

import org.springframework.web.bind.annotation.*;


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
        String reason = suspicious ? "Transaction exceeds R10 000 threshold" : "No fraud rules triggered";

        return new FraudResult(
                transaction.getTransactionId(),
                transaction.getAmount(),
                suspicious,
                reason
        );
    }
}

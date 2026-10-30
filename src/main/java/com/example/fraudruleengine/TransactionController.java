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
    public Transaction receiveTransaction(@RequestBody Transaction transaction){

        boolean suspicious = fraudDetectionService.isSuspicious(transaction);
        System.out.println("Transaction received: " + transaction.getTransactionId());
        System.out.println("Suspicious: " +suspicious);

        return transaction;
    }
}

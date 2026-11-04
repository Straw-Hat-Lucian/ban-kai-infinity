package com.example.fraudruleengine;

import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
public class FraudDetectionService {
    private final TransactionRepository transactionRepository;
    @Autowired
    public FraudDetectionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }
    private static final BigDecimal TRANSACTION_LIMIT =
            new BigDecimal("10000.00");

    private static final String HIGH_RISK_CATEGORY = "GAMBLING";

    public boolean isSuspicious(Transaction transaction){
        boolean highValue = transaction.getAmount().compareTo(TRANSACTION_LIMIT) >= 0;
        boolean highRiskCategory = HIGH_RISK_CATEGORY.equalsIgnoreCase(transaction.getCategory());

        return highValue || highRiskCategory;
    }

    public String getFraudReason(Transaction transaction){

        if (transaction.getAmount().compareTo(TRANSACTION_LIMIT) > 0) {
            return "HIGH_VALUE_TRANSACTION";
        }
        if (HIGH_RISK_CATEGORY.equalsIgnoreCase(transaction.getCategory())) {
            return "HIGH_RISK_CATEGORY";
        }

        return "NO_FRAUD_RULE_TRIGGERED";
    }

    public FraudSeverity getFraudSeverity(Transaction transaction){
        if (transaction.getAmount().compareTo(TRANSACTION_LIMIT) > 0) {
            return FraudSeverity.HIGH;
        }
        if (HIGH_RISK_CATEGORY.equalsIgnoreCase(transaction.getCategory())) {
            return FraudSeverity.MEDIUM;
        }

        return FraudSeverity.LOW;
    }


    public Transaction saveTransaction(Transaction transaction){
        return transactionRepository.save(transaction);
    }

    public List<Transaction> getAllTransactions(){
        return transactionRepository.findAll();
    }
}

package com.example.fraudruleengine;

import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
public class FraudDetectionService {
    private final TransactionRepository transactionRepository;
    @Autowired
    public FraudDetectionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }
    private static final BigDecimal TRANSACTION_LIMIT =
            new BigDecimal("10000.00");

    private static final Set<String> HIGH_RISK_CATEGORIES = Set.of(
            "GAMBLING",
            "CRYPTO",
            "CASH_ADVANCE"
    );

    public boolean isSuspicious(Transaction transaction){
        boolean highValue = transaction.getAmount().compareTo(TRANSACTION_LIMIT) >= 0;
        boolean highRiskCategory = transaction.getCustomerId() != null &&
                HIGH_RISK_CATEGORIES.contains(
                        transaction.getCategory().toUpperCase()
                );

        boolean rapidTransactions = hasRapidTransactions(transaction);
        return highValue || highRiskCategory || rapidTransactions;
    }

    private boolean hasRapidTransactions(Transaction transaction){
        return transaction.getCustomerId() != null &&
                transactionRepository.countRecentTransactions(
                        transaction.getCustomerId(),
                        java.time.LocalDateTime.now().minusMinutes(5)
                ) >= 2;
    }


    public String getFraudReason(Transaction transaction){

        if (transaction.getAmount().compareTo(TRANSACTION_LIMIT) >= 0) {
            return "HIGH_VALUE_TRANSACTION";
        }

        if (transaction.getCategory() != null &&
                HIGH_RISK_CATEGORIES.contains(transaction.getCategory().toUpperCase())) {
            return "HIGH_RISK_CATEGORY";
        }

        if (hasRapidTransactions(transaction)) {
            return "RAPID_TRANSACTIONS";
        }

        return "NO_FRAUD_RULE_TRIGGERED";
    }

    public FraudSeverity getFraudSeverity(Transaction transaction){
        if (transaction.getAmount().compareTo(TRANSACTION_LIMIT) > 0) {
            return FraudSeverity.HIGH;
        }
        if (transaction.getCategory() != null && HIGH_RISK_CATEGORIES.contains(transaction.getCategory().toUpperCase())) {
            return FraudSeverity.MEDIUM;
        }
        if (hasRapidTransactions(transaction)) {
            return FraudSeverity.HIGH;
        }

        return FraudSeverity.LOW;
    }


    public Transaction saveTransaction(Transaction transaction){
        return transactionRepository.save(transaction);
    }

    public List<Transaction> getAllTransactions(){
        return transactionRepository.findAll();
    }

    public Optional<Transaction> getTransactionById(String transactionId){
        return transactionRepository.findById(transactionId);
    }
}

package com.example.fraudruleengine;

import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
public class FraudDetectionService {
    private static final BigDecimal TRANSACTION_LIMIT =
            new BigDecimal("10000.00");

    public boolean isSuspicious(Transaction transaction){
        return transaction.getAmount().compareTo(TRANSACTION_LIMIT) > 0;
    }
}

package com.example.fraudruleengine;

import java.math.BigDecimal;

public class FraudResult {
    private String transactionId;
    private BigDecimal amount;
    private boolean suspicious;

    public FraudResult(String transactionId, BigDecimal amount, boolean suspicious){
        this.transactionId = transactionId;
        this.amount = amount;
        this.suspicious = suspicious;
    }

    public String getTransactionId(){
        return transactionId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public boolean isSuspicious() {
        return suspicious;
    }
}

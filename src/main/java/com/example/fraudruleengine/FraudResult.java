package com.example.fraudruleengine;

import java.math.BigDecimal;

public class FraudResult {
    private String transactionId;
    private BigDecimal amount;
    private boolean suspicious;
    private String reason;
    private FraudSeverity severity;

    public FraudResult(String transactionId, BigDecimal amount, boolean suspicious, String reason, FraudSeverity  severity) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.suspicious = suspicious;
        this.reason = reason;
        this.severity = severity;
    }

    public String getTransactionId( ){
        return transactionId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public boolean isSuspicious() {
        return suspicious;
    }
    public String getReason() {
        return reason;
    }

    public FraudSeverity getSeverity() {
        return severity;
    }
}

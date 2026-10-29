package com.example.fraudruleengine;

import java.math.BigDecimal;

public class Transaction {
    private String transactionId;
    private String customerId;
    private BigDecimal amount;
    private String category;

    public Transaction(){

    }
    public Transaction(String transactionId, String customerId, BigDecimal amount, String category){
        this.transactionId = transactionId;
        this.customerId = customerId;
        this.amount = amount;
        this.category = category;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}

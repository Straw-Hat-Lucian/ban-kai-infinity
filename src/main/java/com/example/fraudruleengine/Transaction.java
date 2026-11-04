package com.example.fraudruleengine;

import java.math.BigDecimal;
import  jakarta.persistence.Entity;
import  jakarta.persistence.Id;
import  jakarta.persistence.Table;
import  jakarta.persistence.Column;
import  jakarta.persistence.Enumerated;
import  jakarta.persistence.EnumType;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    private String transactionId;
    @Column(nullable = false)
    private String customerId;
    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal amount;
    @Column(nullable = false)
    private String category;
    private boolean suspicious;
    private  String fraudReason;
    @Enumerated(EnumType.STRING)
    private FraudSeverity severity;

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

    public boolean isSuspicious() {
        return suspicious;
    }

    public void setSuspicious(boolean suspicious) {
        this.suspicious = suspicious;
    }

    public String getFraudReason(){
        return fraudReason;
    }

    public void setFraudReason(String fraudReason){
        this.fraudReason = fraudReason;
    }

    public FraudSeverity getSeverity(){
        return severity;
    }

    public void setSeverity(FraudSeverity severity){
        this.severity = severity;
    }

}

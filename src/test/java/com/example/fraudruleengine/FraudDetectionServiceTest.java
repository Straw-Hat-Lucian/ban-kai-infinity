package com.example.fraudruleengine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import  java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

public class FraudDetectionServiceTest {
    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private FraudDetectionService fraudDetectionService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void normalTransactionShouldHaveLowSeverity(){
        Transaction transaction = new Transaction(
                "TXN100",
                "CUST001",
                new BigDecimal("500.00"),
                "TRANSFER"
        );

        FraudSeverity severity =
                fraudDetectionService.getFraudSeverity(transaction);

        assertEquals(FraudSeverity.LOW, severity);
    }

    @Test
    public void highValueTransactionShouldHaveHighSeverity(){
        Transaction transaction = new Transaction(
                "TXN101",
                "CUST001",
                new BigDecimal("15000.00"),
                "TRANSFER"
        );

        FraudSeverity severity =
                fraudDetectionService.getFraudSeverity(transaction);

        assertEquals(FraudSeverity.HIGH, severity);
    }

    @Test
    public void highRiskCategoryShouldHaveMediumSeverity(){
        Transaction transaction = new Transaction(
                "TXN102",
                "CUST001",
                new BigDecimal("500.00"),
                "GAMBLING"
        );

        FraudSeverity severity =
                fraudDetectionService.getFraudSeverity(transaction);

        assertEquals(FraudSeverity.MEDIUM, severity);
    }
}

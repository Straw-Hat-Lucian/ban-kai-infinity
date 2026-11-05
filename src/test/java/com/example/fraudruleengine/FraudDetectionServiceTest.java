package com.example.fraudruleengine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import  java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;


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

    @Test
    void shouldDetectRapidTransaction(){

        when(transactionRepository.countRecentTransactions(
                eq("CUST-RAPID-TEST"),
                any(LocalDateTime.class)
        )).thenReturn(2L);

        Transaction transaction = new Transaction(
                "RAPID-TEST-003",
                "CUST-RAPID-TEST",
                new BigDecimal("900.00"),
                "TRANSFER"
        );

        assertTrue(fraudDetectionService.isSuspicious(transaction));

        assertEquals(
                "RAPID_TRANSACTIONS",
                fraudDetectionService.getFraudReason(transaction)
        );

        assertEquals(
                FraudSeverity.HIGH,
                fraudDetectionService.getFraudSeverity(transaction)
        );
    }
}

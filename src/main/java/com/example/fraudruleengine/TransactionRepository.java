package com.example.fraudruleengine;

import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.math.BigDecimal;


public interface TransactionRepository extends JpaRepository<Transaction, String> {
    @Query("SELECT COUNT(t) FROM Transaction t " +
            "WHERE t.customerId = :customerId " +
            "AND t.createdAt >= :since")
    long countRecentTransactions(
            @Param("customerId") String customerId,
            @Param("since") LocalDateTime since
    );


    @Query("SELECT COUNT(t) FROM Transaction t " +
            "WHERE t.customerId = :customerId " +
            "AND t.amount = :amount " +
            "AND t.category = :category " +
            "AND t.createdAt >= :since")
    long countDuplicateTransactions(
            @Param("customerId") String customerId,
            @Param("amount") BigDecimal amount,
            @Param("category") String category,
            @Param("since") LocalDateTime since
    );
}

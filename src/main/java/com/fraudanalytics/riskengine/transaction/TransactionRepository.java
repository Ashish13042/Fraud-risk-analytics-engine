package com.fraudanalytics.riskengine.transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    // Spring Data JPA automatically provides methods like save(), findById(), etc.
}
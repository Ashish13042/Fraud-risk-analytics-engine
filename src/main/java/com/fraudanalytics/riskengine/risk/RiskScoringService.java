package com.fraudanalytics.riskengine.risk;

import com.fraudanalytics.riskengine.transaction.Transaction;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
public class RiskScoringService {

    public int calculateRiskScore(Transaction transaction) {
        int score = 0;

        // Rule 1: High Amount Check
        if (transaction.getAmount().compareTo(new BigDecimal("1000")) > 0) {
            score += 30; // High value transactions are riskier
        }

        // Rule 2: Merchant Category Risk
        if ("CRYPTO".equalsIgnoreCase(transaction.getMerchantCategory()) ||
                "GAMBLING".equalsIgnoreCase(transaction.getMerchantCategory())) {
            score += 40;
        }

        // Rule 3: Global Limit
        if (transaction.getAmount().compareTo(new BigDecimal("10000")) > 0) {
            score = 100; // Immediate Flag
        }

        return Math.min(score, 100); // Cap score at 100
    }

    public String determineStatus(int score) {
        if (score >= 80) return "BLOCKED";
        if (score >= 50) return "FLAGGED";
        return "APPROVED";
    }
}
package com.industriallogic.loans;

public class CapitalStrategyTermLoan extends CapitalStrategy {
    public double capital(Loan loan) {
        return loan.getCommitment() * loan.duration() * loan.riskFactor(); // Term Loan
    }

    double duration(Loan loan) {
        return 0;
    }
}

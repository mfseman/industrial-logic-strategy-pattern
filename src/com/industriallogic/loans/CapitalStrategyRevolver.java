package com.industriallogic.loans;

public class CapitalStrategyRevolver extends CapitalStrategy {
    @Override
    public double capital(Loan loan) {
        return (loan.outstandingRiskAmount() * loan.duration() * loan.riskFactor())
                + (loan.unusedRiskAmount() * loan.duration() * loan.unusedRiskFactor()); // Revolver
    }

    double duration(Loan loan) {
        return 0;
    }
}

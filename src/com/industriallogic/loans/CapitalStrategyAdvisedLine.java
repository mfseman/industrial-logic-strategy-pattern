package com.industriallogic.loans;

import java.util.Date;
import java.util.Iterator;

public class CapitalStrategyAdvisedLine extends CapitalStrategy {

    @Override
    public double capital(Loan loan) {
        return loan.getCommitment() * loan.getUnusedPercentage() * loan.duration() * loan.riskFactor(); // Advised Line
    }

    @Override
    public double duration(Loan loan) {
        double duration = 0.0;
        double weightedAverage = 0.0;
        double sumOfPayments = 0.0;
        Iterator<Payment> loanPayments = loan.getPayments().iterator();
        while (loanPayments.hasNext()) {
            Payment payment = (Payment) loanPayments.next();
            sumOfPayments += payment.amount();
            weightedAverage += yearsTo(payment.date()) * payment.amount();
        }
        if (loan.getCommitment() != 0.0)
            duration = weightedAverage / sumOfPayments;
        return duration;
    }
}

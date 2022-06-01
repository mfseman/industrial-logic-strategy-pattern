// ***************************************************************************
// Copyright (c) 2020, Industrial Logic, Inc., All Rights Reserved.
//
// This code is the exclusive property of Industrial Logic, Inc. It may ONLY be
// used by students during Industrial Logic's workshops or by individuals
// who are being coached by Industrial Logic on a project.
//
// This code may NOT be copied or used for any other purpose without the prior
// written consent of Industrial Logic, Inc.
// ****************************************************************************


//$CopyrightHeader()$

package com.industriallogic.loans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Loan {

    private final CapitalStrategy capitalStrategy;

    private double commitment;
    private double outstanding;
    private Date start;
    private Date maturity;
    private Date expiry;
    private Date today;
    private int riskRating;
    private double unusedPercentage;
    private List<Payment> payments = new ArrayList<Payment>();

    double getCommitment() {
        return commitment;
    }

    Date getMaturity() {
        return maturity;
    }

    Date getExpiry() {
        return expiry;
    }

    private Loan(double commitment, double outstanding, Date start, Date expiry, Date maturity, int riskRating, CapitalStrategy capitalStrategy) {
        this.commitment = commitment;
        this.outstanding = outstanding;
        this.start = start;
        this.expiry = expiry;
        this.maturity = maturity;
        this.riskRating = riskRating;
        this.unusedPercentage = 1.0;
        this.capitalStrategy = capitalStrategy;
    }

    // Factory creation methods for each type of Loan
    public static Loan createTermLoan(
            double commitment, Date start, Date maturity, int riskRating) {
        return new Loan(commitment, commitment, start, null, maturity, riskRating, new CapitalStrategyTermLoan());
    }

    public static Loan createRevolverLoan(
            double commitment, Date start, Date expiry, int riskRating) {
        return new Loan(commitment, 0, start, expiry, null, riskRating, new CapitalStrategyRevolver());
    }

    public static Loan createAdvisedLineLoan(
            double commitment, Date start, Date expiry, int riskRating) {
        if (riskRating > 3) return null;
        Loan advisedLine = new Loan(commitment, 0, start, expiry, null, riskRating, new CapitalStrategyAdvisedLine());
        advisedLine.setUnusedPercentage(0.1);
        return advisedLine;
    }

    public double capital() {
        return capitalStrategy.capital(this);
    }

    double outstandingRiskAmount() {
        return outstanding;
    }

    double unusedRiskAmount() {
        return (getCommitment() - outstanding);
    }

    public void setOutstanding(double newOutstanding) {
        this.outstanding = newOutstanding;
    }

    public double duration() {
        return capitalStrategy.duration(this); // Term Loan
    }

    public double riskFactor() {
        return RiskFactor.getFactors().forRating(riskRating);
    }

    double unusedRiskFactor() {
        return UnusedRiskFactors.getFactors().forRating(riskRating);
    }

    public void payment(double paymentAmount, Date paymentDate) {
        Payment payment = new Payment(paymentAmount, paymentDate);
        outstanding = outstanding - payment.amount();
        getPayments().add(payment);
    }

    public double getUnusedPercentage() {
        return unusedPercentage;
    }

    private void setUnusedPercentage(double unusedPercentage) {
        this.unusedPercentage = unusedPercentage;
    }

    List<Payment> getPayments() {
        return payments;
    }

    Date getStart() {
        return start;
    }

    Date getToday() {
        return today;
    }
}

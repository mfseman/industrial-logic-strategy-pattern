package com.industriallogic.loans;


import java.util.Date;

public abstract class CapitalStrategy {

    static final int MILLIS_PER_DAY = 86400000;
    static final int DAYS_PER_YEAR = 365;

    abstract double capital(Loan loan);

    abstract double duration(Loan loan);

    double yearsTo(Date endDate, Loan loan) {
        Date beginDate = (loan.getToday() == null ? loan.getStart() : loan.getToday());
        return ((endDate.getTime() - beginDate.getTime()) / MILLIS_PER_DAY) / DAYS_PER_YEAR;
    }
}
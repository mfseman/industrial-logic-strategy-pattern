package com.industriallogic.loans;

import java.util.Date;

public class Payment {
    private double amount;
    private Date date;

    Payment(double amount, Date date) {
        this.amount = amount;
        this.date = date;
    }

    double amount() {
        return amount;
    }

    Date date() {
        return date;
    }
}

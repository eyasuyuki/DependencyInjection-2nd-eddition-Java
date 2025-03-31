package org.javaopen.di.chap4.domain;

import java.util.Currency;

public class Money {
    private double amount;
    private Currency currency;

    public Money(double amount, Currency currency) {
        if (currency == null) {
            throw new IllegalArgumentException("Currency cannot be null");
        }
        this.amount = amount;
        this.currency = currency;
    }

    public double getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }
}

package org.javaopen.di.chap4.domain;

import java.util.Currency;
import java.util.Objects;

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

    @Override
    public int hashCode() {
        return Objects.hash(amount, currency);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Money) {
            Money o = (Money) obj;
            return amount == o.amount
                && Objects.equals(currency, o.currency);
        }
        return false;
    }
}

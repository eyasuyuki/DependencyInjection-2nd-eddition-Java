package org.javaopen.di.chap4.domain;

import java.util.Currency;

public interface ICurrencyConverter {
    Money exchange(Money money, Currency targetCurrency);
}

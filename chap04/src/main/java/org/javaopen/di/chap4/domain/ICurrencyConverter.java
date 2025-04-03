package org.javaopen.di.chap4.domain;

import java.util.Currency;

/**
 * リスト 4.6
 */
public interface ICurrencyConverter {
    Money exchange(Money money, Currency targetCurrency);
}

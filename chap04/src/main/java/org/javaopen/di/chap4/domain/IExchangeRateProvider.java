package org.javaopen.di.chap4.domain;

import java.util.Currency;
import java.util.Map;

public interface IExchangeRateProvider {
    Map<Currency, Double> getExchangeRatesFor(Currency currency);

    void updateExchangeRate(Currency currency, Double rate);
}

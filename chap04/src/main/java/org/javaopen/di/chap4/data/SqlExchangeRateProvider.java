package org.javaopen.di.chap4.data;

import org.javaopen.di.chap4.domain.IExchangeRateProvider;

import java.util.Currency;
import java.util.Map;

public class SqlExchangeRateProvider implements IExchangeRateProvider {
    @Override
    public Map<Currency, Double> getExchangeRatesFor(Currency currency) {
        return Map.of();
    }

    @Override
    public void updateExchangeRate(Currency currency, Double rate) {

    }
}

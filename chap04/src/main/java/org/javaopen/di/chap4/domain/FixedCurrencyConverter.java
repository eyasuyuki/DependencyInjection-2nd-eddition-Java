package org.javaopen.di.chap4.domain;

import java.util.Currency;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * リスト 4.8
 * 固定レートで貨幣変換をするダミーのクラス
 * java.util.Localeに定義された国しか変換しない
 */
public class FixedCurrencyConverter implements ICurrencyConverter {
    private static final Map<Currency, Double> rate = new HashMap<>();
    public FixedCurrencyConverter() {
        rate.put(Currency.getInstance(Locale.JAPAN), 149.776);
        rate.put(Currency.getInstance(Locale.UK), 0.77258);
        rate.put(Currency.getInstance(Locale.FRANCE), 0.9232);
        rate.put(Currency.getInstance(Locale.CANADA), 1.43125);
        rate.put(Currency.getInstance(Locale.GERMANY), 0.9232);
        rate.put(Currency.getInstance(Locale.CHINA), 7.26274);
        rate.put(Currency.getInstance(Locale.TAIWAN), 33.1914);
        rate.put(Currency.getInstance(Locale.ITALY), 0.9232);
        rate.put(Currency.getInstance(Locale.KOREA), 1469.37);
    }
    @Override
    public Money exchange(Money money, Currency targetCurrency) {
        if (Currency.getInstance(Locale.US).equals(targetCurrency)) {
            return money;
        }
        double amount = money.getAmount() * rate.get(targetCurrency);
        return new Money(amount, targetCurrency);
    }
}

package org.javaopen.di.chap4.ui.model;

import org.javaopen.di.chap4.domain.Money;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ProductViewModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String summaryText;

    private final Map<Currency, Locale> currencyMap = new HashMap<>();

    public ProductViewModel(String name, Money unitPrice) {
        // currency set
        for (Locale locale : Locale.getAvailableLocales()) {
            Currency currency = getCurrency(locale);
            if (currency == null) continue;
            currencyMap.put(currency, locale);
        }

        final NumberFormat format = NumberFormat.getCurrencyInstance(getLocale(unitPrice.getCurrency()));
        this.summaryText = String.format("%s (%s)", name, format.format(unitPrice.getAmount()));
    }

    private Currency getCurrency(Locale locale) {
        try {
            return Currency.getInstance(locale);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    private Locale getLocale(Currency currency) {
        if (currency == null) {
            return null;
        }
        return currencyMap.get(currency);
    }

    public String getSummaryText() {
        return summaryText;
    }
}

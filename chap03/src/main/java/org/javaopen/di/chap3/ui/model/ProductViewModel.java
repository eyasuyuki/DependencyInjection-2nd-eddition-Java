package org.javaopen.di.chap3.ui.model;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

public class ProductViewModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String summaryText;
    private static final Locale PRICE_LOCALE = Locale.US;

    public ProductViewModel(String name, double unitPrice) {
        final NumberFormat format = NumberFormat.getCurrencyInstance(PRICE_LOCALE);
        this.summaryText = String.format("%s (%s)", name, format.format(unitPrice));
    }

    public String getSummaryText() {
        return summaryText;
    }
}

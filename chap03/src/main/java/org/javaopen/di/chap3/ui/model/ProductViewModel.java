package org.javaopen.di.chap3.ui.model;

import org.javaopen.di.chap3.domain.DiscountedProduct;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

public class ProductViewModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private double unitPrice;

    private static final Locale PRICE_LOCALE = Locale.US;

    public ProductViewModel(DiscountedProduct product) {
        this.name = product.getName();
        this.unitPrice = product.getUnitPrice();
    }

    public String getSummaryText() {

        final NumberFormat format = NumberFormat.getCurrencyInstance(PRICE_LOCALE);
        return String.format("%s (%s)", name, format.format(unitPrice));
    }

    public String getName() {
        return name;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductViewModel)) return false;
        ProductViewModel that = (ProductViewModel) o;
        return Objects.equals(name, that.name)
            && Objects.equals(unitPrice, that.unitPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, unitPrice);
    }
}


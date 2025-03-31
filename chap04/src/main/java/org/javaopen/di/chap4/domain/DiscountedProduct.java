package org.javaopen.di.chap4.domain;

public class DiscountedProduct {
    private String name;
    private Money unitPrice;

    public DiscountedProduct(String name, Money unitPrice) {
        this.name = name;
        this.unitPrice = unitPrice;
    }

    public String getName() {
        return name;
    }

    public Money getUnitPrice() {
        return unitPrice;
    }
}

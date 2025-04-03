package org.javaopen.di.chap3.domain;

/**
 * リスト 3.6
 */
public class DiscountedProduct {
    private String name;
    private double unitPrice;

    public DiscountedProduct(String name, double unitPrice) {
        this.name = name;
        this.unitPrice = unitPrice;
    }

    public String getName() {
        return name;
    }

    public double getUnitPrice() {
        return unitPrice;
    }
}

package org.javaopen.di.chap3.domain;

import java.util.Objects;

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

    @Override
    public int hashCode() {
        return Objects.hash(name, unitPrice);
    }
    @Override
    public boolean equals(Object obj) {
    if (obj instanceof DiscountedProduct) {
        DiscountedProduct o =  (DiscountedProduct) obj;
        return Objects.equals(name, o.name)
            && Objects.equals(unitPrice, o.unitPrice);
    }
        return false;
    }

}

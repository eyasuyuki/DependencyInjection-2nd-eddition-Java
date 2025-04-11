package org.javaopen.di.chap3.domain;

import java.util.Objects;

/**
 * リスト 3.8
 */
public class Product {
    private String name;
    private Double unitPrice;
    private Boolean isFeatured;

    public Product() {}

    public Product(String name, Double unitPrice, Boolean isFeatured) {
        this.name = name;
        this.unitPrice = unitPrice;
        this.isFeatured = isFeatured;
    }

    public DiscountedProduct applyDiscountFor(IUserContext user) {
        boolean preferred = user.isInRole(Role.PREFERRED_CUSTOMER);
        double discount = preferred ? 0.95 : 1.0;
        return new DiscountedProduct(name, unitPrice * discount);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Boolean getFeatured() {
        return isFeatured;
    }

    public void setFeatured(Boolean featured) {
        isFeatured = featured;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, unitPrice, isFeatured);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Product o) {
            return Objects.equals(name, o.name)
                && Objects.equals(unitPrice, o.unitPrice)
                && Objects.equals(isFeatured, o.isFeatured);
        }
        return false;
    }
}

package org.javaopen.di.chap3.domain;

public class Product {
    private String name;
    private Double unitPrice;
    private Boolean isFeatured;

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
}

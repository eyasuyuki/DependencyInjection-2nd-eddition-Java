package org.javaopen.di.chap4.domain;

import java.util.Currency;

public class Product {
    private String name;
    private Money unitPrice;
    private Boolean isFeatured;

    public Product(String name, Money unitPrice, Boolean isFeatured) {
        this.name = name;
        this.unitPrice = unitPrice;
        this.isFeatured = isFeatured;
    }

    /**
     * リスト 4.15
     * @param currency 通貨
     * @param converter 貨幣変換機
     * @return 通貨換算後のProductオブジェクト
     */
    public Product convertTo(Currency currency, ICurrencyConverter converter) {
        if (currency == null) {
            throw new IllegalArgumentException("Currency cannot be null");
        }
        if (converter == null) {
            throw new IllegalArgumentException("Converter cannot be null");
        }
        Money newPrice = converter.exchange(unitPrice, currency);

        return withUnitPrice(newPrice);
    }

    public Product withUnitPrice(Money newPrice) {
        return new Product(name, newPrice, isFeatured);
    }

    public DiscountedProduct applyDiscountFor(IUserContext user) {
        boolean preferred = user.isInRole(Role.PREFERRED_CUSTOMER);
        double discount = preferred ? 0.95 : 1.0;
        Money newPrice = new Money(unitPrice.getAmount() * discount, unitPrice.getCurrency());
        return new DiscountedProduct(name, newPrice);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Money getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Money unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Boolean getFeatured() {
        return isFeatured;
    }

    public void setFeatured(Boolean featured) {
        isFeatured = featured;
    }
}

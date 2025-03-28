package org.javaopen.di.chap4.ui.model;

import java.util.List;

public class FeaturedProductsViewModel {
    private List<ProductViewModel> products;
    public FeaturedProductsViewModel(List<ProductViewModel> products) {
        this.products = products;
    }

    public List<ProductViewModel> getProducts() {
        return products;
    }
}

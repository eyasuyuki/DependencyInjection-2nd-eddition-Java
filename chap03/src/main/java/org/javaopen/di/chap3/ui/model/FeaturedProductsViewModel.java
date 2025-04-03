package org.javaopen.di.chap3.ui.model;

import java.util.List;

/**
 * リスト 3.3
 */
public class FeaturedProductsViewModel {
    private List<ProductViewModel> products;
    public FeaturedProductsViewModel(List<ProductViewModel> products) {
        this.products = products;
    }

    public List<ProductViewModel> getProducts() {
        return products;
    }
}

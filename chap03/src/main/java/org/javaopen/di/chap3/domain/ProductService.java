package org.javaopen.di.chap3.domain;

import org.javaopen.di.chap3.data.CommerceDao;
import org.javaopen.di.chap3.data.Product;

import java.util.List;
import java.util.stream.Collectors;

public class ProductService {
    private final CommerceDao dao;
    public ProductService() {
        this.dao = new CommerceDao();
    }

    public List<Product> getFeaturedProducts(boolean isFeatured) {
        Double discount = isFeatured ? 0.95 : 1.0;
        List<Product> products = dao.getProduct();
        return products.stream()
                .filter(Product::getFeatured)
                .map(p -> new Product(
                        p.getId(),
                        p.getName(),
                        p.getDescription(),
                        p.getUnitPrice() * discount,
                        p.getFeatured()))
                .collect(Collectors.toList());
    }
}

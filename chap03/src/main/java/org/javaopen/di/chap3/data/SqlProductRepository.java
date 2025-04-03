package org.javaopen.di.chap3.data;

import org.javaopen.di.chap3.domain.IProductRepository;
import org.javaopen.di.chap3.domain.Product;

import java.util.List;
import java.util.stream.Collectors;

/**
 * リスト 3.10
 */
public class SqlProductRepository implements IProductRepository {
    private CommerceDao dao;

    public SqlProductRepository(CommerceDao dao) {
        if (dao == null) {
            throw new IllegalArgumentException("Dao must not be null");
        }
        this.dao = dao;
    }

    @Override
    public List<Product> getFeaturedProducts() {
        return dao.getProduct()
            .stream().filter(Product::getFeatured)
            .collect(Collectors.toList());
    }
}

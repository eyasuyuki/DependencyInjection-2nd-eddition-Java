package org.javaopen.di.chap3.domain;

import java.util.List;
import java.util.stream.Collectors;

/**
 * リスト 3.9
 */
public class ProductService implements IProductService {
    private final IProductRepository repository;
    private final IUserContext userContext;
    public ProductService(IProductRepository repository, IUserContext userContext) {
        if (repository == null) {
            throw new IllegalStateException("repository is null");
        }
        if (userContext == null) {
            throw new IllegalStateException("userContext is null");
        }

        this.repository = repository;
        this.userContext = userContext;
    }

    public List<DiscountedProduct> getFeaturedProducts() {
        return repository.getFeaturedProducts().stream()
            .map(product -> product.applyDiscountFor(userContext))
            .collect(Collectors.toList());
    }

}

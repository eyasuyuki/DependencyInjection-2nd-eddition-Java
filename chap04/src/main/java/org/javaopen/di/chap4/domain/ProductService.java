package org.javaopen.di.chap4.domain;

import java.util.Currency;
import java.util.List;
import java.util.stream.Collectors;

public class ProductService implements IProductService {
    private final IProductRepository repository;
    private final IUserContext userContext;
    private final ICurrencyConverter converter;
    public ProductService(IProductRepository repository,
        IUserContext userContext,
        ICurrencyConverter converter) {
        if (repository == null) {
            throw new IllegalStateException("repository is null");
        }
        if (userContext == null) {
            throw new IllegalStateException("userContext is null");
        }
        if (converter == null) {
            throw new IllegalStateException("converter is null");
        }

        this.repository = repository;
        this.userContext = userContext;
        this.converter = converter;
    }

    public List<DiscountedProduct> getFeaturedProducts() {
        Currency userCurrency = userContext.getCurrency();
        return repository.getFeaturedProducts().stream()
                .map(product -> {
                    return product.convertTo(userCurrency, converter).applyDiscountFor(userContext);
                }).collect(Collectors.toList());
    }

}

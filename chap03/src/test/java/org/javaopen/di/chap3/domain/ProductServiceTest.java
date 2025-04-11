package org.javaopen.di.chap3.domain;

import org.javaopen.di.chap3.domain.fakes.StubUserContext;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ProductServiceTest {

    /**
     * リポジトリがnullで例外を出すテスト
     */
    @Test
    public void testCreateNullRepositoryWillThrow() {
        IProductRepository nullRepository = null;

        Runnable action = () -> new ProductService(nullRepository, new StubUserContext());

        assertThatThrownBy(action::run).isInstanceOf(IllegalStateException.class);
    }

    /**
     * ユーザーコンテキストがnullで例外を出すテスト
     */
    @Test
    public void testCreateNullUserContextWillThrow() {
        IUserContext nullUserContext = null;

        Runnable action = () -> new ProductService(new StubProductRepository(), nullUserContext);

        assertThatThrownBy(action::run).isInstanceOf(IllegalStateException.class);

    }

    /**
     * 通常会員に対するgetFeaturedProductsのテスト
     */
    @Test
    public void testGetFeaturedProductsWillReturnCorrectProductsForNonPreferredUser() {
        IUserContext user = new StubUserContext();

        List<DiscountedProduct> expectedProducts = Arrays.asList(new DiscountedProduct("Olives", 24.5),
                new DiscountedProduct("Mushrooms", 14.2));

        List<Product> featuredProducts = Arrays.asList(new Product("Olives", 24.5, false),
                new Product("Mushrooms", 14.2, false));

        IProductRepository repository = new StubProductRepository(featuredProducts);

        IProductService sut = new ProductService(repository, user);

        List<DiscountedProduct> actualProducts = sut.getFeaturedProducts();

        assertThat(actualProducts).containsExactlyElementsOf(expectedProducts);
    }

    /**
     * 優待会員に対するgetFeaturedProductsのテスト
     */
    @Test
    public void testGetFeaturedProductsWillReturnCorrectProductsForPreferredUser() {
        IUserContext user = new StubUserContext(new Role[]{Role.PREFERRED_CUSTOMER});

        List<DiscountedProduct> expectedProducts = Arrays.asList(new DiscountedProduct("Olives", 95.0),
                new DiscountedProduct("Mushrooms", 47.5));

        List<Product> featuredProducts = Arrays.asList(new Product("Olives", 100.0, false),
                new Product("Mushrooms", 50.0, false));

        IProductRepository repository = new StubProductRepository(featuredProducts);

        IProductService sut = new ProductService(repository, user);

        List<DiscountedProduct> actualProducts = sut.getFeaturedProducts();

        assertThat(actualProducts).containsExactlyElementsOf(expectedProducts);
    }

    /**
     * リポジトリのテスト用スタブ
     */
    static class StubProductRepository implements IProductRepository {
        private final List<Product> products;

        public StubProductRepository() {
            products = new ArrayList<>();
        }

        public StubProductRepository(List<Product> products) {
            this.products = products;
        }

        @Override
        public List<Product> getFeaturedProducts() {
            return products;
        }
    }
}

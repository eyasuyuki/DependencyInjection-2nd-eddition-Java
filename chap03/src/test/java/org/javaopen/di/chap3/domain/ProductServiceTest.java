package org.javaopen.di.chap3.domain;

import org.javaopen.di.chap3.domain.fakes.StubUserContext;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ProductServiceTest {

    @Test
    public void testCreateNullRepositoryWillThrow() {
        IProductRepository nullRepository = null;

        Runnable action = () -> new ProductService(nullRepository, new StubUserContext());

        assertThatThrownBy(action::run).isInstanceOf(IllegalStateException.class);
    }

    @Test
    public void testCreateNullUserContextWillThrow() {
        IUserContext nullUserContext = null;

        Runnable action = () -> new ProductService(new StubProductRepository(), nullUserContext);

        assertThatThrownBy(action::run).isInstanceOf(IllegalStateException.class);

    }

    static class StubProductRepository implements IProductRepository {
        @Override
        public List<Product> getFeaturedProducts() {
            return new ArrayList<>();
        }
    }
}

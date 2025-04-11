package org.javaopen.di.chap3.domain;

import org.javaopen.di.chap3.domain.fakes.StubUserContext;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductTest {

    /**
     * setterが機能しているかどうかのテスト
     */
    @Test
    public void testProductContainsWellBehavedWritableProperties() {
        String expectName = "Anything";
        double expectPrice = 123.45;

        Product sut = new Product();

        sut.setName(expectName);
        sut.setUnitPrice(expectPrice);

        assertThat(sut)
                .extracting(Product::getName, Product::getUnitPrice)
                .containsExactly(expectName, expectPrice);
    }

    /**
     * 通常ユーザーは値引きしないテスト
     */
    @Test
    public void testDiscountWhenUserIsNotPreferredUser() {
        double productUnitPrice = 12.3;
        double expectedUnitPrice = 12.3;

        Product sut = new Product("Anything", productUnitPrice, false);

        DiscountedProduct result = sut.applyDiscountFor(new StubUserContext());

        assertThat(result.getUnitPrice()).isEqualTo(expectedUnitPrice);
    }

    /**
     * 優待ユーザーの値引きテスト
     */
    @Test
    public void testDiscountWhenUserIsPreferredUser() {
        IUserContext preferredCustomer = new StubUserContext(new Role[]{Role.PREFERRED_CUSTOMER});

        double productUnitPrice = 25.0;
        double expectedUnitPrice = 23.75;

        Product sut = new Product("Anything", productUnitPrice, false);

        DiscountedProduct result = sut.applyDiscountFor(preferredCustomer);

        assertThat(result.getUnitPrice()).isEqualTo(expectedUnitPrice);
    }
}

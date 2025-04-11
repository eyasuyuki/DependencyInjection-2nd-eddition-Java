package org.javaopen.di.chap3.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DiscountedProductTest {

    /**
     * nameがnullのとき例外を出すテスト
     */
    @Test
    public void testCreateWithNullNameWillThrow()  {
        Runnable action = () -> new DiscountedProduct(null, 0.0);

        assertThatThrownBy(action::run).isInstanceOf(IllegalArgumentException.class);
    }

    /**
     * nameが正しくセットされるテスト
     */
     @Test
     public void testNameWillMatchConstractorArgument() {
        String expectName = "name";

        DiscountedProduct product = new DiscountedProduct(expectName, 0.0);

        assertThat(product.getName()).isEqualTo(expectName);
     }

    /**
     * unitPriceが正しくセットされるテスト
     */
     @Test
     public void testUnitPriceWillMatchConstractorArgument() {
        double expectPrice = 2.5;

        DiscountedProduct product = new DiscountedProduct("Anything", expectPrice);

        assertThat(product.getUnitPrice()).isEqualTo(expectPrice);
     }
}

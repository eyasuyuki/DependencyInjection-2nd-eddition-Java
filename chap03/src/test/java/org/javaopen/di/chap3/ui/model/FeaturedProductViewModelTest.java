package org.javaopen.di.chap3.ui.model;

import org.javaopen.di.chap3.domain.DiscountedProduct;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class FeaturedProductViewModelTest {

    @Test
    public void testProductIsNotNull() {
        FeaturedProductsViewModel sut = new FeaturedProductsViewModel(Arrays.asList(new ProductViewModel[0]));

        assertThat(sut).isNotNull();
    }
}

package org.javaopen.di.chap3.ui.model;

import org.javaopen.di.chap3.domain.DiscountedProduct;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

public class ProductViewModelTest {

    /**
     * プロパティが正しくセットされるテスト
     */
    @Test
    public void testPropertiesWillMatchConstructorArgument() {
        String expectedName = "Anything";
        double expectedUnitPrice = 123.4;

        ProductViewModel sut = new ProductViewModel(new DiscountedProduct(expectedName, expectedUnitPrice));

        assertThat(sut)
            .extracting("name", "unitPrice")
            .containsExactly(expectedName, expectedUnitPrice);
    }

    /**
     * getSummaryTextのテスト
     */
     @Test
     public void testSummaryTextWillBeCorrect() {
        String name = "MyProduct";
        double unitPrice = 48.50;
        String expectedSummary = "MyProduct ($48.50)";

         ProductViewModel sut = new ProductViewModel(new DiscountedProduct(name, unitPrice));

         String actualSummary = sut.getSummaryText();

         assertThat(actualSummary).isEqualTo(expectedSummary);

     }
}

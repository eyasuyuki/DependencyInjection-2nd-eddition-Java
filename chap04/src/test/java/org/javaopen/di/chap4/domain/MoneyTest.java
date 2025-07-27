package org.javaopen.di.chap4.domain;

import org.junit.jupiter.api.Test;

import java.util.Currency;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

public class MoneyTest {
    /**
     * 自分自身と等価になるかのテスト
     */
     @Test
     public void testInstanceConsiderdEqualToItself() {
        Money money1 = new Money(100.0, Currency.getInstance(Locale.US));

        assertThat(money1).isEqualTo(money1);
     }

    /**
     * 同一通貨の同値で等価になるかのテスト
     */
     @Test
     public void testTwoInstancesWithSameCurrencyAndSameAmountAreEqual() {
        Currency currency = Currency.getInstance(Locale.US);
        Money money1 = new Money(100.0, currency);
        Money money2 = new Money(100.0, currency);

        assertThat(money1).isEqualTo(money2);
     }


    /**
     * 同一通貨の異なる値で等価にならないテスト
     */
    @Test
    public void testTwoInstancesWithSameCurrencyAndDifferentAmountAreNotEqual() {
        Currency currency = Currency.getInstance(Locale.US);
        Money money1 = new Money(100.01, currency);
        Money money2 = new Money(100.02, currency);

        assertThat(money1).isNotEqualTo(money2);
    }

    /**
     *  異なる通貨の同値で等価にならないテスト
     */
    @Test
    public void testTwoInstancesWithDifferentCurrencyAndSameAmountAreNotEqual() {
        Money money1 = new Money(100.01, Currency.getInstance(Locale.US));
        Money money2 = new Money(100.01, Currency.getInstance(Locale.GERMANY));

        assertThat(money1).isNotEqualTo(money2);
    }
}

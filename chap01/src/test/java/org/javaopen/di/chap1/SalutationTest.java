package org.javaopen.di.chap1;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;

/**
 * リスト 1.4
 */
public class SalutationTest {
    @Test
    public void testExclaim() {
        SpyMessageWriter writer = new SpyMessageWriter();
        Salutation target = new Salutation(writer);
        target.exclaim();
        // assertion
        assertThat(writer.getMessage()).isEqualTo("Hello, DI!");
    }

}

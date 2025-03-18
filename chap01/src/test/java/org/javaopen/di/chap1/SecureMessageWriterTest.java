package org.javaopen.di.chap1;

import org.junit.Test;

import java.security.Principal;

import static org.assertj.core.api.Assertions.assertThat;

public class SecureMessageWriterTest {
    private Principal principal = new Principal() {
        @Override
        public String getName() {
            return "";
        }
    };
    private SpyMessageWriter writer = new SpyMessageWriter();

    @Test(expected = IllegalStateException.class)
    public void testPrincipalNull() {
        new SecureMessageWriter(null, writer);
    }

    @Test(expected = IllegalStateException.class)
    public void testWriterNull() {
        new SecureMessageWriter(principal, null);
    }

    @Test
    public void testWrite() {
        final String message = "Greetings";
        // not authenticated
        SecureMessageWriter secureWriter = new SecureMessageWriter(principal, writer);
        secureWriter.write(message);
        assertThat(writer.getMessage()).isNullOrEmpty();

        // authenticated
        Principal validPrincipal = new Principal() {
            @Override
            public String getName() {
                return "validUser";
            }
        };
        secureWriter = new SecureMessageWriter(validPrincipal, writer);
        secureWriter.write(message);
        assertThat(writer.getMessage()).isEqualTo(message);
    }
}

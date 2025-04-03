package org.javaopen.di.chap1;

/**
 * リスト 1.1
 */
public class Salutation {
    private IMessageWriter writer;
    public Salutation(IMessageWriter writer) {
        this.writer = writer;
    }
    public void exclaim() {
        this.writer.write("Hello, DI!");
    }
}

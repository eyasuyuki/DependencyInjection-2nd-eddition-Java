package org.javaopen.di.chap1;

public class SpyMessageWriter implements IMessageWriter {
    private String message;
    @Override
    public void write(String message) {
        this.message = message;
    }
    public String getMessage() {
        return this.message;
    }
}

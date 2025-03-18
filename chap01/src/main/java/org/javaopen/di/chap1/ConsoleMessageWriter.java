package org.javaopen.di.chap1;

public class ConsoleMessageWriter implements IMessageWriter {
    public void write(String message) {
        System.out.println(message);
    }
}

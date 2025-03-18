package org.javaopen.di.chap1;

public class Main {
    public static void main(String[] args) {
        IMessageWriter writer = new ConsoleMessageWriter();
        Salutation salutation = new Salutation(writer);
        salutation.exclaim();
    }
}

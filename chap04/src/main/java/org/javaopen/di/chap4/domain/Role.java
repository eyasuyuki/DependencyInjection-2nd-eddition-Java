package org.javaopen.di.chap4.domain;

public enum Role {
    PREFERRED_CUSTOMER("preferredCustomer");

    private final String value;
    Role(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}

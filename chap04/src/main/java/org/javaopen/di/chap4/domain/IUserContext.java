package org.javaopen.di.chap4.domain;

import java.util.Currency;

public interface IUserContext {
    boolean isInRole(Role role);
    Currency getCurrency();
}

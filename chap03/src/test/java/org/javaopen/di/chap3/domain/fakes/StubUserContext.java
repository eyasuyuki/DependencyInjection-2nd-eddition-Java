package org.javaopen.di.chap3.domain.fakes;

import org.javaopen.di.chap3.domain.IUserContext;
import org.javaopen.di.chap3.domain.Role;

import java.util.Arrays;

public class StubUserContext implements IUserContext {
    private Role[] roles = new Role[0];

    public StubUserContext() {}

    public StubUserContext(Role[] roles) {
        this.roles = roles;
    }

    @Override
    public boolean isInRole(Role role) {
        return Arrays.asList(roles).contains(role);
    }
}

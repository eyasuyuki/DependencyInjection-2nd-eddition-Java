package org.javaopen.di.chap4.ui.security;

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.javaopen.di.chap4.domain.IUserContext;
import org.javaopen.di.chap4.domain.Role;

public class WebSessionUserContext implements IUserContext {
    @Override
    public boolean isInRole(Role role) {
        return AuthenticatedWebSession.get().getRoles().hasRole(role.getValue());
    }
}

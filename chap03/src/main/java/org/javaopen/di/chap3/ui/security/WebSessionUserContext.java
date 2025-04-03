package org.javaopen.di.chap3.ui.security;

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.javaopen.di.chap3.domain.IUserContext;
import org.javaopen.di.chap3.domain.Role;

/**
 * リスト 3.12
 */
public class WebSessionUserContext implements IUserContext {
    @Override
    public boolean isInRole(Role role) {
        return AuthenticatedWebSession.get().getRoles().hasRole(role.getValue());
    }
}

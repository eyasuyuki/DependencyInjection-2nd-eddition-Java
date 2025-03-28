package org.javaopen.di.chap4.ui.security;

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.Request;

public class AuthSession extends AuthenticatedWebSession {
    private String role;
    public AuthSession(Request request) {
        super(request);
    }

    @Override
    protected boolean authenticate(String user, String password) {
        if ("premier".equals(user) && "password".equals(password)) {
            role = "preferredCustomer";
            return true;
        } else if ("user".equals(user) && "password".equals(password)) {
            role = "user";
            return true;
        }
        return false;
    }

    @Override
    public Roles getRoles() {
        return role != null ? new Roles(role) : new Roles();
    }
}

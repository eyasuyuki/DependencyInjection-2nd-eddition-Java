package org.javaopen.di.chap2;

import org.apache.commons.fileupload2.core.RequestContext;
import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.Session;
import org.apache.wicket.authorization.Action;
import org.apache.wicket.authorization.IAuthorizationStrategy;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.authroles.authorization.strategies.role.metadata.MetaDataRoleAuthorizationStrategy;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.component.IRequestableComponent;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.IResource;
import org.javaopen.di.chap2.ui.AuthSession;
import org.javaopen.di.chap2.ui.HomePage;
import org.javaopen.di.chap2.ui.LoginPage;

public class WebApp extends AuthenticatedWebApplication {
    @Override
    protected void init() {
        super.init();
        getSecuritySettings().setAuthorizationStrategy(new IAuthorizationStrategy() {
            @Override
            public <T extends IRequestableComponent> boolean isInstantiationAuthorized(Class<T> aClass) {
                if (HomePage.class.equals(aClass) && !isAuthenticated()) {
                    RequestCycle.get().setResponsePage(LoginPage.class);
                }
                return true;
            }

            @Override
            public boolean isActionAuthorized(Component component, Action action) {
                return true;
            }

            @Override
            public boolean isResourceAuthorized(IResource iResource, PageParameters pageParameters) {
                return true;
            }

            private boolean isAuthenticated() {
                AuthSession session = (AuthSession) Session.get();
                return session != null && session.isSignedIn();
            }
        });
        mountPackage("/login", LoginPage.class);
    }

    @Override
    protected Class<? extends AbstractAuthenticatedWebSession> getWebSessionClass() {
        return AuthSession.class;
    }

    @Override
    public Class<? extends Page> getHomePage() {
        return HomePage.class;
    }

    @Override
    protected Class<? extends WebPage> getSignInPageClass() {
        return LoginPage.class;
    }
}

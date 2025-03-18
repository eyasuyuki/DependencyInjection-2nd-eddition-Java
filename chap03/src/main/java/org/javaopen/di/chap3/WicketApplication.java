package org.javaopen.di.chap3;

import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.Session;
import org.apache.wicket.authorization.Action;
import org.apache.wicket.authorization.IAuthorizationStrategy;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.ws.javax.WicketServerEndpointConfig;
import org.apache.wicket.request.component.IRequestableComponent;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.IResource;
import org.eclipse.jetty.jmx.MBeanContainer;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.webapp.WebAppContext;
import org.eclipse.jetty.websocket.jakarta.server.config.JakartaWebSocketServletContainerInitializer;
import org.javaopen.di.chap3.ui.AuthSession;
import org.javaopen.di.chap3.ui.HomePage;
import org.javaopen.di.chap3.ui.LoginPage;

import javax.management.MBeanServer;
import java.lang.management.ManagementFactory;

public class WicketApplication extends AuthenticatedWebApplication {

	private static Server server;

	public static void initServer() throws Exception {
		System.setProperty("wicket.configuration", "development");

		server = new Server();
		HttpConfiguration httpConfig = new HttpConfiguration();
		httpConfig.setOutputBufferSize(32768);

		ServerConnector http = new ServerConnector(server, new HttpConnectionFactory(httpConfig));
		http.setPort(8080);
		http.setIdleTimeout(1000 * 60 * 60);
		server.addConnector(http);

		WebAppContext webAppContext = new WebAppContext();
		webAppContext.setServer(server);
		webAppContext.setContextPath("/");
		webAppContext.setWar("src/main/webapp");

		ServletContextHandler contextHandler =
				ServletContextHandler.getServletContextHandler(webAppContext.getServletContext());
		JakartaWebSocketServletContainerInitializer.configure(
				contextHandler,
				(servletContext, container) -> container.addEndpoint(new WicketServerEndpointConfig())
		);

		server.setHandler(webAppContext);

		MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
		MBeanContainer mBeanContainer = new MBeanContainer(mBeanServer);
		server.addEventListener(mBeanContainer);
		server.addBean(mBeanContainer);
	}

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

	public static void main(String[] args) {
		try {
			initServer();
			server.start();
			server.join();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(100);
		}
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

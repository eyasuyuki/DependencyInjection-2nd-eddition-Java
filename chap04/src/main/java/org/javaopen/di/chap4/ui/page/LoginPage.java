package org.javaopen.di.chap4.ui.page;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.cycle.RequestCycle;
import org.javaopen.di.chap4.ui.security.AuthSession;

import java.util.Currency;
import java.util.Locale;

public class LoginPage extends WebPage {
    private String username;
    private String password;
    public LoginPage() {

        Form<Void> loginForm = new Form<Void>("loginForm") {
            @Override
            protected void onSubmit() {
                if (AuthSession.get().signIn(username, password)) {
                    setResponsePage(HomePage.class);
                } else {
                    error("Invalid username or password");
                }
            }
        };
        loginForm.add(new TextField<>("username", new PropertyModel<>(this, "username")));
        loginForm.add(new PasswordTextField("password", new PropertyModel<>(this, "password")));

        add(loginForm);
    }

}

package org.javaopen.di.chap4.ui;

import org.apache.wicket.IPageFactory;
import org.apache.wicket.request.component.IRequestablePage;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.javaopen.di.chap4.data.CommerceDao;
import org.javaopen.di.chap4.data.SqlProductRepository;
import org.javaopen.di.chap4.domain.FixedCurrencyConverter;
import org.javaopen.di.chap4.domain.ICurrencyConverter;
import org.javaopen.di.chap4.domain.IProductRepository;
import org.javaopen.di.chap4.domain.IProductService;
import org.javaopen.di.chap4.domain.IUserContext;
import org.javaopen.di.chap4.domain.ProductService;
import org.javaopen.di.chap4.ui.page.HomePage;
import org.javaopen.di.chap4.ui.page.LoginPage;
import org.javaopen.di.chap4.ui.security.WebSessionUserContext;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * 合成起点(Composition root)
 * 元のStartupに相当するクラス
 * HomePageに対してコンストラクタインジェクションを行う
 */
public class StartupPageFactory implements IPageFactory {
    private IUserContext userContext;
    private IProductRepository productRepository;
    private ICurrencyConverter currencyConverter;
    private IProductService productService;

    public StartupPageFactory() {
        userContext = new WebSessionUserContext();
        // product repository
        productRepository = new SqlProductRepository(new CommerceDao(getJdbcUrl()));
        // currency converter
        currencyConverter = new FixedCurrencyConverter();
        // product service
        productService = new ProductService(productRepository, userContext, currencyConverter);
    }

    @Override
    public <C extends IRequestablePage> C newPage(Class<C> pageClass) {
        if (LoginPage.class.equals(pageClass)) {
            return (C) new LoginPage();
        } else if (HomePage.class.equals(pageClass)) {
            return (C) new HomePage(productService);
        }
        try {
            return pageClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("instantiation failed: " + pageClass.getName(), e);
        }
    }

    @Override
    public <C extends IRequestablePage> C newPage(Class<C> pageClass, PageParameters pageParameters) {
        return newPage(pageClass);
    }

    @Override
    public <C extends IRequestablePage> boolean isBookmarkable(Class<C> pageClass) {
        if (LoginPage.class.equals(pageClass)) {
            return true;
        } else if (HomePage.class.equals(pageClass)) {
            return false;
        }
        return false;
    }


    private String getJdbcUrl() {
        try {
            Context env = (Context) new InitialContext().lookup("java:comp/env");
            return (String) env.lookup("jdbc/sqlite");
        } catch (NamingException e) {
            throw new RuntimeException("JDBC URL lookup failed.", e);
        }
    }

}

package org.javaopen.di.chap3.ui.page;

import org.apache.wicket.Page;
import org.apache.wicket.Session;
import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.util.tester.WicketTester;
import org.javaopen.di.chap3.domain.DiscountedProduct;
import org.javaopen.di.chap3.domain.IProductService;
import org.javaopen.di.chap3.ui.model.ProductViewModel;
import org.javaopen.di.chap3.ui.security.AuthSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HomePageTest {
    private WicketTester wicketTester;

    @BeforeEach
    public void beforeEach() {
        this.wicketTester = new WicketTester(new StubWebApplication());
    }

    @Test
    public void testCreateWithNullProductService() {
        assertThrows(IllegalStateException.class, () -> new HomePage(null));
    }

    @Test
    public void testCreateWillReturnInstance() {
        HomePage homePage = new HomePage(new StubProductService());
        assertThat(homePage).isNotNull();
    }

    @Test
    public void testBuildPage() {
        IProductService stubProductService = new StubProductService();
        HomePage homePage = new HomePage(stubProductService);
        wicketTester.startPage(homePage);
        ListView<?> products = (ListView<?>)wicketTester.getLastRenderedPage().get("products");
        List<?> productList = (List<?>)products.getDefaultModelObject();
        List<ProductViewModel> expected = stubProductService.getFeaturedProducts().stream()
            .map(ProductViewModel::new).toList();
        assertThat(productList).isEqualTo(expected);
    }

    static class StubWebApplication extends WebApplication {
        @Override
        public Class<? extends Page> getHomePage() {
            return HomePage.class;
        }

        @Override
        public Session newSession(Request request, Response response) {
            return new AuthSession(request);
        }
    }

    static class StubProductService implements IProductService {

        @Override
        public List<DiscountedProduct> getFeaturedProducts() {
            List<DiscountedProduct> products = Arrays.asList(new DiscountedProduct[] {
                new DiscountedProduct("A", 1.0),
                new DiscountedProduct("B", 2.0)
            });
            return products;
        }
    }
}

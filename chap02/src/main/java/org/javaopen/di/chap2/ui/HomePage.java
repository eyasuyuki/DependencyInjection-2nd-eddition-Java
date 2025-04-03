package org.javaopen.di.chap2.ui;

import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.util.ListModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.WebPage;
import org.javaopen.di.chap2.data.Product;
import org.javaopen.di.chap2.domain.ProductService;

import java.io.Serial;
import java.util.List;

/**
 * リスト 2.4
 */
public class HomePage extends WebPage {
	@Serial
	private static final long serialVersionUID = 1L;

    public HomePage(final PageParameters parameters) {
		super(parameters);

		add(new Label("version", getApplication().getFrameworkSettings().getVersion()));

		final boolean isFeatured = AuthSession.get().getRoles().hasRole("preferredCustomer");

		final ProductService service = new ProductService();
        final List<Product> products = service.getFeaturedProducts(isFeatured);
		final ListModel<Product> model = new ListModel<>(products);
		add(new ListView<>("products", model) {
			@Override
			protected void populateItem(ListItem<Product> item) {
				item.add(new Label("name", item.getModelObject().getName()));
				item.add(new Label("price", item.getModelObject().getUnitPrice()));
			}
		});
	}
}

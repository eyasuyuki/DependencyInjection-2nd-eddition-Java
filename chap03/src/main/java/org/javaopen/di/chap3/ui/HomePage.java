package org.javaopen.di.chap3.ui;

import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.util.ListModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.WebPage;
import org.javaopen.di.chap3.data.Product;
import org.javaopen.di.chap3.domain.ProductService;
import org.javaopen.di.chap3.ui.model.FeaturedProductsViewModel;
import org.javaopen.di.chap3.ui.model.ProductViewModel;

import java.awt.*;
import java.io.Serial;
import java.util.Arrays;
import java.util.List;

public class HomePage extends WebPage {
	@Serial
	private static final long serialVersionUID = 1L;

	public HomePage(final PageParameters parameters) {
		super(parameters);

		final boolean isFeatured = AuthSession.get().getRoles().hasRole("preferredCustomer");

//		final ProductService service = new ProductService();
//		final List<Product> products = service.getFeaturedProducts(isFeatured);
		final FeaturedProductsViewModel vm = new FeaturedProductsViewModel(Arrays.asList(
			new ProductViewModel("Chocolate", 34.95),
			new ProductViewModel("Asparagus", 39.80)
		));
		final ListModel<ProductViewModel> model = new ListModel<>(vm.getProducts());
		add(new ListView<>("products", model) {
			@Override
			protected void populateItem(ListItem<ProductViewModel> item) {
				item.add(new Label("summaryText", item.getModelObject().getSummaryText()));
			}
		});
	}
}

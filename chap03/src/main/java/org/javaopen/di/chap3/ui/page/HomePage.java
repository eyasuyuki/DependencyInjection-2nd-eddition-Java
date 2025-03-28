package org.javaopen.di.chap3.ui.page;

import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.util.ListModel;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.WebPage;
import org.javaopen.di.chap3.domain.DiscountedProduct;
import org.javaopen.di.chap3.domain.IProductService;
import org.javaopen.di.chap3.ui.security.AuthSession;
import org.javaopen.di.chap3.ui.model.FeaturedProductsViewModel;
import org.javaopen.di.chap3.ui.model.ProductViewModel;

import java.io.Serial;
import java.util.List;
import java.util.stream.Collectors;

public class HomePage extends WebPage {
	@Serial
	private static final long serialVersionUID = 1L;

	private IProductService productService;

	public HomePage(IProductService productService) {
		if (productService == null) {
			throw new IllegalStateException("productService is null");
		}

		List<DiscountedProduct> products = productService.getFeaturedProducts();

		FeaturedProductsViewModel vm = new FeaturedProductsViewModel(
			products.stream()
			.map(x -> new ProductViewModel(x.getName(), x.getUnitPrice()))
			.collect(Collectors.toList()));

		final boolean isFeatured = AuthSession.get().getRoles().hasRole("preferredCustomer");

		final ListModel<ProductViewModel> model = new ListModel<>(vm.getProducts());
		add(new ListView<>("products", model) {
			@Override
			protected void populateItem(ListItem<ProductViewModel> item) {
				item.add(new Label("summaryText", item.getModelObject().getSummaryText()));
			}
		});
	}
}

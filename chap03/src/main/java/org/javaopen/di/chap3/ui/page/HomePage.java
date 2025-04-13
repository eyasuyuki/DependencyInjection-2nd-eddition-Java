package org.javaopen.di.chap3.ui.page;

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.util.ListModel;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.WebPage;
import org.javaopen.di.chap3.domain.DiscountedProduct;
import org.javaopen.di.chap3.domain.IProductService;
import org.javaopen.di.chap3.domain.Role;
import org.javaopen.di.chap3.ui.security.AuthSession;
import org.javaopen.di.chap3.ui.model.FeaturedProductsViewModel;
import org.javaopen.di.chap3.ui.model.ProductViewModel;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * リスト 3.4
 */
public class HomePage extends WebPage implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;

	private transient IProductService productService; // シリアライズさせない

	public HomePage(IProductService productService) {
		if (productService == null) {
			throw new IllegalStateException("productService is null");
		}

		this.productService = productService;

		buildPage();
	}

	protected void buildPage() {

		List<DiscountedProduct> products = productService.getFeaturedProducts();

		FeaturedProductsViewModel vm = new FeaturedProductsViewModel(
			products.stream()
			.map(ProductViewModel::new)
			.collect(Collectors.toList()));

		final boolean isFeatured = getAuthSession().getRoles().hasRole(Role.PREFERRED_CUSTOMER.getValue());

		final ListModel<ProductViewModel> model = new ListModel<>(vm.getProducts());
		add(new ListView<>("products", model) {
			@Override
			protected void populateItem(ListItem<ProductViewModel> item) {
				item.add(new Label("summaryText", item.getModelObject().getSummaryText()));
			}
		});
	}

	protected AuthenticatedWebSession getAuthSession() {
		return AuthSession.get();
	}
}

package hhplus.ecommerce.domain.product;

import hhplus.ecommerce.api.dto.request.OrderRequest;
import hhplus.ecommerce.domain.cart.cartitem.NewCartItem;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {
    private final ProductReader productReader;
    private final ProductUpdator productUpdator;
    private final ProductValidator productValidator;

    public ProductService(ProductReader productReader, ProductUpdator productUpdator, ProductValidator productValidator) {
        this.productReader = productReader;
        this.productUpdator = productUpdator;
        this.productValidator = productValidator;
    }

    public List<Product> readProductInfo() {
        return productReader.retrieveAll();
    }

    public Product readProductInfoDetail(Long productId) {
        return productReader.retrieveById(productId);
    }

    public List<Product> readPopularProducts() {
        return productReader.retrievePopularProducts();
    }

    public List<Product> readProductsByIds(List<Long> productIds) {
        return productReader.retrieveAllByIds(productIds);
    }

    public void updateStockCount(List<Product> products, List<OrderRequest.ProductOrderRequest> orderRequests) {
        productUpdator.updateStock(products, orderRequests);
    }

    public void verifyProductStockForAddToCart(List<NewCartItem> cartItems) {
        productValidator.checkPossibleAddToCart(cartItems);
    }
}
package hhplus.ecommerce.domain.product;

import hhplus.ecommerce.domain.cart.cartitem.NewCartItem;
import hhplus.ecommerce.domain.order.Order;
import org.springframework.cache.annotation.Cacheable;
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

    @Cacheable(value = "products", key = "#productId", cacheManager = "cacheMager", condition = "#user.type == 'USER'")
    public Product readProductInfoDetail(Long productId) {
        return productReader.retrieveById(productId);
    }

    @Cacheable(value = "products", key = "popular", cacheManager = "cacheMager", condition = "#user.type == 'USER'")
    public List<Product> readPopularProducts() {
        return productReader.retrievePopularProducts();
    }

    public List<Product> readProductsByIds(List<Long> productIds) {
        return productReader.retrieveAllByIds(productIds);
    }

    public void verifyProductStockForAddToCart(List<NewCartItem> cartItems) {
        productValidator.checkPossibleAddToCart(cartItems);
    }

    public void updateStockCount(List<Product> products, Order order) {
        productUpdator.updateStock(products, order);
    }
}
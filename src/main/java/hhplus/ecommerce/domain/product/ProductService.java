package hhplus.ecommerce.domain.product;

import hhplus.ecommerce.api.dto.request.OrderRequest;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {
    private final ProductReader productReader;
    private final ProductUpdater productUpdater;

    public ProductService(ProductReader productReader, ProductUpdater productUpdater) {
        this.productReader = productReader;
        this.productUpdater = productUpdater;
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

    public List<Product> readProductsByIds(List<OrderRequest.ProductOrderRequest> products) {
        return productReader.retrieveAllByIds(products);
    }

    public void updateStockCount(List<Product> products, List<OrderRequest.ProductOrderRequest> req) {
        productUpdater.updateStock(products, req);
    }
}
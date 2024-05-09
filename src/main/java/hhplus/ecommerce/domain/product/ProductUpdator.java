package hhplus.ecommerce.domain.product;

import hhplus.ecommerce.domain.order.Order;
import hhplus.ecommerce.domain.orderitem.OrderItem;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductUpdator {
    private final ProductRepository productRepository;

    public ProductUpdator(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void updateStock(List<Product> products, Order order) {
        for (OrderItem item: order.items()) {
            Product product = products.stream()
                    .filter(p -> p.id().equals(item.productId()))
                    .findFirst()
                    .orElseThrow(() -> new EntityNotFoundException(item.productId() + " 상품의 정보를 찾지 못했습니다."));

            Product decreasedProduct = product.decreaseStock(item.quantity());
            productRepository.updateStock(decreasedProduct);
        }
    }

    // Cacheable와 비교
    @CachePut(value = "products", key = "#product.id", unless = "#result == null", cacheManager = "cacheManager", condition = "#caching")
    public Product updateProductStock(Product product) {
        return productRepository.updateStock(product);
    }

}

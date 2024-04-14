package hhplus.ecommerce.domain.product;

import hhplus.ecommerce.api.dto.request.OrderRequest;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductUpdater {
    private final ProductRepository productRepository;

    public ProductUpdater(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void updateStock(List<Product> products, List<OrderRequest.ProductOrderRequest> req) {
        for (OrderRequest.ProductOrderRequest orderRequest : req) {
            Product product = products.stream()
                    .filter(p -> p.id().equals(orderRequest.id()))
                    .findFirst()
                    .orElseThrow(() -> new EntityNotFoundException(orderRequest.id() + " 상품의 정보를 찾지 못했습니다."));

            Product decreasedProduct = product.decreaseStock(orderRequest.orderCount());
            productRepository.updateStock(decreasedProduct);
        }
    }
}

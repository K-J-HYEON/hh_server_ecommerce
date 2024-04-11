package hhplus.ecommerce.product.domain.component;

import hhplus.ecommerce.order.dto.request.OrderReq;
import hhplus.ecommerce.product.domain.Product;
import hhplus.ecommerce.product.infrastructure.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ProductUpdater {
    private final ProductRepository productRepository;

    public ProductUpdater(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void updateStock(List<Product> products, List<OrderReq.ProductOrderReq> productOrderRequests) {
        for (OrderReq.ProductOrderReq orderRequest : productOrderRequests) {
            Product product = products.stream()
                    .filter(p -> p.productId().equals(orderRequest.id()))
                    .findFirst()
                    .orElseThrow(() -> new EntityNotFoundException(orderRequest.id() + " 상품의 정보를 찾지 못했습니다."));

            Product decreasedProduct = product.decreaseStock(orderRequest.quantity());
            productRepository.updateStock(decreasedProduct);
        }
    }
}
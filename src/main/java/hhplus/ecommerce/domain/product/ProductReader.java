package hhplus.ecommerce.domain.product;

import hhplus.ecommerce.api.dto.request.OrderReq;
import hhplus.ecommerce.storage.order.OrderStatus;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class ProductReader {
    private final ProductRepository productRepository;

    public ProductReader(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> retrieveAll() {
        return productRepository.findAll();
    }

    public Product retrieveById(Long produtId) {
        return productRepository.findById(produtId);
    }

    public List<Product> retrieveAllByIds(List<OrderReq.ProductOrderReq> products) {
        return productRepository.findByIdIn(products.stream()
                .map(OrderReq.ProductOrderReq::id)
                .toList()
        );
    }

    public List<Product> readPopularSellingProducts() {
        LocalDateTime endDate = LocalDateTime.now();
        LocalDateTime startDate = endDate.minusDays(3);
        Pageable topFive = PageRequest.of(0, 5);

        return productRepository.readPopularSellingProducts(OrderStatus.PAID, startDate, endDate, topFive);
    }
}

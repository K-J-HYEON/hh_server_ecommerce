package hhplus.ecommerce.domain.orderitem;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderItemReader {
    private final OrderItemRepository orderItemRepository;

    public OrderItemReader(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    public List<OrderItem> readAllByOrderId(Long orderId) {
        return orderItemRepository.findAllByOrderId(orderId);
    }
}

package hhplus.ecommerce.domain.order;

import hhplus.ecommerce.api.dto.request.OrderRequest;
import hhplus.ecommerce.domain.user.User;
import org.springframework.stereotype.Component;

@Component
public class OrderProcessor {

    private final OrderRepository orderRepository;

    public OrderProcessor(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order order(User user, OrderRequest request) {
        return orderRepository.order(user, request);
    }
}

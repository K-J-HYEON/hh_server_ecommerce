package hhplus.ecommerce.domain.order;

import hhplus.ecommerce.api.dto.request.OrderReq;
import hhplus.ecommerce.domain.user.User;
import org.springframework.stereotype.Component;

@Component
public class OrderProcessor {

    private final OrderRepository orderRepository;

    public OrderProcessor(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order order(User user, OrderReq req) {
        return orderRepository.order(user, req);
    }
}
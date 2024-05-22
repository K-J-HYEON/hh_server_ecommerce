package hhplus.ecommerce.domain.order;

import hhplus.ecommerce.api.dto.request.OrderRequest;
import hhplus.ecommerce.domain.cart.Cart;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderAppender {

    private final OrderRepository orderRepository;
    private final OrderProductReader orderProductReader;

    public OrderAppender(OrderRepository orderRepository, OrderProductReader orderProductReader) {
        this.orderRepository = orderRepository;
        this.orderProductReader = orderProductReader;
    }

    public Order append(Long userId, Cart cart, OrderRequest request) {
        List<OrderProduct> orderProduct = orderProductReader.read(cart);
        OrderForm orderForm = OrderForm.of(request, orderProduct);
        return orderRepository.create(userId, orderForm);
    }
}

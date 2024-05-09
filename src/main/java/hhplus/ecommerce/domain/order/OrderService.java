package hhplus.ecommerce.domain.order;

import hhplus.ecommerce.api.dto.request.OrderRequest;
import hhplus.ecommerce.domain.cart.Cart;
import hhplus.ecommerce.domain.orderitem.OrderItem;
import hhplus.ecommerce.domain.user.User;
import hhplus.ecommerce.storage.order.OrderItemStatus;
import hhplus.ecommerce.storage.order.OrderStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    private final OrderAppender orderAppender;
    private final OrderUpdater orderUpdater;

    public OrderService(OrderAppender orderAppender, OrderUpdater orderUpdater) {
        this.orderAppender = orderAppender;
        this.orderUpdater = orderUpdater;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Order order(User user, Cart cart, OrderRequest request) {
        return orderAppender.append(user, cart, request);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateItemStatus(OrderItem item, OrderItemStatus status) {
        orderUpdater.changeItemStatus(item, status);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void orderFailed(Order order) {
        orderUpdater.changeStatus(order, OrderStatus.FAIL);
    }
}

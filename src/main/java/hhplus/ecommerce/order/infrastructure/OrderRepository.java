package hhplus.ecommerce.order.infrastructure;

import hhplus.ecommerce.order.domain.Order;
import hhplus.ecommerce.order.presentation.dto.request.OrderReq;
import hhplus.ecommerce.order.domain.component.OrderStatus;
import hhplus.ecommerce.user.domain.User;

public interface OrderRepository {
    Order order(User user, OrderReq req);

    Order updateStatus(Order order, OrderStatus orderStatus);

    Order findById(Long orderId);
}

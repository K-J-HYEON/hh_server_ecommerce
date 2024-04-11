package hhplus.ecommerce.domain.order;

import hhplus.ecommerce.storage.order.OrderStatus;
import hhplus.ecommerce.api.dto.request.OrderReq;
import hhplus.ecommerce.domain.user.User;

public interface OrderRepository {
    Order order(User user, OrderReq req);

    Order updateStatus(Order order, OrderStatus orderStatus);

    Order findById(Long orderId);
}

package hhplus.ecommerce.domain.order;

import hhplus.ecommerce.storage.order.OrderStatus;

public interface OrderRepository {
//    Order order(User user, OrderRequest request);

    void updateStatus(Order order, OrderStatus orderStatus);

    Order create(Long userId, OrderForm orderForm);

    Order findById(Long orderId);
}
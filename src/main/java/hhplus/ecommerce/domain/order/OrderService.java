package hhplus.ecommerce.domain.order;

import hhplus.ecommerce.api.dto.request.OrderRequest;
import hhplus.ecommerce.domain.orderitem.OrderItem;
import hhplus.ecommerce.domain.orderitem.OrderItemAppender;
import hhplus.ecommerce.domain.product.Product;
import hhplus.ecommerce.domain.user.User;
import hhplus.ecommerce.storage.order.OrderStatus;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderService {

    private final OrderItemAppender orderItemAppender;
    private final OrderProcessor orderProcessor;
    private final OrderUpdater orderUpdater;

    public OrderService(OrderItemAppender orderItemAppender, OrderProcessor orderProcessor, OrderUpdater orderUpdater) {
        this.orderItemAppender = orderItemAppender;
        this.orderProcessor = orderProcessor;
        this.orderUpdater = orderUpdater;
    }

    public Order order(User user, List<Product> products, OrderRequest request) {
        Order order = orderProcessor.order(user, request);

        List<OrderItem> orderItems = orderItemAppender.create(order, products, request.products());
        return orderUpdater.changeStatus(order, OrderStatus.PENDING_FOR_PAY);
    }

    public void updateOrderStatus(Order order, OrderStatus orderStatus) {
        orderUpdater.changeStatus(order, orderStatus);
    }
}

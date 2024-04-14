package hhplus.ecommerce.domain.order;

import hhplus.ecommerce.storage.order.OrderStatus;
import org.springframework.stereotype.Component;

@Component
public class OrderValidator {
    public boolean isOrderStatusPendingForPay(Order order) {
        return order.orderStatus().equals(OrderStatus.PENDING_FOR_PAY.toString());
    }
}

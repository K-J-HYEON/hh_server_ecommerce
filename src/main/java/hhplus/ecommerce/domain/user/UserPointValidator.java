package hhplus.ecommerce.domain.user;

import hhplus.ecommerce.domain.order.Order;
import hhplus.ecommerce.storage.order.OrderStatus;
import hhplus.ecommerce.domain.order.OrderUpdater;
import org.springframework.stereotype.Component;

@Component
public class UserPointValidator {
    private final OrderUpdater orderUpdater;

    public UserPointValidator(OrderUpdater orderUpdater) {
        this.orderUpdater = orderUpdater;
    }

    public void confirmUserPoint(Order order, User user, Long payAmount) {
        if (user.point() < payAmount) {
            orderUpdater.changeStatus(order, OrderStatus.PAY_FAILED);
            throw new IllegalArgumentException("현재 잔액은 " + user.point() + "이고 잔액이 부족합니다.");
        }
    }
}

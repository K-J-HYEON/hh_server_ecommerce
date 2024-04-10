package hhplus.ecommerce.user.component;

import hhplus.ecommerce.order.domain.Order;
import hhplus.ecommerce.order.component.OrderStatus;
import hhplus.ecommerce.order.component.OrderUpdater;
import hhplus.ecommerce.user.domain.User;
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

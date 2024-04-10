package hhplus.ecommerce.order.util;


import hhplus.ecommerce.order.domain.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderValidator {
    public void confirmOrderStatus(Order order) {
        if (order.orderStatus().equals(OrderStatus.CANCELED.toString())) {
            throw new IllegalArgumentException("주문 취소 상태입니다.");
        }

        if (order.orderStatus().equals(OrderStatus.PAID.toString())) {
            throw new IllegalArgumentException("결제 완료된 주문 입니다.");
        }
    }
}

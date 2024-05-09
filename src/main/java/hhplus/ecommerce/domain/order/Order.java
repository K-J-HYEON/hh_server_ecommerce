package hhplus.ecommerce.domain.order;

import hhplus.ecommerce.domain.orderitem.OrderItem;
import hhplus.ecommerce.storage.order.OrderStatus;
import java.time.LocalDateTime;
import java.util.List;

public record Order(
        Long id,
        Long userId,
        Long payAmount,
        List <OrderItem> items,
        String receiverName,
        String address,
        String phoneNumber,
        String orderStatus,
        LocalDateTime orderedAt
) {
    public Order changeStatus(OrderStatus orderStatus) {
        return new Order(
                id,
                userId,
                payAmount,
                items,
                receiverName,
                address,
                phoneNumber,
                orderStatus.toString(),
                orderedAt);
    }
}
package hhplus.ecommerce.domain.order;

import hhplus.ecommerce.storage.order.OrderStatus;
import java.time.LocalDateTime;

public record Order(
        Long id,
        Long payAmount,
        String receiverName,
        String address,
        String phoneNumber,
        String orderStatus,
        LocalDateTime orderedAt
) {
    public Order changeStatus(OrderStatus orderStatus) {
        return new Order(id, payAmount, receiverName, address, phoneNumber, orderStatus.toString(), orderedAt);
    }
}

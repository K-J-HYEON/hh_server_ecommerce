package hhplus.ecommerce.api.dto.response;

import hhplus.ecommerce.domain.order.Order;
import hhplus.ecommerce.api.dto.request.Receiver;
import java.time.format.DateTimeFormatter;

public record OrderRes(
        Long orderId,
        Receiver receiver,
        Long payAmount,
        String status,
        String orderAt
) {
    private final static DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static OrderRes from(Order order) {
        return new OrderRes(
                order.orderId(),
                new Receiver(
                        order.receiverName(),
                        order.address(),
                        order.phoneNumber()
                ),
                order.payAmount(),
                order.orderStatus(),
                order.orderedAt().format(DATE_TIME_FORMATTER)
        );
    }
}

package hhplus.ecommerce.api.dto.response;

import hhplus.ecommerce.domain.order.Order;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "주문 응답 DTO")
public record OrderResponse(Long orderId) {

    public static OrderResponse from(Order order) {
        return new OrderResponse(order.id());
    }

//        @Schema(description = "주문 id")
//        Long orderId,
//
//        @Schema(description = "결제 금액")
//        Long payAmount,
//
//        @Schema(description = "받는 사람")
//        Receiver receiver,
//
//        @Schema(description = "주문 날짜")
//        String orderedAt
//) {
//    private final static DateTimeFormatter DATE_TIME_FORMATTER =
//            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//
//    public static OrderResponse from(OrderPaidResult orderPaidResult) {
//        return new OrderResponse(
//                orderPaidResult.orderId(),
//                orderPaidResult.payAmount(),
//                orderPaidResult.receiver(),
//                orderPaidResult.orderedAt().format(DATE_TIME_FORMATTER)
//        );
//    }

}

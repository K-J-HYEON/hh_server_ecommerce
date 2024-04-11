package hhplus.ecommerce.orderitem.domain;

public record OrderItem(
        Long orderItemId,
        Long orderId,
        Long productId,
        String productName,

        Long count,
        Long price,
        Long totalPrice

) {
}

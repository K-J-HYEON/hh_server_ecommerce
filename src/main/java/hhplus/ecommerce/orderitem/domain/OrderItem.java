package hhplus.ecommerce.orderitem.domain;

public record OrderItem(
        Long id,
        Long orderId,
        Long productId,
        String productName,

        Long count,
        Long price,
        Long totalPrice

) {
}

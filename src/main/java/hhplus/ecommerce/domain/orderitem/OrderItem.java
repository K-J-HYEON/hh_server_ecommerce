package hhplus.ecommerce.domain.orderitem;

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

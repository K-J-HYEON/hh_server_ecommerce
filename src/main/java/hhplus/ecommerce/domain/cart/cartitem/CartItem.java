package hhplus.ecommerce.domain.cart.cartitem;

public record CartItem(
        Long id, Long cartId, Long productId, Long quantity
) {
}

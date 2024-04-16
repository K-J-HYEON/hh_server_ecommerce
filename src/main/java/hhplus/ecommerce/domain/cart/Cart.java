package hhplus.ecommerce.domain.cart;

import hhplus.ecommerce.storage.cart.CartStatus;

import java.time.LocalDateTime;

public record Cart(
        Long id,
        Long userId
) {

    public Cart changeStatus(CartStatus cartStatus) {
        return new Cart(id, userId);
    }
}

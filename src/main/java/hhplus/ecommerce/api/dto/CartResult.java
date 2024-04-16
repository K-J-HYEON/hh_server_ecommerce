package hhplus.ecommerce.api.dto;

import hhplus.ecommerce.domain.cart.Cart;

public record CartResult(
        Long id,
        Long userId
) {

    public static CartResult of(Cart cart) {
        return new CartResult(
                cart.id(),
                cart.userId()
        );
    }
}

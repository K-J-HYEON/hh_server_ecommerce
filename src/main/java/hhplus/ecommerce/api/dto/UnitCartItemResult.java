package hhplus.ecommerce.api.dto;

import hhplus.ecommerce.domain.cart.cartitem.CartItem;
import hhplus.ecommerce.domain.product.Product;

public record UnitCartItemResult(
        Long cartItemId,
        Long productId,
        String productName,
        Long unitPrice,
        Long quantity,
        Long totalPrice
) {
    public static UnitCartItemResult of(CartItem cartItem, Product product) {
        return new UnitCartItemResult(
                cartItem.id(),
                product.id(),
                product.name(),
                product.price(),
                cartItem.quantity(),
                product.price() * cartItem.quantity()
        );
    }
}
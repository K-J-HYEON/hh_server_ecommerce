package hhplus.ecommerce.domain.cart;

import hhplus.ecommerce.domain.cart.cartitem.CartItem;
import java.util.List;

public record Cart(
        Long id,
        Long userId,
        List<CartItem> items
) {
}

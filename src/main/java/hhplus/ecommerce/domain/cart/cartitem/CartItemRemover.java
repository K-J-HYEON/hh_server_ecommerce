package hhplus.ecommerce.domain.cart.cartitem;

import hhplus.ecommerce.domain.cart.CartRepository;
import hhplus.ecommerce.domain.user.User;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class CartItemRemover {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    public CartItemRemover(CartRepository cartRepository, CartItemRepository cartItemRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
    }

    public void removeItems(List<CartItem> cartItems) {
        cartItemRepository.removeItems(cartItems.stream()
                .map(CartItem::id)
                .toList());
    }

    public void resetCart(Long userId) {
        cartRepository.resetCart(userId);
    }
}
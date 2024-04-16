package hhplus.ecommerce.domain.cart;

import hhplus.ecommerce.storage.cart.CartStatus;
import org.springframework.stereotype.Component;

@Component
public class CartUpdater {

    private final CartRepository cartRepository;

    public CartUpdater(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public Cart changeStatus(Cart cart, CartStatus cartStatus) {
        Cart changedStatusCart = cart.changeStatus(cartStatus);
        return cartRepository.updateStatus(changedStatusCart, cartStatus);
    }
}

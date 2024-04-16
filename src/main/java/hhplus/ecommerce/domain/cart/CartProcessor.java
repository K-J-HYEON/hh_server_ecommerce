package hhplus.ecommerce.domain.cart;

import hhplus.ecommerce.api.dto.request.CartRequest;
import hhplus.ecommerce.domain.order.OrderRepository;
import hhplus.ecommerce.domain.user.User;
import org.springframework.stereotype.Component;

@Component
public class CartProcessor {

    private final CartRepository cartRepository;

    public CartProcessor(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public Cart cart(User user, CartRequest request) {
        return cartRepository.cart(user, request);
    }
}

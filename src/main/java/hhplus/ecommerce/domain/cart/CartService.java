package hhplus.ecommerce.domain.cart;

import hhplus.ecommerce.api.dto.request.CartRequest;
import hhplus.ecommerce.domain.product.Product;
import hhplus.ecommerce.domain.user.User;
import hhplus.ecommerce.storage.cart.CartStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    private final CartProcessor cartProcessor;
    private final CartUpdater cartUpdater;

    public CartService(CartProcessor cartProcessor, CartUpdater cartUpdater) {
        this.cartProcessor = cartProcessor;
        this.cartUpdater = cartUpdater;
    }

    public Cart cart(User user, List<Product> products, CartRequest request) {
        Cart cart = cartProcessor.cart(user, request);

        return cartUpdater.changeStatus(cart, CartStatus.READY);
    }
}

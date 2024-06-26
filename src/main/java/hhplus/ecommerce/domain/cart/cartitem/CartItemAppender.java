package hhplus.ecommerce.domain.cart.cartitem;

import hhplus.ecommerce.domain.cart.Cart;
import hhplus.ecommerce.storage.cart.cartitem.CartItemEntity;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
public class CartItemAppender {

    private final CartItemRepository cartItemRepository;

    public CartItemAppender(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    public void addItem(Cart cart, List<NewCartItem> newCartItems) {
        for (NewCartItem cartItem : newCartItems) {
            Optional<CartItemEntity> cartItemEntity = cartItemRepository.findByCartIdAndProductId(cart.id(), cartItem.productId());

            if (cartItemEntity.isEmpty()) {
                cartItemRepository.addItem(cart, cartItem);
            } else {
                cartItemEntity.get().addQuntity(cartItem.quantity());
            }
        }
    }
}
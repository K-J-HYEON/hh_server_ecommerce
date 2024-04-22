package hhplus.ecommerce.domain.cart.cartitem;

import hhplus.ecommerce.domain.cart.Cart;
import hhplus.ecommerce.storage.cart.cartitem.CartItemEntity;

import java.util.List;
import java.util.Optional;


public interface CartItemRepository {

    Optional<CartItemEntity> findByCartIdAndProductId(Long cartId, Long productId);

    void addItem(Cart cart, NewCartItem cartItem);

    void removeItems(List<Long> cartItemIds);

    List<CartItem> findAllByCartId(Long cartId);
}

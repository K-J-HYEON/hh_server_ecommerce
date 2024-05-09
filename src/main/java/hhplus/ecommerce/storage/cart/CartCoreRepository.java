package hhplus.ecommerce.storage.cart;

import hhplus.ecommerce.domain.cart.Cart;
import hhplus.ecommerce.domain.cart.CartRepository;
import hhplus.ecommerce.domain.user.User;
import hhplus.ecommerce.storage.cart.cartitem.CartItemEntity;
import hhplus.ecommerce.storage.cart.cartitem.CartItemJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.Comparator;
import java.util.List;

@Repository
public class CartCoreRepository implements CartRepository {

    private final CartJpaRepository cartJpaRepository;
    private final CartItemJpaRepository cartItemJpaRepository;

    public CartCoreRepository(CartJpaRepository cartJpaRepository, CartItemJpaRepository cartItemJpaRepository) {
        this.cartJpaRepository = cartJpaRepository;
        this.cartItemJpaRepository = cartItemJpaRepository;
    }

    @Override
    public Cart findByUserId(Long userId) {
        CartEntity cartEntity = cartJpaRepository.findByUserId(userId).orElseGet(() -> {
            CartEntity cart = new CartEntity(userId);
            cartJpaRepository.save(cart);
            return cart;
        });
        List<CartItemEntity> items = cartItemJpaRepository.findAllByCartId(cartEntity.getId())
                .stream().filter(item -> !item.isDeleted())
                .sorted(Comparator.comparing(CartItemEntity::getCreateAt).reversed())
                .toList();
        return cartEntity.toCart(items);
    }

    @Override
    public void resetCart(User user) {
        CartEntity cartEntity = cartJpaRepository.findByUserId(user.id()).orElseGet(() -> {
            CartEntity cart = new CartEntity(user.id());
            cartJpaRepository.save(cart);
            return cart;
        });

        cartItemJpaRepository.findAllByCartId(cartEntity.getId())
                .stream().filter(item -> !item.isDeleted())
                .forEach(CartItemEntity::delete);
    }
}

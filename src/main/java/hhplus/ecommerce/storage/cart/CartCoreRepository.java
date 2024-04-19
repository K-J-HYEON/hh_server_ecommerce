package hhplus.ecommerce.storage.cart;

import hhplus.ecommerce.domain.cart.Cart;
import hhplus.ecommerce.domain.cart.CartRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CartCoreRepository implements CartRepository {

    private final CartJpaRepository cartJpaRepository;

    public CartCoreRepository(CartJpaRepository cartJpaRepository) {
        this.cartJpaRepository = cartJpaRepository;
    }

    @Override
    public Cart findByUserId(Long userId) {
        Optional<CartEntity> cart = cartJpaRepository.findByUserId(userId);
        if (cart.isEmpty()) {
            return cartJpaRepository.save(new CartEntity(userId)).toCart();
        } else {
            return cart.get().toCart();
        }
    }
}

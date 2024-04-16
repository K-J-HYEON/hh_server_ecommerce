package hhplus.ecommerce.storage.cart;

import hhplus.ecommerce.domain.cart.CartRepository;
import org.springframework.stereotype.Repository;

@Repository
public class CartCoreRepository implements CartRepository {

    private final CartJpaRepository cartJpaRepository;

    public CartCoreRepository(CartJpaRepository cartJpaRepository) {
        this.cartJpaRepository = cartJpaRepository;
    }
}

package hhplus.ecommerce.domain.cart;

public interface CartRepository {
    Cart findByUserId(Long userId);

    void resetCart(Long userId);
}
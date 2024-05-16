package hhplus.ecommerce.domain.cart;

import hhplus.ecommerce.domain.user.User;

public interface CartRepository {
    Cart findByUserId(Long userId);

    void resetCart(Long userId);
}
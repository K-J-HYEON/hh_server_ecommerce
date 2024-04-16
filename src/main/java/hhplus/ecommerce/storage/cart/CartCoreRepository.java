package hhplus.ecommerce.storage.cart;

import hhplus.ecommerce.api.dto.request.CartRequest;
import hhplus.ecommerce.api.dto.response.CartResponse;
import hhplus.ecommerce.domain.cart.Cart;
import hhplus.ecommerce.domain.cart.CartRepository;
import hhplus.ecommerce.domain.user.User;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class CartCoreRepository implements CartRepository {

    private CartJpaRepository cartJpaRepository;

    public CartCoreRepository(CartJpaRepository cartJpaRepository) {
        this.cartJpaRepository = cartJpaRepository;
    }

    @Override
    public Cart cart(User user, CartRequest request) {
        return null;
    }

    @Override
    public Cart updateStatus(Cart cart, CartStatus cartStatus) {
        return null;
    }

    @Override
    public Cart findById(Long cartId) {
        return cartJpaRepository.findById(cartId)
                .orElseThrow(() -> new EntityNotFoundException("장바구니 정보를 찾지 못했습니다. - id: " + cartId))
                .toCart();
    }
}
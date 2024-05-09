package hhplus.ecommerce.domain.cart.cartitem;

import hhplus.ecommerce.TestFixtures;
import hhplus.ecommerce.domain.cart.CartRepository;
import hhplus.ecommerce.domain.user.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

class CartItemRemoverTest {

    @MockBean
    private CartRepository cartRepository;

    @MockBean
    private CartItemRepository cartItemRepository;

    @InjectMocks
    private CartItemRemover cartItemRemover;

    @Test
    @DisplayName("장바구니 상품 삭제")
    void remove_cart_items() {

        // given
        List<CartItem> cartItems = List.of(
                new CartItem(1L,1L, 1L),
                new CartItem(2L,2L, 1L)
        );

        // when
        cartItemRemover.removeItems(cartItems);

        // then
        verify(cartItemRepository, atLeastOnce()).removeItems(any());

    }

    @Test
    @DisplayName("사용자의 장바구니를 초기화")
    void reset_cart() {

        // Given
        User user = TestFixtures.user(1L);

        // When
        cartItemRemover.resetCart(user);

        // Then
        verify(cartRepository, atLeastOnce()).resetCart(any());
    }
}
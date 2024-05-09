package hhplus.ecommerce.domain.cart;

import hhplus.ecommerce.TestFixtures;
import hhplus.ecommerce.domain.cart.cartitem.CartItem;
import hhplus.ecommerce.domain.product.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class CartFinderTest {

    @MockBean
    private CartRepository cartRepository;

    @InjectMocks
    private CartFinder cartFinder;

    @BeforeEach
    void setUp() {
        cartRepository = mock(CartRepository.class);

        cartFinder = new CartFinder(cartRepository);
    }

    @Test
    @DisplayName("사용자 바구니 조회")
    void find_cart_by_user_id() {
        // given
        Long userId = 1L;

        Product product = TestFixtures.product("신발");
        Cart cart = new Cart(1L, userId, List.of(
                new CartItem(1L, product.id(), 5L)
        ));

        given(cartRepository.findByUserId(userId));

        // when
        Cart foundCart = cartFinder.findByUserId(userId);

        // then
        assertThat(foundCart.userId());
    }

}
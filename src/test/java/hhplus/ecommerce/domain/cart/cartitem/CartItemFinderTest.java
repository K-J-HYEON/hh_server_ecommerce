package hhplus.ecommerce.domain.cart.cartitem;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

class CartItemFinderTest {

    @MockBean
    private CartItemRepository cartItemRepository;

    @InjectMocks
    private CartItemFinder cartItemFinder;

//    @BeforeEach
//    void setUp() {
//        cartItemRepository = mock(CartItemRepository.class);
//
//        cartItemFinder = new CartItemFinder(cartItemRepository);
//    }


    @Test
    @DisplayName("장바구니 id 이용하여 장바구니 상품 조회")
    void find_all_cart_items() {

        // given
        Long cartId = 1L;
        Long productId = 1L;
        Long quantity = 1L;

        List<CartItem> cartItems = List.of(
                new CartItem(1L, cartId, productId, quantity)
        );

        given(cartItemRepository.findAllByCartId(any())).willReturn(cartItems);

        // when
        List<CartItem> foundCartItems = cartItemFinder.findAllByCartId(cartId);

        // then
        assertThat(foundCartItems.size()).isEqualTo(1);
    }
}
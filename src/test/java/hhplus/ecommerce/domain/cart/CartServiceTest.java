package hhplus.ecommerce.domain.cart;

import hhplus.ecommerce.TestFixtures;
import hhplus.ecommerce.domain.cart.cartitem.*;
import hhplus.ecommerce.domain.product.Product;
import hhplus.ecommerce.domain.user.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

class CartServiceTest {

    @MockBean
    private CartFinder cartFinder;

    @MockBean
    private CartItemFinder cartItemFinder;

    @MockBean
    private CartItemAppender cartItemAppender;

    @MockBean
    private CartItemRemover cartItemRemover;

    @InjectMocks
    private CartService cartService;

    @Test
    @DisplayName("사용자 장바구니 조회")
    void get_cart() {

        // given
        User user = TestFixtures.user(1L);
        Product product = TestFixtures.product("신발");
        Cart cart = new Cart(1L, user.id(), List.of(
                new CartItem(1L, product.id(), 5L)
        ));

        given(cartFinder.findByUserId(any())).willReturn(cart);

        // when
        Cart cart2 = cartService.getCart(user);

        // then
        assertThat(cart2).isNotNull();
        assertThat(cart2.userId()).isEqualTo(1L);
        assertThat(cart2.items()).isEqualTo(1L);
    }

    @Test
    @DisplayName("장바구니의 아이템 추가")
    void add_item_to_cart() {

        // given
        User user = TestFixtures.user(1L);
        Product product = TestFixtures.product("신발");

        Cart cart = new Cart(1L, user.id(), List.of(
                new CartItem(1L, product.id(), 5L)
        ));

        List<NewCartItem> newCartItems = List.of(
                new NewCartItem(1L, 1L)
        );

        // when
        cartService.addItemToCart(cart, newCartItems);

        // then
        verify(cartItemAppender, atLeastOnce()).addItem(any(), anyList());
    }

    @Test
    @DisplayName("장바구니 상품 중 선택한 상품만 조회")
    void get_selected_cart_items() {

        // given
        Long userId = 1L;

        List<Long> selectedCartItemIds = List.of(10L, 20L);

        List<CartItem> cartAllItem = List.of(
                new CartItem(10L, 1L, 1L),
                new CartItem(10L, 2L, 1L),
                new CartItem(10L, 3L, 1L),
                new CartItem(10L, 4L, 1L)
        );

        Cart cart = new Cart(1L, userId, cartAllItem);

        given(cartItemFinder.findAllByCartId(any())).willReturn(cartAllItem);

        // when
        List<CartItem> selectedCartItems = cartService.getCartItemsByIds(cart, selectedCartItemIds);

        // then
        assertThat(selectedCartItems.size()).isEqualTo(2);
//        assertThat(selectedCartItems.getFirst().id()).isEqualTo(10L);
//        assertThat(selectedCartItems.getLast().id()).isEqualTo(12L);
    }

    @Test
    @DisplayName("장바구니에 담긴 모든 상품 조회")
    void get_all_cart_items() {
        // given
        Long userId = 1L;
        Product sample1 = TestFixtures.product("신발");
        Product sample2 = TestFixtures.product("바지");
        List<CartItem> cartItems = List.of(
                new CartItem(1L, sample1.id(), sample1.stockCount()),
                new CartItem(1L, sample2.id(), sample2.stockCount())
        );

        Cart cart = new Cart(1L, userId, cartItems);

        given(cartItemFinder.findAllByCartId(any())).willReturn(cartItems);

        // when
        List<CartItem> foundCartItems = cartService.getAllCartItems(cart);

        // then
        assertThat(foundCartItems.size()).isEqualTo(2);
        assertThat(foundCartItems.get(0).productId()).isEqualTo(1L);
        assertThat(foundCartItems.get(1).productId()).isEqualTo(2L);
    }

    @Test
    @DisplayName("장바구니 상품 삭제")
    void delete_cart_items() {

        // given
        List<CartItem> cartItems = List.of(
                new CartItem(1L, 1L, 1L),
                new CartItem(2L, 3L, 1L)
        );

        // when
        cartService.deleteItem(cartItems);

        // then
        verify(cartItemRemover, atLeastOnce()).removeItems(any());
    }

    @Test
    @DisplayName("사용자의 장바구니 초기화")
    void reset_cart_items() {
        // given
        User user = TestFixtures.user(1L);

        // when
        cartService.resetCart(user);

        // then
        verify(cartItemRemover, atLeastOnce()).resetCart(any());
    }
}
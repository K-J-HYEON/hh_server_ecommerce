package hhplus.ecommerce.application;

import hhplus.ecommerce.TestFixtures;
import hhplus.ecommerce.api.dto.CartItemResult;
import hhplus.ecommerce.domain.cart.Cart;
import hhplus.ecommerce.domain.cart.CartService;
import hhplus.ecommerce.domain.cart.cartitem.CartItem;
import hhplus.ecommerce.domain.cart.cartitem.NewCartItem;
import hhplus.ecommerce.domain.product.Product;
import hhplus.ecommerce.domain.product.ProductService;
import hhplus.ecommerce.domain.user.User;
import hhplus.ecommerce.domain.user.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

class CartUseCaseTest {

    @MockBean
    private UserService userService;

    @MockBean
    private CartService cartService;

    @MockBean
    private ProductService productService;

    @InjectMocks
    private CartUseCase cartUseCase;

//    @BeforeEach
//    void setUp() {
//        userService = mock(UserService.class);
//        cartService = mock(CartService.class);
//        productService = mock(ProductService.class);
//
//        cartUseCase = new CartUseCase(userService, cartService, productService);
//    }

    @Test
    @DisplayName("상품을 장바구니에 추가")
    void add_item_tocart() {

        // given
        Long userId = 1L;
        Long cartId = 1L;
        Long productId = 1L;
        List<NewCartItem> cartItems = List.of(
                new NewCartItem(productId, 1L)
        );

        // when
        cartUseCase.addItem(userId, cartItems);

        // then
        verify(productService, atLeastOnce()).verifyProductStockForAddToCart(any());
        verify(cartService, atLeastOnce()).addItemToCart(any(), anyList());
    }

    @Test
    @DisplayName("장바구니 상품 삭제")
    void deleteCartItems() {
        // given
        Long userId = 1L;
        Long cartId = 1L;
        List<Long> cartItemIds = List.of();

        User user = TestFixtures.user(userId);

        given(userService.getUser(any())).willReturn(user);
        given(cartService.getCart(any())).willReturn(new Cart(1L, user.id()));
        given(cartService.getCartItemsByIds(any(), any())).willReturn(List.of(
                new CartItem(1L, 1L, 1L, 1L)
        ));

        // when
        cartUseCase.deleteItem(userId, cartItemIds);

        // then
        verify(cartService, atLeastOnce()).deleteItem(any());
    }

    @Test
    @DisplayName("장바구니 추가된 상품 조회")
    void getCartItems() {

        // given
        Long cartId = 1L;
        Long userId = 1L;

        User user = TestFixtures.user(userId);

        Product sample1 = TestFixtures.product("신발");
        Product sample2 = TestFixtures.product("바지");

        Cart cart = new Cart(1L, userId);
        List<CartItem> cartItems = List.of(
                new CartItem(1L, cart.id(), sample1.id(), 1L),
                new CartItem(1L, cart.id(), sample2.id(), 2L)
        );

        List<Product> products = List.of(
                sample1,
                sample2
        );

        given(userService.getUser(any())).willReturn(user);
        given(cartService.getCart(any())).willReturn(cart);
        given(cartService.getAllCartItems(any())).willReturn(cartItems);
        given(productService.readProductsByIds(any())).willReturn(products);

        // when
        CartItemResult foundCartItems = cartUseCase.getCartItems(userId);

        // then
        assertThat(foundCartItems).isNotNull();
        assertThat(foundCartItems.cartItems().size()).isEqualTo(2);
//        assertThat(foundCartItems.cartItems().getFirst().productName()).isEqualTo("신발");
//        assertThat(foundCartItems.cartItems().getFirst().unitPrice()).isEqualTo(90_000L);
//        assertThat(foundCartItems.cartItems().getLast().productName()).isEqualTo("바지");
//        assertThat(foundCartItems.cartItems().getLast().unitPrice()).isEqualTo(10_000L);
//        assertThat(foundCartItems.totalPrice()).isEqualTo(90_000L + 10_000L);
    }
}
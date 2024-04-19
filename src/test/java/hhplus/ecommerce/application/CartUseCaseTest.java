package hhplus.ecommerce.application;

import hhplus.ecommerce.domain.cart.CartService;
import hhplus.ecommerce.domain.cart.cartitem.NewCartItem;
import hhplus.ecommerce.domain.product.ProductService;
import hhplus.ecommerce.domain.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import java.util.List;
import static org.mockito.Mockito.*;

class CartUseCaseTest {

    private UserService userService;
    private CartService cartService;
    private ProductService productService;

    @InjectMocks
    private CartUseCase cartUseCase;

    @BeforeEach
    void setUp() {
        userService = mock(UserService.class);
        cartService = mock(CartService.class);
        productService = mock(ProductService.class);

        cartUseCase = new CartUseCase(userService, cartService, productService);
    }

    @Test
    @DisplayName("상품을 장바구니에 추가")
    void add_item_tocart() {

        // given
        Long userId = 1L;
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
}
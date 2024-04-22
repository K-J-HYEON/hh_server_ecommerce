package hhplus.ecommerce.domain.cart.cartitem;

import hhplus.ecommerce.TestFixtures;
import hhplus.ecommerce.domain.cart.Cart;
import hhplus.ecommerce.domain.product.Product;
import hhplus.ecommerce.storage.cart.cartitem.CartItemEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

class CartItemAppenderTest {

    @MockBean
    private CartItemRepository cartItemRepository;

    @InjectMocks
    private CartItemAppender cartItemAppender;

    @BeforeEach
    void setUp() {
        cartItemRepository = mock(CartItemRepository.class);

        cartItemAppender = new CartItemAppender(cartItemRepository);
    }

    @Test
    @DisplayName("이미 장바구니에 추가된 상품을 추가할 경우 아이템 수량을 증가시키는 메서드 호출")
    void when_already_added_cart_then_add_quantity_cart_item() {

        // given
        Cart cart = new Cart(1L, 1L);
        Product product = TestFixtures.product("신발");

        List<NewCartItem> cartItems = List.of(
                new NewCartItem(product.id(), 1L)
        );

        CartItemEntity cartItemEntity = mock(CartItemEntity.class);

        given(cartItemRepository.findByCartIdAndProductId(any(), any())).willReturn(Optional.of(cartItemEntity));

        // when
        cartItemAppender.addItem(cart, cartItems);

        // then
        verify(cartItemEntity, atLeastOnce()).addQuntity(any());
    }

    @Test
    @DisplayName("장바구니에 없는 상품 추가하면 장바구니 아이템 생성")
    void not_exist_cart_item_then_create_cart_item() {

        // given
        Cart cart = new Cart(1L, 1L);
        Product product = TestFixtures.product("신발");

        List<NewCartItem> cartItems = List.of(
                new NewCartItem(product.id(), 1L)
        );

        given(cartItemRepository.findByCartIdAndProductId(any(), any())).willReturn(Optional.empty());

        // when
        cartItemAppender.addItem(cart, cartItems);

        // then
        verify(cartItemRepository, atLeastOnce()).addItem(any(), any());
    }

    @Test
    @DisplayName("장바구니에 여러 상품 추가하면 해당 상품의 수 만큼 장바구니 아이템 생성")
    void add_many_product_to_cart_then_call_create_cart_item() {
        Cart cart = new Cart(1L, 1L);

        Product sample1 = TestFixtures.product("신발");
        Product sample2 = TestFixtures.product("바지");
        Product sample3 = TestFixtures.product("아우터");

        List<NewCartItem> cartItems = List.of(
                new NewCartItem(sample1.id(), 1L),
                new NewCartItem(sample2.id(), 1L),
                new NewCartItem(sample3.id(), 1L)
        );

        given(cartItemRepository.findByCartIdAndProductId(any(), any())).willReturn(Optional.empty());

        // when
        cartItemAppender.addItem(cart, cartItems);

        // then
        verify(cartItemRepository, times(3)).addItem(any(), any());
    }
}
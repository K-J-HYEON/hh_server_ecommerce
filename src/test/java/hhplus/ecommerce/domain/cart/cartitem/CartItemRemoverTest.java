package hhplus.ecommerce.domain.cart.cartitem;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

class CartItemRemoverTest {

    @MockBean
    private CartItemRepository cartItemRepository;

    @InjectMocks
    private CartItemRemover cartItemRemover;

    @Test
    @DisplayName("장바구니 상품 삭제")
    void remove_cart_items() {

        // given
        List<CartItem> cartItems = List.of(
                new CartItem(1L, 1L, 1L, 1L),
                new CartItem(2L, 1L, 2L, 1L)
        );

        // when
        cartItemRemover.removeItems(cartItems);

        // then
        verify(cartItemRepository, atLeastOnce()).removeItems(any());


    }


}
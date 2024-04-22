package hhplus.ecommerce.api.dto.request;

import hhplus.ecommerce.domain.cart.cartitem.NewCartItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CartItemRequestTest {

    @Test
    @DisplayName("장바구니에 추가할 상품이 0개이면 예외 발생")
    void add_to_cart_item_is_zero_then_throw_error() {

        // given
        CartItemRequest cartItemRequest = new CartItemRequest(
                List.of(
                        new CartItemRequest.CartItem(1L, 0L)
                )
        );

        // when & then
        assertThrows(IllegalArgumentException.class, () -> {
            cartItemRequest.toNewCartItem();
        });
    }

    @Test
    @DisplayName("장바구니에 추가할 상품이 0 이상이면 예외가 발생하지 않음")
    void add_to_cart_item_isnot_zero_not_throw_error() {
        // given
        CartItemRequest cartItemRequest = new CartItemRequest(
                List.of(
                        new CartItemRequest.CartItem(1L, 1L)
                )
        );

        // when
        List<NewCartItem> newCartItems = cartItemRequest.toNewCartItem();

        // then
        assertThat(newCartItems.size()).isEqualTo(1);
//        assertThat(newCartItems.getFirst().productId()).isEqualTo(1L);
    }
}
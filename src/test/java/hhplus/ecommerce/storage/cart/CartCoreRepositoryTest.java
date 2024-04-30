package hhplus.ecommerce.storage.cart;

import hhplus.ecommerce.TestFixtures;
import hhplus.ecommerce.domain.cart.Cart;
import hhplus.ecommerce.domain.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.given;

class CartCoreRepositoryTest {

    @MockBean
    private CartJpaRepository cartJpaRepository;

    @InjectMocks
    private CartCoreRepository cartCoreRepository;

    @Test
    @DisplayName("사용자 장바구니가 없으면 장바구니를 만듬")
    void not_exist_user_cart_then_create_cart() {

        // given
        User user = TestFixtures.user(1L);

        CartEntity cartEntity = new CartEntity(user.id());
        given(cartJpaRepository.findByUserId(user.id())).willReturn(Optional.empty());
        given(cartJpaRepository.save(any())).willReturn(cartEntity);

        // when
        Cart newCart = cartCoreRepository.findByUserId(user.id());

        // then
        assertThat(newCart).isNotNull();
        assertThat(newCart.userId()).isEqualTo(1L);
    }

}
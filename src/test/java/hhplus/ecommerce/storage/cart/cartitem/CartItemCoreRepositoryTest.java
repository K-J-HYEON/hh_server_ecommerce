package hhplus.ecommerce.storage.cart.cartitem;

import hhplus.ecommerce.TestFixtures;
import hhplus.ecommerce.domain.cart.Cart;
import hhplus.ecommerce.domain.cart.cartitem.CartItem;
import hhplus.ecommerce.domain.cart.cartitem.NewCartItem;
import hhplus.ecommerce.domain.product.Product;
import hhplus.ecommerce.storage.cart.CartCoreRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CartItemCoreRepositoryTest {

    @Autowired
    private CartItemCoreRepository cartItemCoreRepository;

    @Autowired
    private CartCoreRepository cartCoreRepository;

    @Test
    @Transactional
    @DisplayName("장바구니 상품 조회, 추가, 삭제 확인을 위한 테스트")
    void test_cartitem_cycle() {

        // 장바구니 조회
        Long userId = 1L;
        Product product = TestFixtures.product("후드티");

        Cart cart = cartCoreRepository.findByUserId(userId);

        // 상품 추가
        NewCartItem newCartItem = new NewCartItem(product.id(), 1L);
        cartItemCoreRepository.addItem(cart, newCartItem);

        // 상품 조회
        List<CartItem> cartItems = cartItemCoreRepository.findAllByCartId(cart.id());

        assertThat(cartItems.size()).isEqualTo(1);
        assertThat(cartItems.get(0).productId()).isEqualTo(product.id());

        // 상품 삭제
        cartItemCoreRepository.removeItems(List.of(cartItems.get(0).id()));

        // 삭제되었는지 확인
        List<CartItem> deletedCartItems = cartItemCoreRepository.findAllByCartId(cart.id());
        assertThat(deletedCartItems.size()).isEqualTo(0);
    }
}
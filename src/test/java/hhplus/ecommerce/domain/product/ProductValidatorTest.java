package hhplus.ecommerce.domain.product;

import hhplus.ecommerce.TestFixtures;
import hhplus.ecommerce.domain.cart.cartitem.NewCartItem;
import hhplus.ecommerce.storage.product.ProductEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class ProductValidatorTest {

    private ProductRepository productRepository;
    private ProductValidator productValidator;

    @BeforeEach
    void setUp() {
        productRepository = mock(ProductRepository.class);

        productValidator = new ProductValidator(productRepository);
    }

    @Test
    @DisplayName("장바구니에 담으려는 상품의 재고가 부족한 경우 에러발생")
    void product_stock_add_to_cart_not_enough_throws_error() {
        Long quantity = 50L;
        Product product = TestFixtures.product("신발");

        List<NewCartItem> cartItems = List.of(
                new NewCartItem(product.id(), quantity)
        );

        given(productRepository.findById(any())).willReturn(Optional.of(
                new ProductEntity(product.name(), product.price(), product.stockCount(), product.size(), product.color())
        ));

        assertThrows(IllegalArgumentException.class, () -> {
            productValidator.checkPossibleAddToCart(cartItems);
        });
    }

    @Test
    @DisplayName("장바구니에 담으려는 상품 재고가 부족하지 않으면 에러발생하지 않음")
    void product_stock_add_to_cart_enough_not_throws_error() {
        Long quantity = 1L;
        Product product = TestFixtures.product("신발");

        List<NewCartItem> cartItems = List.of(
                new NewCartItem(product.id(), quantity)
        );

        given(productRepository.findById(any())).willReturn(Optional.of(
                new ProductEntity(product.name(), product.price(), product.stockCount(), product.size(), product.color())
        ));

        assertDoesNotThrow(() -> {
            productValidator.checkPossibleAddToCart(cartItems);
        });
    }

}
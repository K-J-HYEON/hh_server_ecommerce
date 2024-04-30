package hhplus.ecommerce.domain.product;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProductTest {

    @Test
    @DisplayName("상품 주문 총 금액을 계산한다.")
    void total_order_price_calculate() {

        // given
        Product product = new Product(1L, "신발", 90_000L, 10L, "270", "화이트");
        Long orderQuantity = 2L;

        // when
        Long orderTotalPrice = product.orderTotalPrice(orderQuantity);


        // then
        assertThat(orderQuantity).isEqualTo(90_000L * 3);


    }

    @Test
    @DisplayName("상품 재고 업데이트")
    void product_stock_update() {

        // Given
        Product product = new Product(1L, "신발", 90_000L, 10L, "270", "화이트");

        Long stockQuantity = 5L;

        // When
        Product updatedProduct = product.updateStock(stockQuantity);

        // then
        assertThat(updatedProduct.stockCount()).isEqualTo(5L);
    }

    @Test
    @DisplayName("상품 재고 감소")
    void product_stock_quantity_decrease() {

        // given
        Product product = new Product(1L, "신발", 90_000L, 10L, "270", "화이트");

        Long stockQuantity = 3L;

        // when
        Product decreasedStock = product.decreaseStock(stockQuantity);

        // then
        assertThat(decreasedStock.stockCount()).isEqualTo(10L - 3L);

    }

    @Test
    @DisplayName("상품 재고 확인했을 때 부족하면 예외 발생")
    void stock_quantity_check() {

        // given
        Product product = new Product(1L, "신발", 90_000L, 10L, "270", "화이트");

        Long quantity = 20L;

        // when && then
        assertThrows(IllegalArgumentException.class, () -> {
            product.isEnoughProductStockQuantityForOrder(quantity);
        });
    }
}
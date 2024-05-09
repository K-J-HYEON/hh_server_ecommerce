package hhplus.ecommerce.domain.product;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class StockTest {

    @Test
    @DisplayName("상품 재고 부족한 경우 에러 발생")
    void not_enough_stock_count_throws_error() {

        Stock stock = new Stock(1L, 1L, 10L);
        Long orderCount = 20L;

        assertDoesNotThrow(() -> {
            stock.isEnoughProductStockCountForOrder(orderCount);
        });

    }

    @Test
    @DisplayName("상품 재고 충분하면 에러가 발생하지 않음")
    void enough_stock_count_return_true() {

        Stock stock = new Stock(1L, 1L, 10L);
        Long orderCount = 5L;

        assertDoesNotThrow(() -> {
            stock.isEnoughProductStockCountForOrder(orderCount);
        });
    }

    @Test
    @DisplayName("상품 재고 감소")
    void stock_decrease() {
        // given
        Stock stock = new Stock(1L, 1L, 10L);
        Long orderStockCount = 5L;

        // when
        Stock decreaseProductStock = stock.decreaseStock(orderStockCount);

        // then
        assertThat(decreaseProductStock.stockCount()).isEqualTo(10L - 5L);
    }


    @Test
    @DisplayName("상품 재고 증가")
    void stock_increase() {
        // given
        Stock stock = new Stock(1L, 1L, 10L);
        Long orderStockCount = 5L;

        // when
        Stock increaseProductStock = stock.increaseStock(orderStockCount);

        // then
        assertThat(increaseProductStock.stockCount()).isEqualTo(10L + 5L);
    }

}
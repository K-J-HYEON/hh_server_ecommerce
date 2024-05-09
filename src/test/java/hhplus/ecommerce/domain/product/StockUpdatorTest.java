package hhplus.ecommerce.domain.product;

import hhplus.ecommerce.domain.orderitem.OrderItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

class StockUpdatorTest {

    @MockBean
    private Stock mockStock;

    @MockBean
    private Product product;

    @MockBean
    private OrderItem item;

    @MockBean
    private StockRepository stockRepository;

    @InjectMocks
    private StockUpdator stockUpdator;

    @Test
    @DisplayName("재고 감소 업데이트 메서드 호출 시 stock의 재고 감소 메서드 호출")
    void decrease_product_stock() {

        // given
        given(mockStock.productId()).willReturn(1L);
        given(mockStock.stockCount()).willReturn(5L);

        // when
        stockUpdator.decreaseStock(mockStock, item);

        // then
        verify(stockRepository, atLeastOnce()).updateStock(any());
        verify(mockStock, atLeastOnce()).increaseStock(any());
    }

    @Test
    @DisplayName("재고 증가 업데이트 메서드 호출 시 stock의 재고 증가 메서드 호출")
    void increase_product_stock() {

        // given
        given(mockStock.productId()).willReturn(1L);
        given(mockStock.stockCount()).willReturn(5L);

        // when
        stockUpdator.increaseStock(mockStock, item);

        // then
        verify(stockRepository, atLeastOnce()).updateStock(any());
        verify(mockStock, atLeastOnce()).increaseStock(any());
    }
}
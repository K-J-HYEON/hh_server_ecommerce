package hhplus.ecommerce.domain.product;

import hhplus.ecommerce.api.dto.request.OrderRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

class StockUpdatorTest {

    @MockBean
    private StockRepository stockRepository;

    @InjectMocks
    private StockUpdator stockUpdator;

//    @BeforeEach
//    void setUp() {
//        stockRepository = mock(StockRepository.class);
//        stockUpdator = new StockUpdator(stockRepository);
//    }

    @Test
    @DisplayName("재고 업데이트 메서드 호출 시 stock의 재고 감소 메서드가 호출")
    void product_stock_update() {
        Stock mockStock1 = mock(Stock.class);
        Stock mockStock2 = mock(Stock.class);

        List<Stock> stocks = List.of(
                mockStock1,
                mockStock2
        );

        List<OrderRequest.ProductOrderRequest> productOrderRequest = List.of(
                new OrderRequest.ProductOrderRequest(1L, 10L),
                new OrderRequest.ProductOrderRequest(2L, 50L)
        );

        given(mockStock1.productId()).willReturn(1L);
        given(mockStock2.productId()).willReturn(2L);

        // when
        List<Stock> updateStockProducts = stockUpdator.decreaseStock(stocks, productOrderRequest);

        // then
        verify(stockRepository, atLeastOnce()).updateStock(any());
        verify(mockStock1, atLeastOnce()).decreaseStock(any());
        verify(mockStock2, atLeastOnce()).decreaseStock(any());
        assertThat(updateStockProducts.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("상품 재고 보상 메서드 호출 시 상품의 재고를 증가하는 메서드와 업데이트 하는 메서드를 호출")
    void product_stock_count_increase() {

        // given
        Stock mockStock1 = mock(Stock.class);
        Stock mockStock2 = mock(Stock.class);

        // when
        List<OrderRequest.ProductOrderRequest> orderRequests = List.of(
                new OrderRequest.ProductOrderRequest(1L, 10L),
                new OrderRequest.ProductOrderRequest(2L, 50L)
        );

        given(mockStock1.productId()).willReturn(1L);
        given(mockStock2.productId()).willReturn(2L);


        // then
        verify(stockRepository, times(2)).updateStock(any());
        verify(mockStock1, atLeastOnce()).increaseStock(any());
        verify(mockStock2, atLeastOnce()).increaseStock(any());
    }
}
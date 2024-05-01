package hhplus.ecommerce.domain.product;

import hhplus.ecommerce.TestFixtures;
import hhplus.ecommerce.api.dto.request.OrderRequest;
import hhplus.ecommerce.api.dto.request.Receiver;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.given;

class StockServiceTest {

    @MockBean
    private StockReader stockReader;

    @MockBean
    private StockUpdator stockUpdator;

    @MockBean
    private StockValidator stockValidator;

    @InjectMocks
    private StockService stockService;


    @Test
    @DisplayName("상품 재고 조회")
    void read_product_stock_count() {
        // given
        List<Product> products = List.of(TestFixtures.product("신발"));

        given(stockReader.readByProductIds(anyList())).willReturn(List.of(
                TestFixtures.stock(products.get(0).id())
        ));

        // when
        List<Stock> stocks = stockService.getStocksByProductIds(products);

        // then
        assertThat(stocks.size()).isEqualTo(10);
//        assertThat(stocks.getFirst().productId()).isEqualTo(1L);
//        assertThat(stocks.getFirst().stockCount()).isEqualTo(5L);
    }

    @Test
    @DisplayName("상품 재고 감소")
    void decrease_stock_count() {

        // given
        List<Stock> stocks = List.of(
                TestFixtures.stock(1L),
                TestFixtures.stock(2L)
        );

        OrderRequest request = new OrderRequest(
                new Receiver(
                        "김아무개",
                        "서울특별시 마포구",
                        "01012344321"
                ),
                List.of(
                        new OrderRequest.ProductOrderRequest(1L, 1L),
                        new OrderRequest.ProductOrderRequest(2L, 10L)
                ),
                10_00_000L,
                "CARD"

        );

        given(stockUpdator.decreaseStock(anyList(), anyList())).willReturn(List.of(
                TestFixtures.stock(1L).decreaseStock(request.products().get(0).orderCount()),
                TestFixtures.stock(2L).decreaseStock(request.products().get(1).orderCount())
        ));

        // when
        List<Stock> decreaseProductStock = stockService.decreaseProductStock(stocks, request.products());

        // then
        assertThat(decreaseProductStock.size()).isEqualTo(2);
//        assertThat(decreaseProductStock.getFirst().stockCount()).isEqualTo(5 - 1);
//        assertThat(decreaseProductStock.getLast().stockCount()).isEqualTo(10L - 10L);
    }



}
package hhplus.ecommerce.domain.product;

import hhplus.ecommerce.TestFixtures;
import hhplus.ecommerce.domain.order.Order;
import hhplus.ecommerce.domain.order.OrderReader;
import hhplus.ecommerce.domain.orderitem.OrderItem;
import hhplus.ecommerce.storage.order.OrderStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

class StockServiceTest {

    @MockBean
    private StockReader stockReader;

    @MockBean
    private StockUpdator stockUpdator;

    @MockBean
    private OrderReader orderReader;

    @MockBean
    private StockValidator stockValidator;

    @InjectMocks
    private StockService stockService;

    @Test
    @DisplayName("상품 재고 감소")
    void decrease_product_stock_count() {
        // Given
        Product product = TestFixtures.product("신발");
        Stock stock = TestFixtures.stock(product.id());

        given(stockReader.readByProductId(any())).willReturn(stock);

        OrderItem item = new OrderItem(1L, 1L, product.id(), product.name(), product.price(), product.orderTotalPrice(3L), 3L, "CREATED");

        // When
        stockService.decreaseProductStock(item);

        // then
        verify(stockValidator, atLeastOnce()).checkProductStockCountForOrder(any(), any());
        verify(stockUpdator, atLeastOnce()).decreaseStock(any(), any());
    }

    @Test
    @DisplayName("주문이 실패되었을 때, 상품 재고 감소된 상품 재고 다시 증가.")
    void when_order_failed_then_compensate_stock_quantity() {
        // Given
        Order order = TestFixtures.order(OrderStatus.READY);
        Stock stock = TestFixtures.stock(order.items().get(1).productId());

        given(orderReader.read(any())).willReturn(order);
        given(stockReader.readByProductId(any())).willReturn(stock);

        // When
        stockService.compensationOrderStock(order);

        // Then
        verify(stockUpdator, atLeastOnce()).increaseStock(any(), any());

    }


//    @Test
//    @DisplayName("상품 재고 조회")
//    void read_product_stock_count() {
//        // given
//        List<Product> products = List.of(TestFixtures.product("신발"));
//
//        given(stockReader.readByProductIds(anyList())).willReturn(List.of(
//                TestFixtures.stock(products.get(0).id())
//        ));
//
//        // when
//        List<Stock> stocks = stockService.getStocksByProductIds(products);
//
//        // then
//        assertThat(stocks.size()).isEqualTo(10);
////        assertThat(stocks.getFirst().productId()).isEqualTo(1L);
////        assertThat(stocks.getFirst().stockCount()).isEqualTo(5L);
//    }
//
//    @Test
//    @DisplayName("상품 재고 감소")
//    void decrease_stock_count() {
//
//        // given
//        List<Stock> stocks = List.of(
//                TestFixtures.stock(1L),
//                TestFixtures.stock(2L)
//        );
//
//        OrderRequest request = new OrderRequest(
//                new Receiver(
//                        "김아무개",
//                        "서울특별시 마포구",
//                        "01012344321"
//                ),
//                List.of(
//                        new OrderRequest.ProductOrderRequest(1L, 1L),
//                        new OrderRequest.ProductOrderRequest(2L, 10L)
//                ),
//                10_00_000L,
//                "CARD"
//
//        );
//
//        given(stockUpdator.updateStockForOrder(anyList(), anyList())).willReturn(List.of(
//                TestFixtures.stock(1L).decreaseStock(request.products().get(0).orderCount()),
//                TestFixtures.stock(2L).decreaseStock(request.products().get(1).orderCount())
//        ));
//
//        // when
//        List<Stock> decreaseProductStock = stockService.decreaseProductStock(List.of(), request);
//
//        // then
//        assertThat(decreaseProductStock.size()).isEqualTo(2);
////        assertThat(decreaseProductStock.getFirst().stockCount()).isEqualTo(5 - 1);
////        assertThat(decreaseProductStock.getLast().stockCount()).isEqualTo(10L - 10L);
//    }
}
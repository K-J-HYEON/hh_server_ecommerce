package hhplus.ecommerce.domain.product;

import hhplus.ecommerce.TestFixtures;
import hhplus.ecommerce.domain.orderitem.OrderItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.mockito.Mockito.*;

class StockValidatorTest {

    @MockBean
    private Stock stock;

    @InjectMocks
    private StockValidator stockValidator;

    @Test
    @DisplayName("주문 결제 시 상품의 재고 검증하는 메서드가 호출되는지 테스트")
    void when_product_stock_count_for_order_then_call_is_enough_product_stock_count_for_order() {

        // given
        Product product = TestFixtures.product("신발");
        OrderItem item = new OrderItem(
                1L, 1L,
                product.id(), product.name(),
                product.price(), product.orderTotalPrice(3L),
                3L, "CREATED");

        // when
        stockValidator.checkProductStockCountForOrder(stock, item);

        // then
        verify(stock, atLeastOnce()).isEnoughProductStockCountForOrder(any());
    }

//    @Test
//    @DisplayName("주문 결제 시 상품 재고를 검증하는 메서드가 호출")
//    void product_stock_count_for_order_then_call_is_enough_product_stock_count_for_order() {
//        // given
//        Long stockCount = 5L;
//        OrderRequest.ProductOrderRequest productOrderRequest = new OrderRequest.ProductOrderRequest(1L, stockCount);
//        List<OrderRequest.ProductOrderRequest> productOrderRequests = List.of(productOrderRequest);
//
//        given(stock.productId()).willReturn(1L);
//
//        // when
//        stockValidator.checkProductStockCountForOrder(List.of(stock), productOrderRequests);
//
//
//        // then
//        verify(stock, times(1)).isEnoughProductStockCountForOrder(stockCount);
//
//    }
//
//    @Test
//    @DisplayName("주문 결제 시 주문 요청 상품 id와 상품 id가 일치하지 않으면 예외 처리")
//    void when_product_stock_count_for_order_then_throw_entity_not_found_exception() {
//
//        // given
//        Long stockCount = 5L;
//        OrderRequest.ProductOrderRequest productOrderRequest = new OrderRequest.ProductOrderRequest(1L, stockCount);
//        List<OrderRequest.ProductOrderRequest> productOrderRequests = List.of(productOrderRequest);
//
//
//        given(stock.productId()).willReturn(2L);
//
//        // when & then
//        assertThrows(EntityNotFoundException.class, () -> {
//            stockValidator.checkProductStockCountForOrder(Collections.singletonList(stock), productOrderRequests);
//        });
//    }
}
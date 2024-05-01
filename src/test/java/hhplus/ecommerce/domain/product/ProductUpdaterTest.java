package hhplus.ecommerce.domain.product;

import hhplus.ecommerce.TestFixtures;
import hhplus.ecommerce.api.dto.request.OrderRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.mockito.Mockito.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ProductUpdaterTest {

    @MockBean
    private ProductRepository productRepository;

    @InjectMocks
    private ProductUpdater productUpdater;

    @Test
    @DisplayName("상품 재고 업데이트")
    void product_stock_update() {

        // given
        List<Product> products = List.of(
                TestFixtures.product("신발"),
                TestFixtures.product("바지")
        );

        List<OrderRequest.ProductOrderRequest> productOrderRequest = List.of(
                new OrderRequest.ProductOrderRequest(1L, 10L),
                new OrderRequest.ProductOrderRequest(2L, 50L)
        );

        // when
        productUpdater.updateStock(products, productOrderRequest);

        // then
        verify(productRepository, atLeast(2)).updateStock(any());
    }
}
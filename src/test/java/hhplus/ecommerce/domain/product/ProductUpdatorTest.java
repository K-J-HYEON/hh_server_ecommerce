package hhplus.ecommerce.domain.product;

import hhplus.ecommerce.TestFixtures;
import hhplus.ecommerce.domain.order.Order;
import hhplus.ecommerce.domain.orderitem.OrderItem;
import hhplus.ecommerce.storage.order.OrderStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import java.time.LocalDateTime;
import java.util.List;

class ProductUpdatorTest {

    @InjectMocks
    private ProductUpdator productUpdator;

    @MockBean
    private ProductRepository productRepository;

    @Test
    @DisplayName("상품 재고 업데이트")
    void product_stock_update() {

        // given
        List<Product> products = List.of(
                TestFixtures.product("신발"),
                TestFixtures.product("바지")
        );

        Product product = mock(Product.class);

        given(product.id()).willReturn(1L);

        OrderItem item = new OrderItem(
                1L, 1L,
                product.id(), product.name(),
                product.price(), product.orderTotalPrice(3L),
                3L, "CREATED");

        Order order = new Order(1L, 1L,
                50_000L, List.of(item),
                "이름", "주소",
                "번호", OrderStatus.PAID.toString(),
                "CARD", LocalDateTime.now());

        // when
        productUpdator.updateStock(products, order);

        // then
        verify(productRepository, atLeast(2)).updateStock(any());
    }
}
package hhplus.ecommerce.domain.orderitem;

import hhplus.ecommerce.TestFixtures;
import hhplus.ecommerce.api.dto.request.OrderRequest;
import hhplus.ecommerce.domain.order.Order;
import hhplus.ecommerce.domain.product.Product;
import hhplus.ecommerce.storage.order.OrderStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class OrderItemAppenderTest {

    private OrderItemRepository orderItemRepository;

    private OrderItemAppender orderItemAppender;

    @BeforeEach
    void setUp() {
        orderItemRepository = mock(OrderItemRepository.class);

        orderItemAppender = new OrderItemAppender(orderItemRepository);
    }

    @Test
    @DisplayName("주문 아이템 생성")
    void create_order_items() {
        // given
        Order order = TestFixtures.order(OrderStatus.READY);
        List<Product> product = List.of(
                TestFixtures.product("신발"),
                TestFixtures.product("바지")
        );

        List<OrderRequest.ProductOrderRequest> productOrderRequest = List.of(
                new OrderRequest.ProductOrderRequest(1L, 5L),
                new OrderRequest.ProductOrderRequest(2L, 3L)
        );

        given(orderItemRepository.createOrderItem(any())).willReturn(List.of(
                TestFixtures.orderItem(1L, product.get(0).id()),
                TestFixtures.orderItem(2L, product.get(1).id())
        ));

        // when
        List<OrderItem> orderItems = orderItemAppender.create(order, product, productOrderRequest);

        // then
        assertThat(orderItems.size()).isEqualTo(2);
        assertThat(orderItems.get(1).unitPrice()).isEqualTo(30_000L);
        assertThat(orderItems.get(1).totalPrice()).isEqualTo(90_000L);
    }
}
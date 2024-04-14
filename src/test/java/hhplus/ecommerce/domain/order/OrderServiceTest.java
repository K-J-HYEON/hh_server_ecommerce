package hhplus.ecommerce.domain.order;

import hhplus.ecommerce.api.dto.request.OrderRequest;
import hhplus.ecommerce.api.dto.request.Receiver;
import hhplus.ecommerce.domain.product.Product;
import hhplus.ecommerce.storage.order.OrderStatus;
import hhplus.ecommerce.domain.orderitem.OrderItemAppender;
import hhplus.ecommerce.TestFixtures;
import hhplus.ecommerce.domain.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.*;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
    private OrderService orderService;
    private OrderItemAppender orderItemAppender;
    private OrderProcessor orderProcessor;
    private OrderUpdater orderUpdater;

    private User user;
    private OrderRequest orderReq;

    @BeforeEach
    void setUp() {
        orderItemAppender = mock(OrderItemAppender.class);
        orderProcessor = mock(OrderProcessor.class);
        orderUpdater = mock(OrderUpdater.class);

        orderService = new OrderService(orderItemAppender, orderProcessor, orderUpdater);

        user = TestFixtures.user(1L);
        orderReq = new OrderRequest(
                new Receiver(
                        user.name(),
                        user.address(),
                        user.phoneNumber()
                ),
                List.of(
                        new OrderRequest.ProductOrderRequest(1L, 1L)
                ),
                100_000L,
                "MOBILE PAY"
        );
    }

    @Test
    @DisplayName("주문 생성 성공 => 주문 상태 pending")
    void order_success() {

        // given
        List<Product> products = List.of();
        Order readyOrder = TestFixtures.order(OrderStatus.READY);
        Order completedOrder = TestFixtures.order(OrderStatus.PENDING_FOR_PAY);

        given(orderProcessor.order(any(), any())).willReturn(readyOrder);
        given(orderUpdater.changeStatus(any(), any())).willReturn(completedOrder);

        // when
        Order order = orderService.order(user, products, orderReq);

        // then
        assertThat(order).isNotNull();
        assertThat(order.payAmount()).isEqualTo(200_000L);
        assertThat(order.orderStatus()).isEqualTo("PENDING FOR PAY");
        verify(orderUpdater, atLeastOnce()).changeStatus(any(), any());
    }
}
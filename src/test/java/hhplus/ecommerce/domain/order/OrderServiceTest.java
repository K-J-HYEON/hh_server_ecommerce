package hhplus.ecommerce.domain.order;

import hhplus.ecommerce.api.dto.request.OrderRequest;
import hhplus.ecommerce.api.dto.request.Receiver;
import hhplus.ecommerce.domain.cart.Cart;
import hhplus.ecommerce.storage.order.OrderStatus;
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
    private OrderAppender orderAppender;
    private OrderUpdater orderUpdater;

    private User user;
    private OrderRequest orderReq;

    @BeforeEach
    void setUp() {
        orderAppender = mock(OrderAppender.class);
        orderUpdater = mock(OrderUpdater.class);

        orderService = new OrderService(orderAppender, orderUpdater);

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
        Order readyOrder = TestFixtures.order(OrderStatus.READY);
        Cart cart = new Cart(1L, user.id(), List.of());

        given(orderAppender.append(any(), any(), any())).willReturn(readyOrder);

        // when
        Order order = orderService.order(user, cart, orderReq);

        // then
        assertThat(order).isNotNull();
        assertThat(order.payAmount()).isEqualTo(200_000L);
        assertThat(order.orderStatus()).isEqualTo("READY");
    }

    @Test
    @DisplayName("주문 실패하면 주문 상태 변경")
    void when_order_failed_then_order_status_is_failed() {
        // given
        Order order = TestFixtures.order(OrderStatus.READY);

        // when
        orderService.orderFailed(order);

        // then
        verify(orderUpdater, atLeastOnce()).changeStatus(any(), any());
    }
}
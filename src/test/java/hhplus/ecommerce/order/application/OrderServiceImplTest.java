package hhplus.ecommerce.order.application;

import hhplus.ecommerce.order.domain.Order;
import hhplus.ecommerce.order.dto.request.OrderReq;
import hhplus.ecommerce.order.dto.request.Receiver;
import hhplus.ecommerce.order.util.OrderProcessor;
import hhplus.ecommerce.order.util.OrderStatus;
import hhplus.ecommerce.order.util.OrderUpdater;
import hhplus.ecommerce.orderitem.application.OrderItemAppender;
import hhplus.ecommerce.TestFixtures;
import hhplus.ecommerce.product.application.ProductRetrieve;
import hhplus.ecommerce.product.application.ProductUpdater;
import hhplus.ecommerce.product.application.ProductValidator;
import hhplus.ecommerce.user.domain.User;
import hhplus.ecommerce.user.util.UserRetrieve;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.*;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {
    private OrderService orderService;
    private UserRetrieve userRetrieve;
    private ProductRetrieve productRetrieve;
    private ProductUpdater productUpdater;
    private OrderItemAppender orderItemAppender;
    private OrderProcessor orderProcessor;
    private OrderUpdater orderUpdater;
    private ProductValidator productValidator;

    private User user;
    private OrderReq orderReq;

    @BeforeEach
    void setUp() {
        userRetrieve = mock(UserRetrieve.class);
        productRetrieve = mock(ProductRetrieve.class);
        productUpdater = mock(ProductUpdater.class);
        orderItemAppender = mock(OrderItemAppender.class);
        orderProcessor = mock(OrderProcessor.class);
        orderUpdater = mock(OrderUpdater.class);
        productValidator = mock(ProductValidator.class);

        orderService = new OrderServiceImpl(userRetrieve, productRetrieve, productUpdater, orderItemAppender, orderProcessor, orderUpdater, productValidator);

        user = TestFixtures.user(1L);
        orderReq = new OrderReq(
                new Receiver(
                        user.name(),
                        user.address(),
                        user.phoneNumber()
                ),
                List.of(
                        new OrderReq.ProductOrderReq(1L, 1L)
                ),
                100_000L
        );
    }

    @Test
    @DisplayName("주문 성공 => 주문 상태 complete")
    void order_success() {
        // given
        Order readyOrder = TestFixtures.order(OrderStatus.READY);
        Order completedOrder = TestFixtures.order(OrderStatus.COMPLETE);

        given(userRetrieve.retrieveByUserId(anyLong())).willReturn(user);
        given(orderProcessor.order(any(), any())).willReturn(readyOrder);
        given(productRetrieve.retrieveAllByIds(any())).willReturn(List.of());
        given(orderUpdater.changeStatus(any(), any())).willReturn(completedOrder);

        // when
        Order order = orderService.order(user.id(), orderReq);

        // then
        assertThat(order).isNotNull();
        assertThat(order.payAmount()).isEqualTo(89_000L);
        assertThat(order.orderStatus()).isEqualTo("complete");
        verify(productUpdater, atLeastOnce()).updateStock(any(), any());
        verify(orderUpdater, atLeastOnce()).changeStatus(any(), any());
    }
}
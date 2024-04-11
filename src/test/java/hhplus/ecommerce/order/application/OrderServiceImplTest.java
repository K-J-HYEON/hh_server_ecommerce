package hhplus.ecommerce.order.application;

import hhplus.ecommerce.domain.order.OrderService;
import hhplus.ecommerce.domain.order.OrderServiceImpl;
import hhplus.ecommerce.domain.order.Order;
import hhplus.ecommerce.api.dto.request.OrderReq;
import hhplus.ecommerce.api.dto.request.Receiver;
import hhplus.ecommerce.domain.order.OrderProcessor;
import hhplus.ecommerce.storage.order.OrderStatus;
import hhplus.ecommerce.domain.order.OrderUpdater;
import hhplus.ecommerce.domain.orderitem.OrderItemAppender;
import hhplus.ecommerce.TestFixtures;
import hhplus.ecommerce.domain.product.ProductReader;
import hhplus.ecommerce.domain.product.ProductUpdater;
import hhplus.ecommerce.domain.product.ProductValidator;
import hhplus.ecommerce.domain.user.User;
import hhplus.ecommerce.domain.user.UserReader;
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
    private UserReader userReader;
    private ProductReader productReader;
    private ProductUpdater productUpdater;
    private OrderItemAppender orderItemAppender;
    private OrderProcessor orderProcessor;
    private OrderUpdater orderUpdater;
    private ProductValidator productValidator;

    private User user;
    private OrderReq orderReq;

    @BeforeEach
    void setUp() {
        userReader = mock(UserReader.class);
        productReader = mock(ProductReader.class);
        productUpdater = mock(ProductUpdater.class);
        orderItemAppender = mock(OrderItemAppender.class);
        orderProcessor = mock(OrderProcessor.class);
        orderUpdater = mock(OrderUpdater.class);
        productValidator = mock(ProductValidator.class);

        orderService = new OrderServiceImpl(userReader, productReader, productUpdater, orderItemAppender, orderProcessor, orderUpdater, productValidator);

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

        given(userReader.retrieveByUserId(anyLong())).willReturn(user);
        given(orderProcessor.order(any(), any())).willReturn(readyOrder);
        given(productReader.retrieveAllByIds(any())).willReturn(List.of());
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
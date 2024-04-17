package hhplus.ecommerce.domain.order;

import hhplus.ecommerce.TestFixtures;
import hhplus.ecommerce.api.dto.request.OrderRequest;
import hhplus.ecommerce.api.dto.request.Receiver;
import hhplus.ecommerce.domain.user.User;
import hhplus.ecommerce.storage.order.OrderStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.assertj.core.api.Assertions.*;

class OrderProcessorTest {

    private OrderRepository orderRepository;

    private OrderProcessor orderProcessor;

    @BeforeEach
    void setUp() {
        orderRepository = mock(OrderRepository.class);
        orderProcessor = new OrderProcessor(orderRepository);
    }

    @Test
    @DisplayName("주문 생성")
    void createOrder() {

        // given
        User user = TestFixtures.user(1L);
        OrderRequest orderRequest = new OrderRequest(
                new Receiver(
                        user.name(),
                        user.address(),
                        user.phoneNumber()
                ),
                List.of(
                        new OrderRequest.ProductOrderRequest(1L, 1L)
                ),
                500_000L,
                "MOBILE_PAY"
        );

        Order order = new Order(
                1L,
                user.id(),
                orderRequest.paymentAmount(),
                orderRequest.receiver().name(),
                orderRequest.receiver().address(),
                orderRequest.receiver().phoneNumber(),
                OrderStatus.READY.toString(),
                LocalDateTime.now());

        given(orderRepository.order(any(), any())).willReturn(order);

        // when
        Order createOrder = orderProcessor.order(user, orderRequest);

        // then
        assertThat(createOrder.orderStatus()).isEqualTo("READY");
        assertThat(createOrder.payAmount()).isEqualTo(500_000L);
    }
}
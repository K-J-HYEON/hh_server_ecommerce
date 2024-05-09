package hhplus.ecommerce.domain.order;

import hhplus.ecommerce.TestFixtures;
import hhplus.ecommerce.domain.orderitem.OrderItem;
import hhplus.ecommerce.domain.orderitem.OrderItemRepository;
import hhplus.ecommerce.storage.order.OrderItemStatus;
import hhplus.ecommerce.storage.order.OrderStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class OrderUpdaterTest {

    @MockBean
    private OrderRepository orderRepository;

    @MockBean
    private OrderItemRepository orderItemRepository;

    @InjectMocks
    private OrderUpdater orderUpdater;

//    @BeforeEach
//    void setUp() {
//        orderRepository = mock(OrderRepository.class);
//
//        orderUpdater = new OrderUpdater(orderRepository,);
//    }

//    @Test
//    @DisplayName("주문 상태를 변경")
//    void change_order_status() {
//        // given
//        Order readyOrder = TestFixtures.order(OrderStatus.READY);
//        Order testCase1 = TestFixtures.order(OrderStatus.PENDING_FOR_PAY);
//        Order testCase2 = TestFixtures.order(OrderStatus.PAID);
//
//        given(orderRepository.updateStatus(any(), any())).willReturn(testCase1);
//        given(orderRepository.updateStatus(any(), any())).willReturn(testCase2);
//
//        // when
//        Order order1 = orderUpdater.changeStatus(readyOrder, OrderStatus.PENDING_FOR_PAY);
//        Order order2 = orderUpdater.changeStatus(readyOrder, OrderStatus.PAID);
//
//        // then
//        assertThat(order1.orderStatus()).isEqualTo("PENDING_FOR_PAY");
//        assertThat(order2.orderStatus()).isEqualTo("PAID");
//    }

    @Test
    @DisplayName("주문 상태를 변경")
    void change_order_status() {

        // given
        Order readyOrder = TestFixtures.order(OrderStatus.READY);

        // when
        orderUpdater.changeStatus(readyOrder, OrderStatus.CANCELED);

        // then
        verify(orderRepository, atLeastOnce()).updateStatus(any(), any());

    }

    @Test
    @DisplayName("주문 아이템의 상태 변경")
    void change_order_item_status() {

        // given
        OrderItem item = TestFixtures.orderItem(1L, 1L);

        // when
        orderUpdater.changeItemStatus(item, OrderItemStatus.SUCCESS);

        // then
        verify(orderItemRepository, atLeastOnce()).updateStatus(any(), any());
    }
}
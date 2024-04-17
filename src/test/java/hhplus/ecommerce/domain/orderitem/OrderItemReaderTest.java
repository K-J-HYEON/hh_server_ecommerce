package hhplus.ecommerce.domain.orderitem;

import hhplus.ecommerce.TestFixtures;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class OrderItemReaderTest {

    private OrderItemRepository orderItemRepository;

    private OrderItemReader orderItemReader;

    @BeforeEach
    void setUp() {
        orderItemRepository = mock(OrderItemRepository.class);

        orderItemReader = new OrderItemReader(orderItemRepository);
    }

    @Test
    @DisplayName("주문 아이템 목록 조회")
    void read_order_items() {

        // given
        Long orderId = 1L;

        List<OrderItem> orderItemList = List.of(
                TestFixtures.orderItem(orderId, 1L)
        );

        given(orderItemRepository.findAllByOrderId(any())).willReturn(orderItemList);

        // when
        List<OrderItem> foundOrderItemList = orderItemReader.readAllByOrderId(orderId);

        // then
        assertThat(foundOrderItemList.size()).isEqualTo(1);
//        assertThat(foundOrderItemList.getFirst().productName()).isEqualTo("신발");
    }
}
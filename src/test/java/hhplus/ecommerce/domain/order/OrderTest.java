package hhplus.ecommerce.domain.order;

import hhplus.ecommerce.TestFixtures;
import hhplus.ecommerce.storage.order.OrderStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    @DisplayName("주문 상태 변경 테스트")
    void updateOrderStatus() {

        // given
        Order order = TestFixtures.order(OrderStatus.READY);

        // when
        Order updateOrderStatus = order.changeStatus(OrderStatus.PAID);

        // then
        assertThat(updateOrderStatus.orderStatus()).isEqualTo(OrderStatus.PAID.toString());
    }

}